package ru.vasseugs.spring_boot_postgresql_1.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "userEntityManagerFactory",
        basePackages = {"ru.vasseugs.spring_boot_postgresql_1.domain.user"},
        transactionManagerRef = "userTransactionManager"
)
public class UserDBConfig {

    @Primary
    @Bean(name="userDSProperties")
    @ConfigurationProperties("spring.datasource.users")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name="userDataSource")
    public DataSource personDataSource(@Qualifier("userDSProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name="userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("userDataSource") DataSource dataSource
    ) {


        return builder.
                dataSource(dataSource)
                .packages("ru.vasseugs.spring_boot_postgresql_1.domain.user")
                .persistenceUnit("User")
                .build();

    }

    @Primary
    @Bean(name="userTransactionManager")
    @ConfigurationProperties("spring.user.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("userEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager((entityManagerFactory));
    }

}