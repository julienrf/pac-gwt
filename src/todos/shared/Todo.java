package todos.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Todo implements IsSerializable {

    public String name;
    public Boolean done;

    public Todo(String name, Boolean done) {
        this.done = done;
        this.name = name;
    }

    // Why do I need to do that?
    public Todo() {
        done = false;
        name = "";
    }

    public void toggle() {
        done = !done;
    }
}
