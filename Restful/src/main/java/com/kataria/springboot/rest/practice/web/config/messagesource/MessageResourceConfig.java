package com.kataria.springboot.rest.practice.web.config.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageResourceConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSesource = new ResourceBundleMessageSource();
		messageSesource.setBasename("i18n/messages");
		messageSesource.setFallbackToSystemLocale(false);
		return messageSesource;
	}

}
