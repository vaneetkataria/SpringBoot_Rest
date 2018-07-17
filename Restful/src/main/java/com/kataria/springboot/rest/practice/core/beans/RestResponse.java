package com.kataria.springboot.rest.practice.core.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private RestResponseStatus responseStatus;

	@JsonIgnore
	public void init() {
		if (null == responseStatus)
			responseStatus = new RestResponseStatus();
	}

	@JsonIgnore
	public RestResponse setMessage(String responseMessage) {
		init();
		responseStatus.setMessage(responseMessage);
		return this;
	}

	@JsonIgnore
	public RestResponse setPath(String path) {
		init();
		responseStatus.setPath(path);
		return this;
	}

	public RestResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(RestResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public void setSuccess() {
		responseStatus = new RestResponseStatus();
		responseStatus.setMessage("Successful");
	}

	@Override
	public String toString() {
		return String.format("RestResponse [responseStatus=%s]", responseStatus);
	}

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private static class RestResponseStatus {
		private String message;
		private String path;
		private Date timeStamp;

		public RestResponseStatus() {
			super();
			timeStamp = new Date();
		}

		public RestResponseStatus(String status, String message, Date timeStamp) {
			super();
			this.message = message;
			this.timeStamp = timeStamp;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(Date timeStamp) {
			this.timeStamp = timeStamp;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		@Override
		public String toString() {
			return String.format("RestResponseStatus [message=%s, path=%s, timeStamp=%s]", message, path, timeStamp);
		}

	}

}
