package todos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.shared.Todo;

/**
 * The async counterpart of <code>TodoService</code>.
 */
public interface TodoServiceAsync {
    void create(String name, AsyncCallback<Todo> callback);
    void read(int id, AsyncCallback<Todo> callback);
    void toggle(int id, AsyncCallback<Todo> callback);
    void delete(int id, AsyncCallback<Void> callback);
    void list(AsyncCallback<Todo[]> callback);
}
