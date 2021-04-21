package beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class UserEdu {
     private int user_idNum;
     private String school;
     private String time;
     private String background;
     private String major;

    public String getSchool() {
        return school;
    }

    public String getTime() {
        return time;
    }

    public String getBackground() {
        return background;
    }

    public String getMajor() {
        return major;
    }


    public int getUser_idNum() {
        return user_idNum;
    }

    public void setUser_idNum(int user_idNum) {
        this.user_idNum = user_idNum;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Object[] getValueArray(){
        return new Object[]{getUser_idNum(),getSchool(),getMajor()
        ,getTime(),getBackground()};
    }
    public static String[] getField(){
        return new String[]{"user_idNum","school","major","time","background"};
    }

}

