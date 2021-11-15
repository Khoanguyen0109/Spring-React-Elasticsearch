package com.example.demoprojectapi;

import com.example.demoprojectapi.Filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.FilterRegistration;

@EnableJpaRepositories(basePackages = "com.example.demoprojectapi.repositories")
@SpringBootApplication
public class DemoProjectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApiApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean (){
//		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//		AuthFilter authFilter = new AuthFilter();
//		registrationBean.setFilter(authFilter);
////		registrationBean.addUrlPatterns("/api/documents/*");
//		return registrationBean;
//	}

}
