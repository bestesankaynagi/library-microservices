package tr.com.innova.internship.apigateway;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication

public class ApiGatewayApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

		@Bean
		public RestTemplate restTemplate(){
			return new RestTemplate();
		}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
