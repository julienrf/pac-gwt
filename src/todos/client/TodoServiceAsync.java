package todos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import todos.shared.TodoData;

/**
 * The async counterpart of <code>TodoService</code>.
 */
public interface TodoServiceAsync {
    void create(String name, AsyncCallback<TodoData> callback);
    void read(int id, AsyncCallback<TodoData> callback);
    void toggle(int id, AsyncCallback<TodoData> callback);
    void delete(int id, AsyncCallback<Void> callback);
    void list(AsyncCallback<TodoData[]> callback);
}
