package springbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class DipSunyardApplication {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getHel() {

		return "helloWorld";
	}
	public static void main(String[] args) {
		SpringApplication.run(DipSunyardApplication.class, args);
	}
}
