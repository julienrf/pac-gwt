package todos.client.controls;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.client.views.TodoView;
import todos.client.Todos;
import todos.client.pacgwt.Control;
import todos.shared.Todo;
import todos.shared.TodoData;

/**
 * Handle a to-do item.
 * Acts as a proxy (through inheritance, but that may not be a good idea since it implements IsSerializable which I don’t want to)
 */
public class TodoCtl extends Todo implements Control {
    
    private final TodoView view;
    private final TodosCtl todos;
    
    public TodoCtl(TodosCtl todos, TodoData data) {
        super(data);
        this.todos = todos;
        view = new TodoView(this); // FIXME I should not pass “this” as a parameter
    }
    
    public TodoView view() {
        return view;
    }

    /**
     * Toggle action
     * FIXME abstract this pattern
     */
    @Override public void toggle() {
        // Call the parent implementation and update the view (immediate feedback)
        super.toggle();
        view.update();

        // Sync with the server
        Todos.service.toggle(todos.idOf(this), new AsyncCallback<TodoData>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(TodoData data) {
                // Update our data with reference data from the server and update the view
                TodoCtl.this.data = data;
                view.update();
            }
        });
    }

    public void delete() {
        todos.delete(this);
    }
}
