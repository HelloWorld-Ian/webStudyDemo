package Dao;

import beans.UserInfo;

public interface UserDao {
    public int getUserBlogTotal(int id);
    public void updateBlogTotal(int id, int total);
    public UserInfo getUserInfo(String email);
    public int add(UserInfo userInfo);
    public int update(UserInfo userInfo);

}
