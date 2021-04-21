package Dao;

import beans.UserFileInfo;

import java.util.List;

public interface UserFileDao {
    public int add(UserFileInfo userFileInfo);
    public int update(UserFileInfo userFileInfo);
    public List<UserFileInfo> queryForAllInfo(int user_id);
    public List<UserFileInfo>  getFileInfoBySort(int sort);
}
