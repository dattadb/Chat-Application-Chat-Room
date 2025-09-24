package ChatApplication.Response;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import ChatApplication.Model.ResponseMessage;
import ChatApplication.Model.User;

public class SuccesResponse {
	
	
	
	
	private Object object;
	private String message;
	private HttpStatus httpStatus;
	private boolean status;
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public SuccesResponse(Object object, String message, HttpStatus httpStatus, boolean status) {
		super();
		this.object = object;
		this.message = message;
		this.httpStatus = httpStatus;
		this.status = status;
	}
	public SuccesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void plzCheckField() {

		this.object = null;
		this.status = false;
		this.httpStatus = httpStatus.BAD_REQUEST;
		this.message = ResponseMessage.EnterNullData;
		
	}
	public void userNotFound(Optional<User> use) {
		this.object = use;
		this.status = false;
		this.httpStatus = httpStatus.NOT_FOUND;
		this.message = ResponseMessage.userNotFound;
				
	}
	public void UserLoginSuccesfully(User user2) {
		this.object = user2;
		this.status = true;
		this.httpStatus = httpStatus.OK;
		this.message = ResponseMessage.UserLoginSuccesfully;
	}
	public void passwordWrong() {
		this.object = null;
		this.status = false;
		this.httpStatus = httpStatus.BAD_REQUEST;
		this.message = ResponseMessage.passwordWrong;		
	}
	
	

}
