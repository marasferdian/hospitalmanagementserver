package com.cmedresearch.officemaptool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
public class OfficemaptoolApplication {
  @Bean
  public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.addScript(new ClassPathResource("schema.sql"));
    dataSourceInitializer.setDatabasePopulator(databasePopulator);
    dataSourceInitializer.setEnabled(true);
    return dataSourceInitializer;
  }

  public static void main(String[] args) {
    SpringApplication.run(OfficemaptoolApplication.class, args);
  }
}
