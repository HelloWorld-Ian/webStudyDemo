package Controller;

import Dao.Json;
import Dao.Sql;
import Service.UserBasicService;
import Service.UserEduService;
import Service.UserService;
import beans.UserInfo;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
@RequestMapping(path = "/Login")
@Scope("prototype")
public class LoginAndRegister {
    private DruidDataSource ds;
    private UserService userService;
    private UserBasicService userBasicService;
    private UserEduService userEduService;
    private HttpSession session;
    private HttpServletRequest request;

    public UserBasicService getUserBasicService() {
        return userBasicService;
    }

    public UserEduService getUserEduService() {
        return userEduService;
    }

    @Autowired
    public void setUserBasicService(UserBasicService userBasicService) {
        this.userBasicService = userBasicService;
    }

    @Autowired
    public void setUserEduService(UserEduService userEduService) {
        this.userEduService = userEduService;
    }

    public HttpSession getSession() {
        return session;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Autowired(required = false)
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    @Qualifier("sqlConnect")
    public void setDs(DruidDataSource ds) {
        this.ds = ds;
    }

    public DruidDataSource getDs() {
        return ds;
    }

    public Connection getConnect() {
        try {
            return ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(path = "/login")
    public String LoginCheck(UserInfo userInfo, HttpServletRequest request) {
         UserInfo check=userService.getUserInfo(userInfo.getUser_email());
         if (check==null){
             return "fail";
         }
         String password= check.getPassword();
         if(password.equals(userInfo.getPassword())){
             HttpSession session= request.getSession();
             session.setAttribute("id",check.getId());
             File file=new File(FileOperate.uploadPath+File.separatorChar+check.getId()+"");
             System.out.println(file.getAbsolutePath());
             if(!file.exists()){
                 file.mkdirs();
             }
             return "success";
         }else{
             return "fail";
         }
    }

    @ResponseBody
    @RequestMapping(path = "/register")
    public String register(UserInfo userInfo) {
          if(userService.addUserInfo(userInfo)){
              userInfo=userService.getUserInfo(userInfo.getUser_email());
              int id=userInfo.getId();
              userBasicService.init(id);
              userEduService.init(id);
              return "success";
          }else{
              return "fail";
          }
    }

    @ResponseBody
    @RequestMapping(path = "/getSessionID")
    public String getSessionID() {
        Object obj=session.getAttribute("id");
        if(obj==null){
            return "";
        }else{
            return (int)obj+"";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/getUserBlogTotal")
    public String getUserBlogTotal(){
        int id=(int)session.getAttribute("id");
        int blogTotal=userService.getUserBlogTotal(id);
        String[]field=new String[]{"id","total_blog"};
        Object[]value=new Object[]{id,blogTotal};
        return Json.getJson(field,value);
    }


    @ResponseBody
    @RequestMapping(path = "/updateBlogTotal")
    public void updateBlogTotal(@RequestParam("total_blog")int total_blog,
                                HttpServletRequest request){
        int id=(int)session.getAttribute("id");
        userService.updateBlogTotal(id,total_blog);
    }
}
