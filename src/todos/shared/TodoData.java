package todos.shared;

import java.io.Serializable;

/**
 * Logic-less class holding data of a to-do item
 * FIXME Make it immutable?
 */
public class TodoData implements Serializable {

    public String name;
    public boolean done;
    
    public TodoData(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public TodoData() {
        name = "";
        done = false;
    }
}
