package todos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.shared.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle the to-do list
 */
public class TodosCtl implements Control<TodosView> {

    private final List<TodoCtl> todos;
    private final TodosView view;

    public TodosCtl() {
        this.todos = new ArrayList<TodoCtl>();
        this.view = new TodosView(this);
    }

    @Override public TodosView view() {
        return view;
    }
    
    public Iterable<TodoCtl> todos() {
        return todos;
    }

    public int idOf(TodoCtl todoCtl) {
        return todos.indexOf(todoCtl);
    }

    public void fetch() {
        Todos.service.list(new AsyncCallback<Todo[]>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(Todo[] ts) {
                todos.clear();
                for (Todo todo : ts) {
                    todos.add(new TodoCtl(TodosCtl.this, todo.name, todo.done));
                }
                view.update();
            }
        });
    }

    public void create(String value) {
        Todos.service.create(value, new AsyncCallback<Todo>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(Todo todo) {
                // FIXME I should not use inheritance between Todo and TodoCtl, but delegation since I’m copying all Todo proporties in the new TodoCtl object
                TodoCtl ctl = new TodoCtl(TodosCtl.this, todo.name, todo.done);
                todos.add(ctl);
                view.add(ctl.view().root);
            }
        });

    }

    public void delete(final TodoCtl todoCtl) {
        final int id = idOf(todoCtl);
        Todos.service.delete(id, new AsyncCallback<Void>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(Void aVoid) {
                todos.remove(id);
                todoCtl.view().remove(); // HACK I should write a method remove on TodoCtl
            }
        });
    }
}
