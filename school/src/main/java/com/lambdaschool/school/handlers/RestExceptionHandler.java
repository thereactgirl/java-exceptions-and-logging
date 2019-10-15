package com.lambdaschool.school.handlers;

import com.lambdaschool.school.exceptions.ResourceFoundException;
import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.ErrorDetail;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//this file allows us to override the spring default exceptions
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
	public RestExceptionHandler()
	{
		//call the top constructor
		super();
	}

	@ExceptionHandler({ResourceNotFoundException.class, EntityNotFoundException.class})
	public ResponseEntity<?> handleResourceNotFoundException(Exception rnfe,
	                                                         HttpServletRequest request)
	{
		ErrorDetail errorDetail = new ErrorDetail();

		errorDetail.setTimestamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle("Resource Not Found");
		errorDetail.setDetail(rnfe.getMessage());
		errorDetail.setDevelopermessage(rnfe.getClass().getName());

		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ResourceFoundException.class})
	public ResponseEntity<?> handleResourceFoundException(Exception rnfe,
	                                                      HttpServletRequest request)
	{
		ErrorDetail errorDetail = new ErrorDetail();

		errorDetail.setTimestamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDetail.setTitle("Resource already exist");
		errorDetail.setDetail(rnfe.getMessage());
		errorDetail.setDevelopermessage(rnfe.getClass().getName());

		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{

		ErrorDetail errorDetail = new ErrorDetail();

		errorDetail.setTimestamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDetail.setTitle(ex.getPropertyName() + " Parameter Type Mismatch. Please use a valid ID type of long");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDevelopermessage(request.getDescription(true));

		return new ResponseEntity<>(errorDetail, headers, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
			ErrorDetail errorDetail = new ErrorDetail();

		errorDetail.setTimestamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle(ex.getRequestURL());
		errorDetail.setDetail(request.getDescription(true));
		errorDetail.setDevelopermessage("Rest Handler Not Fount (check for valid URI)");

		return new ResponseEntity<>(errorDetail, headers, HttpStatus.NOT_FOUND);
	}



}
