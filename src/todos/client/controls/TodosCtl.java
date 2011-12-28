package todos.client.controls;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.client.Todos;
import todos.client.pacgwt.Component;
import todos.client.pacgwt.Control;
import todos.client.pacgwt.EventSource;
import todos.client.views.TodoView;
import todos.client.views.TodosView;
import todos.shared.TodoData;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle the to-do list
 */
public class TodosCtl implements Control {

    private final List<TodoCtl> todos;
    private final TodosView view;

    public TodosCtl() {
        this.todos = new ArrayList<TodoCtl>();
        this.view = Component.register(new TodosView(TodosView.create()), this);
        this.view.subscribe(TodosView.AddPressed.class, new EventSource.Handler<TodosView.AddPressed>() {
            @Override public void apply(TodosView.AddPressed event) {
                create(event.value);
            }
        });
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
                final List<TodoView> views = new ArrayList<TodoView>(datas.length);
                for (TodoData data : datas) {
                    TodoCtl ctl = new TodoCtl(TodosCtl.this, data);
                    todos.add(ctl);
                    views.add(ctl.view());
                }
                view.update(views);
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
