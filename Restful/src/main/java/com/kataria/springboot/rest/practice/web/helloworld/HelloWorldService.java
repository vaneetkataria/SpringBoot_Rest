package com.kataria.springboot.rest.practice.web.helloworld;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kataria.springboot.rest.practice.web.helloworld.beans.HelloWorld;
import com.kataria.springboot.rest.practice.web.helloworld.beans.HelloWorldWithoutGetterSetter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "Hello World Service" })
@RestController
public class HelloWorldService {

	@GetMapping(path = "/Hello-world")
	public String helloWold(
			@ApiParam(value = "Paramter to be printed after hello world", required = true, example = "aaaa",
					defaultValue = "ABC") @RequestParam(required = true, name = "msg") String msg,
			@RequestHeader(required = false, name = "msgHeader", defaultValue = "from Header") String msgHeader) {
		return "Hello World " + msg + " " + msgHeader;
	}

	// Response will be contentType : application/json;charset=UTF-8 only.
	@GetMapping(path = "/Hello-world-Json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String helloWorldJsonUtf8Value() {
		return "Hello World-Json-value";
	}

	// Response will be contentType : application/json;charset=UTF-8 only.
	@GetMapping(path = "/Hello-world-Json-UTF8", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String helloWorldJsonValue() {
		return "Hello-world-Json-UTF8";
	}

	// Content application/xml and application/json can both be demanded by sending
	// in Accept Header. application/octet-stream or application/pdf will not be
	// served . No content will be served as response is not a byte array.
	@GetMapping(path = "/Hello-world-Bean")
	public HelloWorld helloWorldBean() {
		return new HelloWorld("Hello World-Bean");
	}

	@GetMapping(path = "/Hello-world-Bean-WGS")
	public HelloWorldWithoutGetterSetter helloWorldBeanWGS() {
		return new HelloWorldWithoutGetterSetter("Hello World-Bean-WGS");
	}

	// Content application/json will only be served . If application/xml demanded
	// 406 Not acceptable will be thrown by Spring.
	@GetMapping(path = "/Hello-world-Bean-Json", produces = MediaType.APPLICATION_JSON_VALUE)
	public HelloWorld helloWorldBeanJson() {
		return new HelloWorld("Hello World-Bean-Json");
	}

	// Content application/json will only be served . If application/xml demanded
	// 406 Not acceptable will be thrown by Spring.
	@GetMapping(path = "/Hello-world-Bean-Json-UTF8", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HelloWorld helloWorldBeanJsonUTF8() {
		return new HelloWorld("Hello World-Bean-Json-UTF8");
	}

	// Content application/xml will only be served . If application/json demanded
	// 406 Not acceptable will be thrown by Spring.
	@GetMapping(path = "/Hello-world-Bean-xml", produces = MediaType.APPLICATION_XML_VALUE)
	public HelloWorld helloWorldBeanXml() {
		return new HelloWorld("Hello World-Bean-Xml");
	}

}
