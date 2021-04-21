package Service;

import Dao.Json;
import Dao.UserEduDao;
import beans.UserEdu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEduService {

    private UserEduDao userEduDao;

    public UserEduDao getUserEduDao() {
        return userEduDao;
    }

    @Autowired
    public void setUserEduDao(UserEduDao userEduDao) {
        this.userEduDao = userEduDao;
    }

    public boolean addEduInfo(UserEdu u){
        return userEduDao.add(u)!=-1;
    }

    public boolean updateEduInfo(UserEdu u){
        return userEduDao.update(u)!=-1;
    }

    public String getJsonInfo(int id){
        UserEdu u=userEduDao.queryForInfo(id);
        if(u==null){
            return null;
        }
        String[]filed=UserEdu.getField();
        Object[]value=u.getValueArray();
        return Json.getJson(filed,value);
    }

    public boolean init(int id){
        return userEduDao.init(id)!=-1;
    }
}
