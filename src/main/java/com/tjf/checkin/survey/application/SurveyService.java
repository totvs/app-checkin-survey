package com.tjf.checkin.survey.application;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjf.checkin.survey.exception.SurveySubmitNonUniqueResultException;
import com.tjf.checkin.survey.repository.SurveyModel;
import com.tjf.checkin.survey.repository.SurveyModelRepository;

/**
 * The Class SurveyService responsible for performing the validations and adding the surveys
 * 
 * @author Diego N. da Silveira
 */
@Service
@Transactional
public class SurveyService {

	/** 
	 * Entity Manager
	 * 
	 * @see EntityManager
	 */
	@PersistenceContext
	private EntityManager em;

	/** 
	 * Repository 
	 * 
	 * @see SurveyModelRepository
	 */
	@Autowired
	private SurveyModelRepository repo;

	/**
	 * Add survey if it's unique
	 *
	 * @param dto Content of the RequestBody
	 * @throws SurveySubmitNonUniqueResultException if survey already exists
	 */
	public void addSurvey(SurveyModel dto) {
		if (!existsSurveyByEmailAndEvent(dto.getEmail(), dto.getEvent())) {
			repo.saveAndFlush(dto);
		} else {
			throw new SurveySubmitNonUniqueResultException(); 
		}

	}

	/**
	 * Class responsible for verifying that a survey response with this email already exists for the same event.
	 *
	 * @param email the email
	 * @param event the event
	 * @return false, if it's unique
	 * @return true, if it exists.
	 */
	boolean existsSurveyByEmailAndEvent(String email, String event) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);;
		query.select(criteriaBuilder.literal(true));

		Root<SurveyModel> survey = query.from(SurveyModel.class);

		Predicate emailNamePredicate = criteriaBuilder.equal(survey.get("email"), email);
		Predicate eventPredicate = criteriaBuilder.equal(survey.get("event"), event);

		query.where(emailNamePredicate, eventPredicate);

		TypedQuery<Boolean> typedQuery = em.createQuery(query);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException exception) {
			return false;
		} catch (NonUniqueResultException exception) {
			return true;
		}
	}

}
