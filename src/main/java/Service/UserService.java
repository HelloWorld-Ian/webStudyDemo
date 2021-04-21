package Service;

import Dao.Json;
import Dao.UserBasicDao;
import Dao.UserDaoImpl;
import beans.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserDaoImpl userDao;
    UserBasicDao userBasicDao;

    public UserBasicDao getUserBasicDao() {
        return userBasicDao;
    }

    @Autowired
    public void setUserBasicDao(UserBasicDao userBasicDao) {
        this.userBasicDao = userBasicDao;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public int getUserBlogTotal(int id){
          return userDao.getUserBlogTotal(id);
    }

    public UserInfo getUserInfo(String user_email){
          return userDao.getUserInfo(user_email);
    }

    public String getJsonInfo(int id){
        UserInfo u= userDao.getUserInfo(id);
        String[]field=UserInfo.getField();
        Object[]value=u.getValueArray();
        return Json.getJson(field,value);
    }

    public void updateBlogTotal(int id, int total_blog){
        userDao.updateBlogTotal(id, total_blog);
    }

    public boolean addUserInfo(UserInfo u){
        return userDao.add(u)!=-1;
    }

    public boolean update(UserInfo u){
        return userDao.update(u)!=-1;
    }

    public String getUserInfoWithUserId(int id){
        UserInfo u=userDao.getUserInfo(id);
        String user_id=userBasicDao.getUserId(id);
        u.setUser_id(user_id);
        String[]field=UserInfo.getFieldWithUserId();
        Object[]value=u.getValueArrayWithUserId();
        return Json.getJson(field,value);
    }
}
