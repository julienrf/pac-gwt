package todos.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import todos.shared.Todo;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("todo")
public interface TodoService extends RemoteService {
    /**
     * Create a new to-do item
     * @param name Name of task which has to be done
     * @return The created task
     */
    Todo create(String name);

    /**
     * Get a to-do item from a given id
     * @param id Id of the item to get
     * @return The to-do item
     */
    Todo read(int id);

    /**
     * Toggle the done state of a to-do item
     * @param id Id of the item
     * @return The new state of the item
     */
    Todo toggle(int id);

    /**
     * Delete an item
     * @param id Id of the item to delete
     */
    void delete(int id);

    /**
     * @return the list of to-do items of this application
     */
    Todo[] list();
}
