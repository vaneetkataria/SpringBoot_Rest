package com.kataria.springboot.rest.practice.web.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionedHelloWorld {

	/*
	 * pros : Completely different URL 1. Simplest way to version and understand the
	 * version process being done . 2. API being hit from the browser before this
	 * version process will keep being hit from browser and same for postman .
	 * everything is very clear . 3. Neat to give a URL for something to someone. 4.
	 * Easy to document and and LB rules . 5. Can be easily returned response as
	 * moved permanently for discarded old versions with new version URL in response
	 * headers .
	 * 
	 * Cons 1. Will be hard to manage if HATEOAS is being used . 2. API pollution .
	 * 3. It violates Rest behavior of resource based URLs . Version cannot specify
	 * an resource path .
	 */
	@GetMapping(value = "/v1/Hello-world")
	public String helloWorldVersionedByPathVariable() {
		return "V1-Hello-world";
	}

	/*
	 * 1. Here This URL will be mapped in spring by "/Hello-world?version=1.0"
	 * itself . If parameter will does not come then it will not be hit to
	 * controller and will be a 404. 2. This version is request mapping level
	 * parameter which is completely different from method argument parameters . As
	 * method argument parameters don't change request URL , request URL remains
	 * same as mapped . Method argument parameters are for changing the code
	 * processing Behavior of controller mapped under that URL , while request
	 * mapping parameters are for changing end point itself.
	 * 
	 * Pros : 1. Easy to use and understand . 2. Browser --> Browser and Postman -->
	 * Postman for old to new versions.
	 * 
	 * Cons : 1. Always have to specify parameter with request . Clean URL cannot be
	 * given to anyone . 2. Some what difficult to apply LB rules . 3. More typical
	 * to send moved permanently status codes to discard old version API requests.
	 * 4. Request parameters are not for this purpose . They are being used wrongly
	 * here.
	 * 
	 */
	@GetMapping(value = "/Hello-world", params = { "version=2.0" })
	public String helloWorldVersionedByParamVariable() {
		return "Hello-world-Param-on-request-mapping-level";
	}

	/*
	 * Not much recommended as some routers don't forward non standard headers. URL
	 * will be mapped with this header with URI itself . If header don't come it is
	 * 404 for the server.
	 */
	@GetMapping(value = "/Hello-world", headers = "version=1.0")
	public String helloWorldVersionedByMethodParamVariable() {
		return "Hello-world-header-on-request-mapping-level ";
	}

	/*
	 * 1. Here this URL will be mapped in spring by "Hello-world + Accept
	 * :application/vnd.versioned.helloworld.v1+JSON" itself . If header does not
	 * come then it will not be hit to controller and will be a 404. 2. This header
	 * is request mapping level header which is completely different from method
	 * argument headers . As method argument headers don't change request URL ,
	 * request URL remains same as mapped . Method argument headers are for changing
	 * the code processing behavior of controller mapped under that URL while
	 * request mapping headers are for changing end point itself.
	 * 
	 * cons : 1. Not easy to use and understand . 2. Browser --> Browser and Postman
	 * --> Postman for old to new versions is not possible every thing will be moved
	 * to postman now . Difficult to use and test . 3. Always have to specify
	 * parameter with request , clean URL cannot be given to anyone . 4. Some what
	 * difficult to apply LB rules . 5. More typical to send moved permanently
	 * status codes to discard old version request. 6. Headers are being wrongly
	 * used here .
	 * 
	 * 
	 */
	@GetMapping(value = "/Hello-world", produces = "application/vnd.versioned.helloworld.v1+json")
	public String helloWorldVersionedByVenderProdices() {
		return "Hello-world-vendor-produces-way";
	}

}
