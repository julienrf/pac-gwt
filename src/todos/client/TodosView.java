package todos.client;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import todos.pacgwt.WidgetView;

/**
 * The to-do list user interface
 */
public class TodosView extends WidgetView<TodosCtl, VerticalPanel> {

    private InputElement addTodo;

    public TodosView(TodosCtl control) {
        super(control);
    }

    public TodosView(TodosCtl control, VerticalPanel root) {
        super(control, root);
    }

    @Override public VerticalPanel create() {
        VerticalPanel todos = new VerticalPanel();
        RootPanel container = RootPanel.get("todo-placeholder"); // HACK this node should be passed as a parameter
        container.add(todos);

        addTodo = InputElement.as(RootPanel.get("todo-input").getElement()); // HACK, too

        return todos;
    }

    @Override
    public void bindEvents() {
        super.bindEvents();
        Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            @Override public void onPreviewNativeEvent(Event.NativePreviewEvent preview) {
                if (preview.getNativeEvent().getType().equalsIgnoreCase("keypress") && preview.getNativeEvent().getKeyCode() == 13 && preview.getNativeEvent().getEventTarget().cast() == addTodo) {
                    control().create(addTodo.getValue());
                    addTodo.setValue("");
                }
            }
        });
    }

    @Override public void update() {
        root.clear();
        for (TodoCtl todo : control().todos()) {
            add(todo.view().root());
        }
    }
}
