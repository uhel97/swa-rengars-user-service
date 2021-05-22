package cz.cvut.fel.oi.swa.rengars.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("cz.cvut.fel.oi.swa.rengars.userservice")
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {

		SpringBootUtil.setPort(args,8800, 8900);
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
