package com.kataria.springboot.rest.practice.web.config.locale;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class LocaleResolverConfig extends WebMvcConfigurerAdapter {

	// If CookieLocaleResolver and LocaleChangeInterceptor is not configured then
	// cookie
	// and param concept will not take any affect .
	// Only Accept-Language header can resolve locale . fr-FR-paris in headers
	// instead of fr_FR_paris in params has to be sent . MessageResource will work
	// the same way in both.

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setCookieName("localeCookie");
		cookieLocaleResolver.setDefaultLocale(Locale.US);
		cookieLocaleResolver.setCookieMaxAge(24 * 60 * 60);
		return cookieLocaleResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
