package com.kataria.springboot.rest.practice.core.beans;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kataria.springboot.rest.practice.core.constants.CoreConstants;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private RestResponseStatus responseStatus;

	@JsonIgnore
	public void init() {
		if (null == responseStatus)
			responseStatus = new RestResponseStatus();
	}

	public RestResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(RestResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	@JsonIgnore
	public RestResponse setSuccess() {
		init();
		responseStatus.setStatus(CoreConstants.SUCCESS);
		return this;
	}

	@JsonIgnore
	public RestResponse setHttpStatus(HttpStatus httpStatus) {
		init();
		responseStatus.setHttpStatus(httpStatus.value() + " " + httpStatus.name());
		return this;
	}

	@JsonIgnore
	public RestResponse setResponseCode(String responseCode) {
		init();
		responseStatus.setResponseCode(responseCode);
		return this;
	}

	@JsonIgnore
	public RestResponse setResponseMessage(String responseMessage) {
		init();
		responseStatus.setResponseMessage(responseMessage);
		return this;
	}

	@Override
	public String toString() {
		return String.format("RestResponse [responseStatus=%s]", responseStatus);
	}

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private static class RestResponseStatus {
		private String status;
		private String httpStatus;
		private String responseCode;
		private String responseMessage;
		private Date timeStamp;

		public RestResponseStatus() {
			super();
			timeStamp = new Date();
		}

		public RestResponseStatus(String status, String httpStatus, String responseCode, String responseMessage,
				Date timeStamp) {
			super();
			this.status = status;
			this.httpStatus = httpStatus;
			this.responseCode = responseCode;
			this.responseMessage = responseMessage;
			this.timeStamp = timeStamp;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getHttpStatus() {
			return httpStatus;
		}

		public void setHttpStatus(String httpStatus) {
			this.httpStatus = httpStatus;
		}

		public String getResponseCode() {
			return responseCode;
		}

		public void setResponseCode(String responseCode) {
			this.responseCode = responseCode;
		}

		public String getResponseMessage() {
			return responseMessage;
		}

		public void setResponseMessage(String responseMessage) {
			this.responseMessage = responseMessage;
		}

		public Date getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(Date timeStamp) {
			this.timeStamp = timeStamp;
		}

		@Override
		public String toString() {
			return String.format(
					"RestResponseStatus [status=%s, httpStatus=%s, responseCode=%s, responseMessage=%s, timeStamp=%s]",
					status, httpStatus, responseCode, responseMessage, timeStamp);
		}

	}

}
