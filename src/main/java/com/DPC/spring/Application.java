package com.DPC.spring;

import com.DPC.spring.entities.ERole;
import com.DPC.spring.entities.Role;
import com.DPC.spring.entities.User;
import com.DPC.spring.entities.UserDetails;
import com.DPC.spring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2


public class Application   extends SpringBootServletInitializer /*implements ApplicationRunner */{


	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserDetailsRepository userDetailsRepository;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}



	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoderBean = applicationContext.getBean(BCryptPasswordEncoder.class);
		return passwordEncoderBean;
	}

//	@Override
//public void run(ApplicationArguments args) throws Exception {
//
//		// Save roles*/
//
// Role adminRole = this.roleRepository.save(new Role(ERole.ADMIN));
//		Role adherentRole = this.roleRepository.save(new Role(ERole.ADHERENT));
//		Role guestRole = this.roleRepository.save(new Role(ERole.GUEST));


////		 //Save users
//User user1 = new User("marwen", "sghair",
//	"marwen@dipower.fr",
//this.passwordEncoder().encode("12345"),"44444444");
//

	// Save users details
//		Calendar dateOfBirth = Calendar.getInstance();
//		dateOfBirth.set(1992, 7, 21);
//		UserDetails userDetails1 = new UserDetails("10020564",30 ,dateOfBirth.getTime(),
//				"Tunisien", "developpeur", "Homme",
//				" ",  "203336668", new Date( "11/11/1994")
//				);

		// Affect user1 to userDetails1
		//user1.setDetails(userDetails1); // Set child reference
		//userDetails1.setUser(user1); // Set parent reference
	//this.userRepository.save(user1);//


//		// ManyToMany Relations
//	Set<Role> roles = new HashSet<>();
//
//roles.add(adminRole);
//		roles.add(adherentRole);
//		roles.add(guestRole);
//		user1.setRoles(roles);
//this.userRepository.save(user1);
//
//
//	}
}

