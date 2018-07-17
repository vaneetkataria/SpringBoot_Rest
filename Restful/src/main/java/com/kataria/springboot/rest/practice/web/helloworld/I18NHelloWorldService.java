package com.kataria.springboot.rest.practice.web.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18NHelloWorldService {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/i18n/Hello-world")
	public String helloWorld(Locale locale) {
		return "Hello World " + locale.getLanguage() + "-" + locale.getCountry();
	}

	@GetMapping(path = "/i18n/Hello-world-multilingual")
	public String helloWorld() {
		return messageSource.getMessage("hello.world.greeting.message", null, LocaleContextHolder.getLocale());
	}

}
