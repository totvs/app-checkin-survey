package com.tjf.checkin.survey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurveyConfiguration {

	@Value("${survey.logurl}")
    public String logUrl;
}
