package io.codedecode.serializationVSdeserialization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class SerializationVsDeserializationApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SpringApplication.run(SerializationVsDeserializationApplication.class, args);

        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("John");


        //Serialization
        FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(emp);
        objectOutputStream.close();
        fileOutputStream.close();


        //Deserialization
        FileInputStream fileInputStream = new FileInputStream("file.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Employee employee = (Employee) objectInputStream.readObject();

        System.out.println(employee.getName());
        System.out.println(employee.getId());

        //This field is transient, so it was ignored while serializing the object, so we are getting null here.
        System.out.println(employee.getPassword());

        fileInputStream.close();
        objectInputStream.close();




        EmployeeExternal employeeExternal = new EmployeeExternal();
        employeeExternal.setId(1);
        employeeExternal.setName("External John");


        //Externalization
        FileOutputStream externalFileOutputStream = new FileOutputStream("externalFile.txt");
        ObjectOutputStream externalObjectOutputStream = new ObjectOutputStream(externalFileOutputStream);
        externalObjectOutputStream.writeObject(employeeExternal);
        externalObjectOutputStream.close();
        externalFileOutputStream.close();


        FileInputStream externalFileInputStream = new FileInputStream("externalFile.txt");
        ObjectInputStream externalObjectInputStream = new ObjectInputStream(externalFileInputStream);
        EmployeeExternal externalEMP = (EmployeeExternal) externalObjectInputStream.readObject();

        System.out.println("External Name :: " + externalEMP.getName());
        System.out.println("External Id   :: " + externalEMP.getId());

        //This field is not transient, BUT it has not been set by the writeObject method in EmployeeExternal Entity class .
        System.out.println("EXTERNAL Password :: " + externalEMP.getPassword());

        externalFileInputStream.close();
        externalObjectInputStream.close();
    }
}
