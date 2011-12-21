package todos.server;

import todos.client.TodoService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import todos.shared.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TodoServiceImpl extends RemoteServiceServlet implements TodoService {

    private final List<Todo> todos = new ArrayList<Todo>();

    @Override public Todo create(String name) {
        final Todo todo = new Todo(name, false);
        todos.add(todo);
        return todo;
    }

    @Override public Todo read(int id) {
        return todos.get(id);
    }

    @Override public Todo toggle(int id) {
        Todo todo = todos.get(id);
        todo.toggle();
        return todo;
    }

    @Override public void delete(int id) {
        todos.remove(id);
    }

    @Override public Todo[] list() {
        return todos.toArray(new Todo[todos.size()]);
    }
}
