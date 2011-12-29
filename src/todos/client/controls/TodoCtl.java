package todos.client.controls;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.client.Todos;
import todos.client.pacgwt.Component;
import todos.client.pacgwt.Control;
import todos.client.views.TodoView;
import todos.shared.Todo;
import todos.shared.TodoData;

/**
 * Handle a to-do item.
 * Acts as a proxy (through inheritance, but that may not be a good idea since it implements IsSerializable which I don’t want to)
 */
public class TodoCtl extends Todo implements Control, TodoView.Control {
    
    private final TodoView view;
    private final TodosCtl todos;
    
    public TodoCtl(TodosCtl todos, TodoData data) {
        super(data);
        this.todos = todos;
        this.view = Component.register(new TodoView(TodoView.create(new TodoView.Data(data.name, data.done)), this), this);
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
        view.update(data.done);

        // Sync with the server
        Todos.service.toggle(todos.idOf(this), new AsyncCallback<TodoData>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(TodoData data) {
                // Update our data with reference data from the server and update the view
                TodoCtl.this.data = data;
                view.update(data.done);
            }
        });
    }

    @Override public void delete() {
        todos.delete(this);
    }
}
