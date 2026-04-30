package com.pedidos.sistema_pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.pedidos.controller",
    "com.pedidos.service"
})
@EntityScan(basePackages = {
    "com.pedidos.model"
})
@EnableJpaRepositories(basePackages = {
    "com.pedidos.repository"
})
public class SistemaPedidosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaPedidosApplication.class, args);
    }
}