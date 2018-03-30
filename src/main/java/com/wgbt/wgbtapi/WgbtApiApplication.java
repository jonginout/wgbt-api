package com.wgbt.wgbtapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableJpaAuditing    // 데이터 변경사항 추적 Auditing 데이터가 생성될때 Auditing 해준다.
@EnableSwagger2
public class WgbtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WgbtApiApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("wgbt-api")
				.apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("WGBT APP")
				.description("WGBT APP Documents")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Niklas Heidloff")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
				.version("2.0")
				.build();
	}

}