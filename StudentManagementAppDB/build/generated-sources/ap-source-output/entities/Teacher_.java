package entities;

import entities.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-04T00:39:10")
@StaticMetamodel(Teacher.class)
public class Teacher_ { 

    public static volatile SingularAttribute<Teacher, String> surname;
    public static volatile SingularAttribute<Teacher, String> name;
    public static volatile ListAttribute<Teacher, Student> studentList;
    public static volatile SingularAttribute<Teacher, Integer> id;
    public static volatile SingularAttribute<Teacher, Integer> age;

}