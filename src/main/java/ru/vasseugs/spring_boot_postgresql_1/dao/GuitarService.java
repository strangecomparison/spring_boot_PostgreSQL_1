package ru.vasseugs.spring_boot_postgresql_1.dao;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.CountryEntity;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.GuitarEntity;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.GuitarModelEntity;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.ManufacturerEntity;
import ru.vasseugs.spring_boot_postgresql_1.dto.GuitarDTO;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.CountryRepository;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.GuitarModelRepository;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.GuitarRepository;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.ManufacturerRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuitarService {

    GuitarRepository guitarRepository;
    ManufacturerRepository manufacturerRepository;
    CountryRepository countryRepository;
    GuitarModelRepository guitarModelRepository;

    public GuitarService(GuitarRepository guitarRepository,
                         ManufacturerRepository manufacturerRepository,
                         CountryRepository countryRepository,
                         GuitarModelRepository guitarModelRepository) {
        this.guitarRepository = guitarRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.countryRepository = countryRepository;
        this.guitarModelRepository = guitarModelRepository;
    }

    // obtaining all guitars
    public List<GuitarEntity> getAllGuitars() {
        List<GuitarEntity> allGuitars = new ArrayList<>();
        guitarRepository.findAll()
                .forEach(allGuitars::add);
        return allGuitars;
    }

    // saving guitar with its relations
    public void save(String manufacturerParam,
                     String modelParam,
                     String countryParam,
                     String yearParam,
                     MultipartFile imageParam) throws IOException {

        // first, creating a new instance of target entity
        GuitarEntity guitar = new GuitarEntity();

        /* initializing these entities by obtaining corresponding records from DB
        using received data from our web form.
        we shouldn't create new because they exist as single instances
         */
        ManufacturerEntity manufacturer = manufacturerRepository.getById(Long.valueOf(manufacturerParam));
        GuitarModelEntity model = guitarModelRepository.getById(Long.valueOf(modelParam));
        CountryEntity country = countryRepository.getById((Long.valueOf(countryParam)));

        // initializing entity fields with obtained entities
        guitar.setManufacturer(manufacturer);
        guitar.setGuitarModel(model);
        guitar.setCountry(country);
        guitar.setYearOfIssue(Integer.valueOf(yearParam)); // except this field
        guitar.setImage(imageParam.getBytes()); // and this

        // adding this particular guitar instance into other entities' lists connected with it
        manufacturer.addGuitar(guitar);
        model.addGuitar(guitar);
        country.addGuitar(guitar);

        // finally saving
        guitarRepository.save(guitar);
    }


    public GuitarDTO showGuitarDTO(long id) {
        GuitarEntity ge = guitarRepository.findById(id)
                .orElse(null);

        // using DTO because we need to convert image into Base64 string
        GuitarDTO dto = null;

        if(ge != null) {
            dto = new GuitarDTO();
            dto.setGuitarId(ge.getId());
            dto.setManufacturer(ge.getManufacturer().getManufacturerName());
            dto.setModel(ge.getGuitarModel().getModelName());
            dto.setCountry(ge.getCountry().getCountryName());
            dto.setYearOfIssue(ge.getYearOfIssue());
            dto.setImage(Base64.encodeBase64String(ge.getImage()));
        }
        return dto;
    }

    public GuitarEntity showGuitarEntity(long id) {
        return guitarRepository.findById(id).orElse(null);
    }

    public void deleteGuitar(long id) {
        guitarRepository.deleteById(id);
    }

    public void edit(long id,
                     String manufacturerParam,
                     String modelParam,
                     String countryParam,
                     String yearParam,
                     MultipartFile imageParam) throws IOException {

        // retrieving the guitar entity
        GuitarEntity toUpdate = guitarRepository.findById(id).orElse(null);

        // retrieving its foreign key values
        ManufacturerEntity manufacturer = manufacturerRepository.getById(Long.valueOf(manufacturerParam));
        GuitarModelEntity model = guitarModelRepository.getById(Long.valueOf(modelParam));
        CountryEntity country = countryRepository.getById((Long.valueOf(countryParam)));

        // updating its values
        assert toUpdate != null;
        toUpdate.setManufacturer(manufacturer);
        toUpdate.setGuitarModel(model);
        toUpdate.setCountry(country);
        toUpdate.setYearOfIssue(Integer.valueOf(yearParam));
        toUpdate.setImage(imageParam.getBytes());

        // finally saving
        guitarRepository.save(toUpdate);
    }



}
