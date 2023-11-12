package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User implements Serializable {
    protected boolean administrator;
    protected String name;
    protected HashMap<Comics, Integer> deferredComics;
    public User() {
        this.administrator = false;
        this.name = "User";
        this.deferredComics = new HashMap<>();
    }

    public User(String name) {
        this.name = name;
        this.administrator = false;
        this.deferredComics = new HashMap<>();
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    public HashMap<Comics, Integer> getDeferredComics() {
        return deferredComics;
    }

    public void setDeferredComics(HashMap<Comics, Integer> deferredComics) {
        this.deferredComics = deferredComics;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder defferComics = new StringBuilder();
        for (Map.Entry<Comics, Integer> entry : this.deferredComics.entrySet()){
            defferComics.append(entry.getKey().getName()).append(" - ").append(entry.getValue()).append("шт, ");
        }
        return String.format("%20s (%s)", this.name, defferComics);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
