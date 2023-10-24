package org.example.comics;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    protected boolean administrator;
    protected String name;

    public User() {
        this.administrator = false;
        this.name = "User";
    }

    public User(String name) {
        this.name = name;
        this.administrator = false;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
