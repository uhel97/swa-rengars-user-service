package cz.cvut.fel.oi.swa.rengars.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableFeignClients("cz.cvut.fel.oi.swa.rengars.userservice")
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {

//		SpringBootUtil.setPort(args,8800, 8900);
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
