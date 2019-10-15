package com.lambdaschool.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceFoundException extends RuntimeException
{
	//these exceptions will be turned into json so this line is needed
	private static final long serialVersionUID = 1L;

	public ResourceFoundException(String message)
	{
		super(message);
	}

	public ResourceFoundException(String message,
	                              Throwable cause)
	{
		super(message,
				cause);
	}
}