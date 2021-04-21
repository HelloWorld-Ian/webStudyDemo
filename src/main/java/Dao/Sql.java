package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sql {
    public static List<Object[]> Select(Connection connect, String[]field, String table,String addition){
        if(addition==null){
            addition="";
        }
        List<Object[]>result=new ArrayList<>();
        int len=field==null?0:field.length;
        StringBuilder sql=new StringBuilder();
        if(len==0){
            sql.append("select * from").append(table);
        }else{
            sql.append("select ");
            for(String x:field){
                sql.append(x).append(", ");
            }
            sql.delete(sql.length()-2,sql.length()).append(" ");
            sql.append("from").append(" ").append(table).
                    append(" ").append(addition).append(" ").append(";");
        }
        String Sql=sql.toString();
        PreparedStatement statement;
        try {
           statement=connect.prepareStatement(Sql);
           ResultSet res= statement.executeQuery();
           while(res.next()){
               Object[]str= new Object[len];
               for(int i=0;i<len;i++){
                   str[i]=res.getObject(i+1);
               }
               result.add(str);
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return result;
    }
    public static boolean Insert(Connection connect,String[]field,Object[]values,String table){
          StringBuilder sql=new StringBuilder();
          sql.append("insert into ").append(table).append("( ");
          for (String s : field) {
            sql.append(s).append(",");
          }
          sql.delete(sql.length()-1,sql.length()).append(")values(");
          PreparedStatement statement;
         try {
            for(Object s:values){
              if(s!=null) {
                  sql.append("?").append(",");
              }else{
                  sql.append("null").append(",");
              }
          }
            sql.delete(sql.length()-1,sql.length()).append(')');
            statement = connect.prepareStatement(sql.toString());
            int index=1;
             for(Object s:values){
                 if(s!=null) {
                    statement.setObject(index++,s);
                 }
             }
          sql.delete(sql.length()-1,sql.length()).append(")");

            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean Update(Connection connect,String[]field,Object[]values,String table,String addition){
        StringBuilder sql=new StringBuilder("update "+table+" set ");
        int len=field.length;
        if(len!= values.length||addition==null||addition.equals("")){
            return false;
        }
        for (String s : field) {
            sql.append(s).append(" = ").append("?")
                    .append(",");
        }
        sql.delete(sql.length()-1,sql.length()).append(" ");
        sql.append(addition);
        System.out.println(sql);
        try {
            PreparedStatement statement= connect.prepareStatement(sql.toString());
            int index=1;
            for(Object s:values){
                statement.setObject(index++,s);
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean delete(Connection connect,String tableName,String addition){
        StringBuilder sql=new StringBuilder("delete from "+tableName+" ");
        if(addition!=null&&!addition.equals("")) {
            sql.append(addition);
        }
        try {
            System.out.println(sql);
            PreparedStatement statement= connect.prepareStatement(sql.toString());
            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static int Count(Connection connect,String tableName,String addition){
         return (int)Sql.Select(connect,new String[]{"count(*)"},tableName,addition).get(0)[0];
    }

}
