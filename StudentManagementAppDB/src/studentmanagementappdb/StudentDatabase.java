
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementappdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class StudentDatabase {
     public static Connection connect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/filemansys";
        String username="root";
        String password="12345";
        Connection connection=DriverManager.getConnection(url, username, password);
        return connection;
    }
      
     public static List<Student> getAll() {
        List<Student> result=new ArrayList();
        try(Connection con=connect()){
            PreparedStatement stmt=con.prepareStatement("select * from student");
            stmt.execute();
            ResultSet rs=stmt.getResultSet();
        while(rs.next()){
            Integer id=rs.getInt("id");
            String sname=rs.getString("name");
            String ssurname=rs.getString("surname");
            Integer sage=rs.getInt("age");
            result.add(new Student(id,sname,ssurname,sage));
        }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }    
            return result;
    }
    public static List<Student> getAll(String name,String surname,Integer age) {
        if(( name==null || name.isEmpty()) && ( surname==null || name.isEmpty()) && age==null){
            return getAll();
        }
        List<Student> result=new ArrayList();
        try(Connection con=connect()){
           
            StringBuilder query=new StringBuilder("select * from student where name like ? and surname like ?");
             if(age!=null){
                 query.append(" and age=?") ;
             }
            PreparedStatement stmt=con.prepareStatement(query.toString());
            stmt.setString(1, "%"+name+"%");
            stmt.setString(2, "%"+surname+"%");
            if(age!=null){
            stmt.setInt(3, age);
            }
            stmt.execute();
            ResultSet rs=stmt.getResultSet();
        while(rs.next()){
            Integer id=rs.getInt("id");
            String sname=rs.getString("name");
            String ssurname=rs.getString("surname");
            Integer sage=rs.getInt("age");
            result.add(new Student(id,sname,ssurname,sage));
        }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }    
            return result;
    }
    public static boolean update(Student s){
        try(Connection con=connect()){
            StringBuilder queryStr=new StringBuilder("update student set name=name ");
            if(s.getName()!=null && !s.getName().isEmpty()){
               queryStr.append(", name=?"); 
            }
            if(s.getSurname()!=null && !s.getSurname().isEmpty()){
               queryStr.append(", surname=?"); 
            }
            if(s.getAge()!=null){
               queryStr.append(", age=?"); 
            }
            queryStr.append(" where id=?");
            PreparedStatement stmt=con.prepareStatement(queryStr.toString());
            int i=0;
            if(s.getName()!=null && !s.getName().isEmpty()){
              stmt.setString(++i,s.getName());
            }
            if(s.getSurname()!=null && !s.getSurname().isEmpty()){
               stmt.setString(++i,s.getSurname());
            }
            if(s.getAge()!=null){
               stmt.setInt(++i,s.getAge());
            }
            stmt.setInt(++i,s.getId());
            stmt.executeUpdate();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
//    public static void main(String[] args) throws Exception {
//        add(new Student (3,"Dasdemir",null,null));
//    }
    //asagidaki kodlari 1 defe ozum refresh etmisem
    public static int add(Student student) throws Exception{
        try(Connection con=connect()){
            PreparedStatement stmt=con.prepareStatement("insert intos student(id,name,surname,age) values(?,?,?,?)");
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getSurname());
            stmt.setInt(4, student.getAge());
                    
            return stmt.executeUpdate();
        }
    }
    public static int delete(Integer id) throws Exception{
        try(Connection con=connect()) {
           PreparedStatement stmt=con.prepareStatement("delete from student where id=?");
           stmt.setInt(1, id);
           return stmt.executeUpdate();
        }
    }
}
