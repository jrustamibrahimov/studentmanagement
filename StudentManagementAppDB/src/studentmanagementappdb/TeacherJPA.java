
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementappdb;

import entities.Teacher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Hp
 */
public class TeacherJPA {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentManagementAppDBPU");

    public static Teacher findTeacherById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        // User user=em.find(User.class,id); //Bu JPQL query-sine uygun metoddur, asagida query=nin ozu verilib.
        Query query=em.createQuery("select t from Teacher t where t.id=:id");//JPQL(Java Persistence Query Language) query-si
        query.setParameter("id", id);//yuxaridaki parametri sorgudaki "id"-ye teyin edir.
        List<Teacher> list=query.getResultList();//Query-deki ':'-nin menasi id-ni value deyil parametr olaraq gostermesidir.
        //Teacher t= (Teacher)query.getSingleResult();Eger id uygun gelmirse exception ata biler.
        em.close();
        if (list.size()>0){
            return list.get(0);
        }
            return null;
    }

    public static List<Teacher> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query=em.createQuery("select t from Teacher t");
        List<Teacher> list=query.getResultList();
        em.close();
        return list;    
    }

    public static List<Teacher> getAll(String name, String surname, Integer age) {
        if ((name == null || name.isEmpty()) && (surname == null || name.isEmpty()) && age == null) {
            return getAll();
        }
        return null;
    }

    public static boolean update(Teacher t) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Teacher teacher=em.merge(t);
        em.getTransaction().commit();
        em.close();
        return true; 
        
    }

    //asagidaki kodlari 1 defe ozum refresh etmisem
    public static int add(Teacher t )throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
        return t.getId();
    }

    public static int delete(Integer id) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Teacher t=em.find(Teacher.class, id);//deyile biler ki bes burdaki find metodu neye lazimdir,yuxaridaki 
        //findTeacherById(int id) metodundan istifade ede bilerdik,amma onu deyim ki,yuxaridaki metod burada islene bilmez,
        //cunki yuxari metoddaki close() metodu proses sonunda connection-i "close" edecek ve sonraki merhele(remove()) ile 
        //elaqe qirilacaq.Bele proseslerde proses evveli connection qurulur,proses sonunda ise close() metodu ile connection qirilir.        
        em.remove(t);
        em.getTransaction().commit();
        em.close();
        return t.getId();
    }
}
