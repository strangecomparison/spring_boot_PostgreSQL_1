package ru.vasseugs.spring_boot_postgresql_1.service;

import org.springframework.stereotype.Service;
import ru.vasseugs.spring_boot_postgresql_1.entities.CountryEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarModelEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.ManufacturerEntity;
import ru.vasseugs.spring_boot_postgresql_1.dto.GuitarDTO;
import ru.vasseugs.spring_boot_postgresql_1.repositories.CountryRepository;
import ru.vasseugs.spring_boot_postgresql_1.repositories.GuitarModelRepository;
import ru.vasseugs.spring_boot_postgresql_1.repositories.GuitarRepository;
import ru.vasseugs.spring_boot_postgresql_1.repositories.ManufacturerRepository;

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
    public void save(GuitarDTO guitarDTO) {

        // first, creating a new instance of target entity
        GuitarEntity guitar = new GuitarEntity();

        /* initializing these entities by obtaining corresponding records from DB
        using received data from our web form.
        we shouldn't create new because they exist as single instances
         */
        ManufacturerEntity manufacturer = manufacturerRepository.getById((long) guitarDTO.getManufacturer());
        GuitarModelEntity model = guitarModelRepository.getById((long) guitarDTO.getModel());
        CountryEntity country = countryRepository.getById((long) guitarDTO.getCountry());

        // initializing entity fields with obtained entities
        guitar.setManufacturer(manufacturer);
        guitar.setGuitarModel(model);
        guitar.setCountry(country);
        guitar.setYearOfIssue(guitarDTO.getYearOfIssue()); // except this field

        // adding this particular guitar instance into other entities' lists connected with it
        manufacturer.addGuitar(guitar);
        model.addGuitar(guitar);
        country.addGuitar(guitar);

        // finally saving
        guitarRepository.save(guitar);

    }



}
