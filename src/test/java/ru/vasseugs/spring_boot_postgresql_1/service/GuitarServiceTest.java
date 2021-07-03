package ru.vasseugs.spring_boot_postgresql_1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarEntity;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class GuitarServiceTest {

    @Autowired
    GuitarService guitarService;




}
