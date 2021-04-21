package Service;

import Dao.Json;
import Dao.QuestionDao;
import beans.QuestionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private  QuestionDao questionDao;


    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    @Autowired
    public void setQuestionDao(QuestionDao q) {
        this.questionDao = q;
    }

    public boolean add(QuestionBean q){
        return questionDao.add(q)!=-1;
    }

    public String getQuestionJsonBySort(int sort){
        List<QuestionBean>list=questionDao.getQuestionBySort(sort);
        if(list==null||list.size()==0){
            return null;
        }
        String[]field=QuestionBean.getField();
        int l=list.size();
        Object[][]value=new Object[l][];
        int i=0;
        for(QuestionBean x:list){
            value[i++]=x.getValue();
        }
        return Json.getJson(field,value);
    }

    public String getQuestionJsonByUser_id(int  user_id){
        List<QuestionBean>list=questionDao.getQuestionByUser_id(user_id);
        if(list==null||list.size()==0){
            return null;
        }
        String[]field=QuestionBean.getField();
        int l=list.size();
        Object[][]value=new Object[l][];
        int i=0;
        for(QuestionBean x:list){
            value[i++]=x.getValue();
        }
        return Json.getJson(field,value);
    }

}
