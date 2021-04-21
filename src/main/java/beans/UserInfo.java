package beans;


public class UserInfo {
    public int id;
    public String user_email;
    public String password;
    public String total_blog;
    public String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getTotal_blog() {
        return total_blog;
    }

    public int getId() {
        return id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setTotal_blog(String total_blog) {
        this.total_blog = total_blog;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object[]getValueArray(){
        return new Object[]{getId(),getUser_email(),getPassword(),getTotal_blog()};
    }

    public Object[]getValueArrayWithUserId(){
        return new Object[]{getId(),getUser_email(),getPassword(),getTotal_blog(),getUser_id()};
    }

    public static String[]getField(){
        return new String[]{"id","user_email","password","total_blog"};
    }

    public static String[]getFieldWithUserId(){
        return new String[]{"id","user_email","password","total_blog","user_id"};
    }

}
