package Service;

import Dao.Json;
import Dao.UserFileDao;
import beans.UserFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFileService {

    private UserFileDao userFileDao;

    public UserFileDao getUserFileDao() {
        return userFileDao;
    }

    @Autowired
    public void setUserFileDao(UserFileDao userFileDao) {
        this.userFileDao = userFileDao;
    }

    public boolean addFileInfo(UserFileInfo u){
        return userFileDao.add(u)!=-1;
    }

    public boolean updateFileInfo(UserFileInfo u){
        return userFileDao.update(u)!=-1;
    }

    public String getJsonUserFileInfo(int id){
        List<UserFileInfo>list=userFileDao.queryForAllInfo(id);
        if(list==null||list.size()==0){
            return "";
        }
        int l=list.size();
        Object[][]value=new Object[l][];
        int i=0;
        for(UserFileInfo x:list){
            value[i++]=x.getValueArray();
        }
        String[]field=UserFileInfo.getField();
        return Json.getJson(field,value);
    }

    public String getJsonFileInfoBySort(int sort){
        List<UserFileInfo>list=userFileDao.getFileInfoBySort(sort);
        if(list==null||list.size()==0){
            return "";
        }
        int l=list.size();
        Object[][]value=new Object[l][];
        int i=0;
        for(UserFileInfo x:list){
            value[i++]=x.getValueArray();
        }
        String[]field=UserFileInfo.getField();
        return Json.getJson(field,value);
    }
}
