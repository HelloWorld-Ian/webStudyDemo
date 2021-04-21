package Dao;

import beans.QuestionBean;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface QuestionDao {
    public int add(QuestionBean q);
    public int update(QuestionBean q);
    public List<QuestionBean> getQuestionBySort(int sort);
    public List<QuestionBean> getQuestionByUser_id(int user_id);
}
