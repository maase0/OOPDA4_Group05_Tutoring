/**
 *
 * @author Erich Maas
 * @author Angela Gaudio
 * @author Brannon Perna
 * @author Tyler Roman
 */

import java.io.Serializable;

public class Student implements Serializable {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
