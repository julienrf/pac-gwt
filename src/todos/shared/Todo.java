package todos.shared;

/**
 * A to-do item and its logic
 */
public class Todo {

    protected TodoData data;

    public Todo(TodoData data) {
        this.data = data;
    }

    // Convenient constructor
    public Todo(String name) {
        this.data = new TodoData(name, false);
    }

    public void toggle() {
        data.done = !data.done;
    }

    public TodoData data() {
        return data;
    }
}
