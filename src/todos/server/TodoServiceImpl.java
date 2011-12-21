package todos.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import todos.client.TodoService;
import todos.shared.Todo;
import todos.shared.TodoData;

import java.util.ArrayList;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TodoServiceImpl extends RemoteServiceServlet implements TodoService {

    private final List<Todo> todos = new ArrayList<Todo>();

    @Override public TodoData create(String name) {
        final Todo todo = new Todo(name);
        todos.add(todo);
        return todo.data();
    }

    @Override public TodoData read(int id) {
        return todos.get(id).data();
    }

    @Override public TodoData toggle(int id) {
        Todo todo = todos.get(id);
        todo.toggle();
        return todo.data();
    }

    @Override public void delete(int id) {
        todos.remove(id);
    }

    // FIXME Maybe I should directly store TodoDatas, it would avoid the following conversion (but it would require to drop object oriented paradigm)
    @Override public TodoData[] list() {
        List<TodoData> datas = new ArrayList<TodoData>(todos.size());
        for (Todo todo : todos) {
            datas.add(todo.data());
        }
        return datas.toArray(new TodoData[datas.size()]);
    }
}
