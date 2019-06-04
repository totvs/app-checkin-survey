package com.tjf.checkin.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Interface SurveyModelRepository.
 * 
 * @author Diego N. da Silveira
 */
@Repository
@Transactional
public interface SurveyModelRepository extends JpaRepository<SurveyModel, String>{}
