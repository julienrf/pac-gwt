package todos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.client.pacgwt.Control;
import todos.shared.TodoData;

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
        Todos.service.list(new AsyncCallback<TodoData[]>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(TodoData[] datas) {
                todos.clear();
                for (TodoData data : datas) {
                    todos.add(new TodoCtl(TodosCtl.this, data));
                }
                view.update();
            }
        });
    }

    public void create(String value) {
        Todos.service.create(value, new AsyncCallback<TodoData>() {
            @Override public void onFailure(Throwable throwable) { /* TODO */ }

            @Override public void onSuccess(TodoData data) {
                TodoCtl ctl = new TodoCtl(TodosCtl.this, data);
                todos.add(ctl);
                view.add(ctl.view().root());
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
