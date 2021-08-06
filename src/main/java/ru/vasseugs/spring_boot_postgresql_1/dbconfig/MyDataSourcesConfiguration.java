package ru.vasseugs.spring_boot_postgresql_1.dbconfig;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration(proxyBeanMethods = false)
public class MyDataSourcesConfiguration {
    
    @Bean(name = "guitarsDB")
    @Primary
    @ConfigurationProperties("app.datasource.guitars")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "usersDB")
    @ConfigurationProperties("app.datasource.users")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }
}
