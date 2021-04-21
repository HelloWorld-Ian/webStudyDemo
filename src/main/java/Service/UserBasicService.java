package Service;

import Dao.Json;
import Dao.UserBasicDao;
import beans.UserBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBasicService {

    private UserBasicDao userBasicDao;

    public UserBasicDao getUserBasicDao() {
        return userBasicDao;
    }

    @Autowired
    public void setUserBasicDao(UserBasicDao u) {
        this.userBasicDao = u;
    }

    public boolean addBasicInfo(UserBasic u){
        return userBasicDao.add(u)!=-1;
    }

    public boolean updateBasicInfo(UserBasic u){
        return userBasicDao.update(u)!=-1;
    }

    public String getJsonInfo(int user_idNum){
         UserBasic u=userBasicDao.queryForInfo(user_idNum);
         if(u==null){
             return null;
         }
         String[]field=UserBasic.getField();
         Object[]value=u.getValueArray();
         return Json.getJson(field,value);
    }

    public boolean init(int id){
        return userBasicDao.init(id)!=-1;
    }
}
