package com.tjf.checkin.survey.exception;

import javax.persistence.NonUniqueResultException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

/**
 * The Class SurveySubmitNonUniqueResultException.
 * 
 * <br>Error occurs in the request, if a survey is not unique.
 * 
 * <br>{@link ApiBadRequest}
 * 
 * @author Diego N. da Silveira
 */
@ApiBadRequest("SurveySubmitNonUniqueResultException")
public class SurveySubmitNonUniqueResultException extends NonUniqueResultException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2588331690625306253L;

}
