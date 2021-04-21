package Controller;

import Dao.*;
import Service.*;
import beans.*;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(path = "/Info")
@Scope("prototype")
public class StoreInfo {
    private DruidDataSource sqlConnect;
    private HttpServletRequest request;
    private BlogService blogService;
    private UserEduService userEduService;
    private UserBasicService userBasicService;
    private UserService userService;
    private UserFileService userFileService;
    private QuestionService questionService;

    public QuestionService getQuestionService() {
        return questionService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public UserFileService getUserFileService() {
        return userFileService;
    }

    @Autowired
    public void setUserFileService(UserFileService userFileService) {
        this.userFileService = userFileService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserEduService getUserEduService() {
        return userEduService;
    }

    @Autowired
    public void setUserEduService(UserEduService userEduService) {
        this.userEduService = userEduService;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public BlogService getBlogService() {
        return blogService;
    }

    public UserBasicService getUserBasicService() {
        return userBasicService;
    }

    @Autowired
    public void setUserBasicService(UserBasicService userBasicService) {
        this.userBasicService = userBasicService;
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    public void setSqlConnect(DruidDataSource sqlConnect) {
        this.sqlConnect = sqlConnect;
    }

    @Autowired(required=false)
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    @Qualifier("sqlConnect")
    public void DruidDataSource(DruidDataSource sqlConnect) {
        this.sqlConnect = sqlConnect;
    }

    public Connection getConnect(){
        try {
            return sqlConnect.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public DruidDataSource getSqlConnect() {
        return sqlConnect;
    }

    public int getUser_id(){
        HttpSession session=request.getSession();
        return (int)session.getAttribute("id");
    }
    @ResponseBody
    @RequestMapping(path = "/getBasic",method = RequestMethod.GET,produces ="application/html;charset=utf-8")
    public String getBasic(){
        int id=getUser_id();
        return userBasicService.getJsonInfo(id);
    }

    @ResponseBody
    @RequestMapping(path = "/Basic", method = RequestMethod.POST)
    public String storeBasic(UserBasic userBasic) {
        int id=getUser_id();
        userBasic.setUser_idNum(id);
        if(userBasicService.updateBasicInfo(userBasic)){
            return "success";
        }else{
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/getEdu",method = RequestMethod.GET,produces ="application/html;charset=utf-8")
    public String getEdu(){
       int id=getUser_id();
       return userEduService.getJsonInfo(id);
    }

    @ResponseBody
    @RequestMapping(path = "/Edu",method = RequestMethod.POST)
    public String storeEdu(UserEdu userEdu){
        int id=getUser_id();
        userEdu.setUser_idNum(id);
        if(userEduService.updateEduInfo(userEdu)){
            return "success";
        }else{
            return "";
        }
    }
    @ResponseBody
    @RequestMapping(path = "/getPassword",method = RequestMethod.GET,produces ="application/html;charset=utf-8")
    public String getPassWord(){
        int id=getUser_id();
        return userService.getJsonInfo(id);
    }

    @ResponseBody
    @RequestMapping(path = "/PassWord",method = RequestMethod.POST)
    public String storePassword(UserInfo userInfo){
        if(userService.update(userInfo)){
            return "success";
        }else{
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/FileInfo",method = RequestMethod.POST)
    public String storeFileInfo(UserFileInfo fileInfo){
        int id=getUser_id();
        fileInfo.setUser_id(id);
        if(userFileService.addFileInfo(fileInfo)){
            return "success";
        }else{
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/getFileInfo",method = RequestMethod.GET,produces ="application/html;charset=utf-8")
    public String getFileInfo(){
         int id=getUser_id();
         return userFileService.getJsonUserFileInfo(id);
    }

    @ResponseBody
    @RequestMapping(path = "/getFileInfoBySort",produces ="application/html;charset=utf-8")
    public String getFileInfoBySort(@RequestParam("sort")int sort){
        return userFileService.getJsonFileInfoBySort(sort);
    }

    @ResponseBody
    @RequestMapping(path = "/deleteFileInfo",method = RequestMethod.POST)
    public String deleteFileInfo(@RequestParam("file_realName")String file_realName){
        int id=getUser_id();
        String addition="where user_id="+id+" and file_realName=\""+file_realName+"\"";
        if(Sql.delete(getConnect(),"fileinfo",addition )){
            return "success";
        }else{
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/getBlogInfo",produces ="application/html;charset=utf-8")
    public String getBlogInfo(){
         int id=getUser_id();
         return blogService.getUserJsonBlogInfo(id);
    }

    @ResponseBody
    @RequestMapping(path = "/getBlogInfoWithText",produces ="application/html;charset=utf-8")
    public String getBlogInfoWithText(@RequestParam("blog_id")int blog_id,
                                      @RequestParam("title") String title){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int id=getUser_id();
        String path=FileOperate.uploadPath+File.separatorChar+id+""+File.separatorChar+title;
        return blogService.getUserJsonBlogInfoWithText(id,blog_id,path);
    }

    @ResponseBody
    @RequestMapping(path = "/getBlogInfoBySort",produces ="application/html;charset=utf-8")
    public String getBlogInfoBySort(@RequestParam("sort")int sort){
        return blogService.getJsonBlogInfoWithTextBySort(sort);
    }

    @ResponseBody
    @RequestMapping(path = "/getUserInfoWithUserId",produces ="application/html;charset=utf-8")
    public String getUserInfoWithUserId(){
        int id=getUser_id();
        return userService.getUserInfoWithUserId(id);
    }

    @ResponseBody
    @RequestMapping(path="/storeQuestion")
    public String storeQuestion(QuestionBean q){
        int id=getUser_id();
        q.setUser_idNum(id);
        System.out.println(q.getTitle());
        if(questionService.add(q)){
            return "success";
        }else{
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/getQuestionByUser_id",produces ="application/html;charset=utf-8" )
    public String getQuestionByUser_id(){
        int id=getUser_id();
        return questionService.getQuestionJsonByUser_id(id);
    }

    @ResponseBody
    @RequestMapping(path = "/getQuestionBySort",produces ="application/html;charset=utf-8"  )
    public String getQuestionBySort(@RequestParam("sort")int sort){
        return questionService.getQuestionJsonBySort(sort);
    }
}
