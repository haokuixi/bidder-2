/**
 * 
 */
package com.bharatmehta.bidder.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bharatmehta.bidder.exception.BidderServiceException;

/**
 * @author bharat.mehta
 *
 */
@ControllerAdvice(assignableTypes = {BidderController.class})
public class BidderControllerAdvice {

	
	@ExceptionHandler(BidderServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Error bidderServiceException(BidderServiceException e){
		Error error = new Error();
		error.setMessage(e.getMessage());
		return error;
		
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Error handleException(Exception e){
		
		Error error = new Error();
		error.setMessage(e.getMessage());
		return error;
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Error[] processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Error [] errors = new Error[fieldErrors.size()];
        int i =0;
        for(FieldError error : fieldErrors){
        	Error e = new Error();
        	e.setMessage(error.toString());
        	errors[i++] = e;
        }
        return errors;
        
    }

}
