package com.tjf.checkin.survey.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

/**
 * The Class SurveySubmitConstraintException.
 * 
 * <br>An error occurs in the request, if there is any violation.
 * 
 * <br>{@link ApiBadRequest}
 * 
 * @author Diego N. da Silveira
 */
@ApiBadRequest("SurveySubmitConstraintException")
public class SurveySubmitConstraintException extends ConstraintViolationException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2071517040052831080L;
	
	/**
	 * Instantiates a new survey submit constraint exception.
	 *
	 * @param constraintViolations the constraint violations
	 */
	public SurveySubmitConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}

}