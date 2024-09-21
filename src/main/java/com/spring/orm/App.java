package com.spring.orm;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean(StudentDao.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean go = true;
        while (go) {
            System.out.println("Press 1 for Add student");
            System.out.println("Press 2 for display all Student");
            System.out.println("Press 3 for get detail of Single Student");
            System.out.println("Press 4 for delete students");
            System.out.println("Press 5 for Update Student");
            System.out.println("Press 6 for Exit");

            try {
                int  input = Integer.parseInt(br.readLine());
                switch (input) {
                    case 1 :
                        System.out.println("Enter Student Name:");
                        String name = br.readLine();
                        System.out.println("Enter Student City:");
                        String city = br.readLine();

                        Student student = new Student();
                        student.setStudentName(name);
                        student.setStudentCity(city);
                        studentDao.saveStudent(student);
                        System.out.println("Student added successfully.");
                        break;
                    case 2 :
                        System.out.println("All Students:");
                        for (Student s : studentDao.getAllStudents()) {
                            System.out.println("ID: " + s.getStudentId() + ", Name: " + s.getStudentName() + ", City: " + s.getStudentCity());
                        }
                        break;
                    case 3:
                        System.out.println("Enter Student ID:");
                        int id = Integer.parseInt(br.readLine());
                        Student foundStudent = studentDao.getStudentById(id);
                        if (foundStudent != null) {
                            System.out.println("ID: " + foundStudent.getStudentId() + ", Name: " + foundStudent.getStudentName() + ", City: " + foundStudent.getStudentCity());
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 4:
                        System.out.println("Enter Student ID to delete:");
                        int deleteId = Integer.parseInt(br.readLine());
                        studentDao.deleteStudent(deleteId);
                        System.out.println("Student deleted successfully.");
                        break;
                    case 5:
                        System.out.println("Enter Student ID to update:");
                        int updateId = Integer.parseInt(br.readLine());
                        Student updateStudent = studentDao.getStudentById(updateId);
                        if (updateStudent != null) {
                            System.out.println("Enter new Name:");
                            updateStudent.setStudentName(br.readLine());
                            System.out.println("Enter new Age:");
                            updateStudent.setStudentCity((br.readLine()));
                            studentDao.updateStudent(updateStudent);
                            System.out.println("Student updated successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 6:
                        go = false;
                        break;
                    default:
                        throw new Exception("Galat Input hai pagal.");
                }
            } catch (Exception e){
                System.out.println("Invalid Input, Try Another one!");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Thanks!");

    }
}
