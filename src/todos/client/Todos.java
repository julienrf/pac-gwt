package todos.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class Todos implements EntryPoint {

    public static final TodoServiceAsync service = GWT.create(TodoService.class);

    @Override public void onModuleLoad() {
        // Create the application and fetch content
        new TodosCtl().fetch();
    }
}
