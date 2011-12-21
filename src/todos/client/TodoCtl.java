package todos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.shared.Todo;

/**
 * Handle a to-do item.
 * Acts as a proxy (through inheritance, but that may not be a good idea since it implements IsSerializable which I don’t want to)
 */
public class TodoCtl extends Todo implements Control<TodoView> {
    
    private final TodoView view;
    private final TodosCtl todos;
    
    public TodoCtl(TodosCtl todos, String name, boolean done) {
        super(name, done);
        this.todos = todos;
        view = new TodoView(this); // FIXME I should not pass “this” as a parameter
    }
    
    @Override public TodoView view() {
        return view;
    }

    @Override public void toggle() {
        super.toggle();
        Todos.service.toggle(todos.idOf(this), new AsyncCallback<Todo>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(Todo model) {
                view.update();
            }
        });
    }

    public void delete() {
        todos.delete(this);
    }
}
