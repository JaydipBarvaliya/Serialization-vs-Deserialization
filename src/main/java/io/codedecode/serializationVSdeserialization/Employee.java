package io.codedecode.serializationVSdeserialization;

import java.io.Serializable;

public class Employee implements Serializable {

    private int id;

    private String name;

    transient private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
