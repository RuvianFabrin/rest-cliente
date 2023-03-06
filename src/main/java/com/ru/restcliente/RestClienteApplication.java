package com.ru.restcliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com/ru/restcliente/entities")
@SpringBootApplication
public class RestClienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestClienteApplication.class, args);
    }

}
