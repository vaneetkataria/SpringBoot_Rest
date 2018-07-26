package com.kataria.springboot.rest.practice.web.user;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kataria.springboot.rest.practice.core.beans.RestResponse;
import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;
import com.kataria.springboot.rest.practice.web.user.helper.HttpResponsePrepratorUserResourceAccessor;
import com.kataria.springboot.rest.practice.web.user.helper.resource.support.AddressResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "User Resources" })
@RestController
@Validated
public class UserResourceService {

	@Autowired
	private HttpResponsePrepratorUserResourceAccessor userResourceAccessor;

	@ApiOperation(value = "API to fetch all Users.", notes = "This API fetches all existing users. ",
			produces = "application/json , application/xml")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = UserList.class, message = "If atleast one user is found."),
			@ApiResponse(code = 404, response = void.class,
					message = "If no user found . Response body will be empty."),
			@ApiResponse(code = 500, response = RestResponse.class, message = "In case of some server side error.") })
	@GetMapping(path = "/users")
	public ResponseEntity<Resource<UserList>> getAllUsers() throws UserResourceException {
		try {
			return userResourceAccessor.getAllUsers();
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@ApiOperation(value = "API to fetch a Single User.",
			notes = "This API fetches single user with userID as a path variable in API end point. It returns HTTP status codes as described below in response section . In some other failure cases it will return standard HTTP codes. ",
			produces = "application/json , application/xml", code = 302)
	@ApiResponses(value = { @ApiResponse(code = 200, response = User.class, message = "If user is found."),
			@ApiResponse(code = 404, response = void.class,
					message = "If no user found . Response body will be empty."),
			@ApiResponse(code = 500, response = RestResponse.class, message = "In case of some server side error.") })

	@GetMapping(path = "/users/{userId}")
	public ResponseEntity<Resource<User>> getUser(@ApiParam(value = "UserID a numeric value greater than zero only .",
			required = true, example = "5", allowableValues = "range[1, 5]") @PathVariable @Positive(
					message = "UserID must be positive.") Integer userId)
			throws UserResourceException {
		try {
			return userResourceAccessor.getUser(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@ApiOperation(value = "API to fetch corressponding address of the user.",
			notes = "This API provides corressponding address of the user.",
			produces = "application/json , application/xml", code = 302)
	@ApiResponses({
			@ApiResponse(code = 200, message = "If Address exists for user", responseContainer = "List",
					response = AddressResource.class),
			@ApiResponse(code = 404, message = "If User or Address does not exist.", response = void.class),
			@ApiResponse(code = 500, message = "In case of some internal server error", response = void.class) })

	@GetMapping(path = "/users/{userId}/corrAddresses")
	public ResponseEntity<List<AddressResource>> getAllCorresspondingAddress(@ApiParam(value = "UserID of user",
			required = true, example = "5", allowableValues = "range[1, 5]") @PathVariable @Positive(
					message = "UserID must be positive.") Integer userId)
			throws UserResourceException {
		try {
			return userResourceAccessor.getAllCorresspondingAddress(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@ApiOperation(value = "API to add a Single User.",
			notes = "This API adds  a user . It returns HTTP status codes as described below in response section . In some other failure cases it will return standard HTTP codes. ",
			produces = "application/json , application/xml", code = 302,
			consumes = "application/json , application/xml")

	@ApiResponses(value = { @ApiResponse(code = 301, response = User.class, message = "If User is successfully added."),
			@ApiResponse(code = 500, response = RestResponse.class, message = "In case of some server side error."),
			@ApiResponse(code = 400, response = RestResponse.class,
					message = "In some issue in request parameters happen.") })

	@PostMapping(path = "/users")
	public ResponseEntity<Resource<User>> addUser(@RequestBody @Valid User user) throws UserResourceException {
		try {
			return userResourceAccessor.addUser(user);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@ApiOperation(value = "API to delete a Single User.",
			notes = "This API deletess a user . It returns HTTP status codes as described below in response section . In some other failure cases it will return standard HTTP codes. ",
			produces = "application/json , application/xml")

	@ApiResponses(value = {
			@ApiResponse(code = 200, response = User.class, message = "If User is successfully deleted ."),
			@ApiResponse(code = 500, response = RestResponse.class, message = "In case of some server side error."),
			@ApiResponse(code = 400, response = RestResponse.class,
					message = "In some issue in request parameters happen."),
			@ApiResponse(code = 404, response = RestResponse.class, message = "In user does not exists.") })

	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<Resource<User>> deleteUser(
			@ApiParam(value = "User ID to be deleted . It must be a numeric value greater than zero", required = true,
					example = "1", allowableValues = "range[1, infinity]") @PathVariable @Positive(
							message = "UserID must be positive.") Integer userId)
			throws UserResourceException {
		try {
			return userResourceAccessor.deleteUser(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

}
