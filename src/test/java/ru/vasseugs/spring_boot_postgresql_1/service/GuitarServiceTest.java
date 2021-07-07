package ru.vasseugs.spring_boot_postgresql_1.service;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarEntity;
import ru.vasseugs.spring_boot_postgresql_1.entities.ManufacturerEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuitarServiceTest {



}
