package com.geekbrains.spring.lesson8.configs;


import com.geekbrains.spring.lesson8.utils.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.geekbrains.spring.lesson8.repositories")
@EnableTransactionManagement
@ComponentScan("com.geekbrains.spring.lesson8")
public class AppConfig {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

}
