package Dao;

import beans.UserEdu;

public interface UserEduDao {
    public int add(UserEdu userEduDao);
    public int update(UserEdu userEduDao);
    public UserEdu queryForInfo(int user_idNum);
    public int init(int id);
}
