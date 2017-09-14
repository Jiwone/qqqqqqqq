package src.com.secretd.web.dao;

import java.util.List;

import src.com.secretd.web.entity.Survey;
import src.com.secretd.web.entity.Symptom;
import src.com.secretd.web.entity.Disease;

public interface SurveyDao {

	List<Survey> getList(String nextNum);

	Survey get(String nextNum);

	List<Symptom> getSymptomList(String nextNum, String ansNum);

	int getNum(int result, String nextNum, String ansNum);

	String getNextNum(String nextNum, String ansNum);

	List<Disease> getDiseaseList(String dis1, String dis2, String dis3, String nNum);

	
	
	
}
