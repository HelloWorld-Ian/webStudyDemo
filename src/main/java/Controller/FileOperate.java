package Controller;

import Dao.Sql;
import Service.BlogService;
import beans.BlogInfo;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(path = "/FileOperate")
public class FileOperate {
    private DruidDataSource sqlConnect;
    private  HttpServletRequest request;
    private BlogService blogService;
//    public static final String uploadPath="D:"+File.separatorChar+"testUpload";
    public static final String uploadPath="userInfo";

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
    public HttpServletRequest getRequest() {
        return request;
    }

    public BlogService getBlogService() {
        return blogService;
    }



    public void setSqlConnect(DruidDataSource sqlConnect) {
        this.sqlConnect = sqlConnect;
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired(required = false)
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @ResponseBody
    @RequestMapping(path = "/UploadFile")
     public String getUploadFile( HttpSession session){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        upload.setFileSizeMax(4194304);
        try {
            List<FileItem>files=upload.parseRequest(request);
            for(FileItem x:files){
                String name=x.getName();
                if(name!=null){
                    File savedFile=new File(uploadPath+File.separatorChar+session.getAttribute("id")+"",name);
                    x.write(savedFile);
                }
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping(path = "/download")
    public void downloadFile(@RequestParam("file_realName")String fileName, HttpServletResponse response, HttpSession session){
        File file=new File(uploadPath+File.separatorChar+session.getAttribute("id")+"",fileName);
        byte[]bytes=new byte[1024];
        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
            OutputStream os=response.getOutputStream();
            int i= bis.read(bytes);
            while(i!=-1){
                os.write(bytes,0,i);
                i=bis.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(path ="/delete")
    public String deleteFile(@RequestParam("file_realName")String fileName,HttpSession session){
        File file=new File(uploadPath+File.separatorChar+session.getAttribute("id")+"",fileName);
        int id=getUser_id();
        String addition="where user_id="+id+" and file_realName=\""+fileName+"\"";
        if(file.delete()&& Sql.delete(getConnect(),"fileinfo",addition )){
            return "success";
        }else{
            return "fail";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/storeBlog")
    public String storeBlog( BlogInfo blogInfo,
                             HttpSession session){
        String path=uploadPath+File.separatorChar+session.getAttribute("id")+""+File.separatorChar+blogInfo.getTitle();
        if(blogService.storeBlogHtml(blogInfo.getText(),path)&&blogService.addBlogInfo(blogInfo)){
            return "success";
        }else{
            return "fail";
        }
    }

    public int getUser_id(){
        HttpSession session=request.getSession();
        return (int)session.getAttribute("id");
    }
}
