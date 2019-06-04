package com.tjf.checkin.survey.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tjf.checkin.survey.application.SurveyService;
import com.tjf.checkin.survey.exception.SurveySubmitConstraintException;
import com.tjf.checkin.survey.repository.SurveyModel;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion;
import com.totvs.tjf.core.validation.ValidatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * This controller is responsible for mapping the application's REST endpoints.
 * 
 * @author Diego N. da Silveira
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/survey", produces = { APPLICATION_JSON_VALUE })
@ApiGuideline(ApiGuidelineVersion.v1)
public class SurveyController {
        
    /** 
     * Exception Validator.
     * 
     * {@link ValidatorService}}
     */
    @Autowired
    private ValidatorService validator;

    /** 
     * Service
     * 
     * @see SurveyService
     */
    @Autowired
    private SurveyService service;
    
    /**
     * Submit survey.
     *
     * @param dto Content of the RequestBody
     * @throws SurveySubmitConstraintException If there is any violation 
     */
    @ApiOperation(value = "Submit survey.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
    @ApiResponse(code = 201, message = "{SurveyController.submit.Survey}")
    @ResponseStatus(CREATED)
    @PostMapping(path = "/", consumes = { APPLICATION_JSON_VALUE })
    public void submitSurvey(@RequestBody SurveyModel dto) {
    	
    	validator.validate(dto).ifPresent( violations -> { 
    		throw new SurveySubmitConstraintException(violations); 
		});

    	this.service.addSurvey(dto);
    }
}
