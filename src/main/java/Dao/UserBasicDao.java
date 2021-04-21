package Dao;

import beans.UserBasic;
import beans.UserEdu;

public interface UserBasicDao {
    public int add(UserBasic u);
    public int update(UserBasic u);
    public UserBasic queryForInfo(int user_idNum);
    public String getUserId(int id);
    public int init(int id);
}
