package br.com.unicarioca.tcc.usuariosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class UsuariosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosApiApplication.class, args);
	}

}
