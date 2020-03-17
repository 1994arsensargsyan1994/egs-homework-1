package homework2;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID =  -5052837839287690996L;
    private String name;
    private int ege;

    public User(String name, int ege) {
        this.name = name;
        this.ege = ege;
    }

    public String getName() {
        return name;
    }

    public int getEge() {
        return ege;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ege=" + ege +
                '}';
    }

}
