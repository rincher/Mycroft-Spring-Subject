package is.recruit.mycroft.spring.subjects;

import is.recruit.mycroft.spring.subjects.model.User;
import is.recruit.mycroft.spring.subjects.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@RequiredArgsConstructor
public class SpringSubjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSubjectsApplication.class, args);
	}

}
