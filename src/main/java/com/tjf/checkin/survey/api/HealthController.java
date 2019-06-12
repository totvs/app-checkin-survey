package com.tjf.checkin.survey.api;

import static com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion.v1;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tjf.checkin.survey.SurveyConfiguration;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;

@RestController
@RequestMapping(path = "/health_check", produces = { APPLICATION_JSON_VALUE })
@ApiGuideline(v1)
public class HealthController {

	/**
	 * Config Parameter
	 */
	@Autowired
	private SurveyConfiguration surveyConfiguration;

	/**
	 * Health Check.
	 *
	 * @return status OK
	 */
	@GetMapping
	@ResponseStatus(OK)
	public String healthCheck() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		if (surveyConfiguration.logUrl.equalsIgnoreCase("true")) {
			System.out.println(request.getRequestURL().toString());
		}

		return "{\"status\": \"ok\"}";
	}

}
