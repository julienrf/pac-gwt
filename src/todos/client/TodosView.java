package todos.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import todos.client.pacgwt.DomView;

/**
 * The to-do list user interface
 */
public class TodosView extends DomView<TodosCtl, DivElement> {

    private InputElement addTodo;

    public TodosView(TodosCtl control) {
        super(control);
    }

    public TodosView(TodosCtl control, DivElement root) {
        super(control, root);
    }

    @Override public DivElement create() {
        DivElement todos = DivElement.as(DOM.createDiv());
        RootPanel container = RootPanel.get("todo-placeholder"); // HACK this node should be passed as a parameter
        container.getElement().appendChild(todos);

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
        root.setInnerHTML("");
        for (TodoCtl todo : control().todos()) {
            add(todo.view().root());
        }
    }
}
