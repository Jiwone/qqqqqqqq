package src.com.secretd.web.dao;

import java.util.List;

import src.com.secretd.web.entity.Survey;
import src.com.secretd.web.entity.Symptom;

public interface SurveyDao {

	List<Survey> getList(String nextNum);

	Survey get(String nextNum);

	List<Symptom> getSymptomList(String nextNum, String ansNum);

	int getNum(int result, String nextNum, String ansNum);

	
	
	
}
