package ru.vasseugs.spring_boot_postgresql_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarEntity;
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

    @Autowired
    public GuitarService(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }

    public List<GuitarEntity> getAllGuitars() {
        List<GuitarEntity> allGuitars = new ArrayList<>();
        guitarRepository.findAll()
                .forEach(allGuitars::add);
        return allGuitars;
    }

    public void save(GuitarEntity guitar) {


    }



}
