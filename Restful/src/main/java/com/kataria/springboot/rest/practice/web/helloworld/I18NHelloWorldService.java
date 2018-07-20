package com.kataria.springboot.rest.practice.web.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kataria.springboot.rest.practice.core.beans.RestResponse;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Internationalised Hello world service." })
@RestController
public class I18NHelloWorldService {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/i18n/Hello-world")
	public String helloWorld(Locale locale) {
		return "Hello World " + locale.getLanguage() + "-" + locale.getCountry();
	}

	@ApiOperation(value = "API to produce hello world for various Locales.",
			notes = "This API produces Hello-world message as per specified locale . By default en_US locale runs . To chnage a locale specify a paramter named language as fr_FR_paris . For the next time until changed language parameter is not sent with any request content will be served in locale fr_FR_paris only  ")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Success."),
			@ApiResponse(code = 500, response = String.class, message = "In case of some server side error.") })
	@GetMapping(path = "/i18n/Hello-world-multilingual", produces = MediaType.TEXT_PLAIN_VALUE)
	public String helloWorld(@ApiParam(
			value = "Locale to be specified in languageName_countryName_variantName(fr_FR_paris or fr_FR or fr only) format . Send this if content is required as per some other locale .",
			required = false,
			defaultValue = "en_US") @RequestParam(name = "language", required = false) String language) {
		return messageSource.getMessage("hello.world.greeting.message", null, LocaleContextHolder.getLocale());
	}

}
