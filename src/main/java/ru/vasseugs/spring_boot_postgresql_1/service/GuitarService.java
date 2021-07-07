package ru.vasseugs.spring_boot_postgresql_1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vasseugs.spring_boot_postgresql_1.entities.CountryEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarModelEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.ManufacturerEntity;
import ru.vasseugs.spring_boot_postgresql_1.dto.GuitarDTO;
import ru.vasseugs.spring_boot_postgresql_1.repositories.CountryRepository;
import ru.vasseugs.spring_boot_postgresql_1.repositories.GuitarModelRepository;
import ru.vasseugs.spring_boot_postgresql_1.repositories.GuitarRepository;
import ru.vasseugs.spring_boot_postgresql_1.repositories.ManufacturerRepository;

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



    public GuitarEntity showGuitar(long id) {
        return guitarRepository.findById(id)
                .orElse(null);
    }

    public void deleteGuitar(long id) {
        guitarRepository.deleteById(id);
    }



}
