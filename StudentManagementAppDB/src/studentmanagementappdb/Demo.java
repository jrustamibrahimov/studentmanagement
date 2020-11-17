/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementappdb;

import entities.Teacher;
import java.util.List;

/**
 *
 * @author Hp
 */

public class Demo {
    public static void main(String[] args) throws Exception {
//        Teacher t=TeacherJPA.findTeacherById(6);
//        t.setName("Elnur");
//        TeacherJPA.update(t);
//        
//        Teacher t2=new Teacher ();
//       // t2.setId(8);
//        t2.setName("Lale");
//        t2.setSurname("Mammadli");
//        t2.setAge(40);
//        TeacherJPA.add(t2);
//        
       List <Teacher> list=TeacherJPA.getAll(); 
//        
//        TeacherJPA.delete(7);
//       
//        List <Teacher> list2=TeacherJPA.getAll(); 
        
//        System.out.println("t="+t.getName());
//        System.out.println("t2="+t2);
        System.out.println("list="+list);
//        System.out.println("list2="+list2);
        

    }
    
}
