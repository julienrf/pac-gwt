package todos.client.views;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import todos.client.pacgwt.DomView;

import java.util.List;

/**
 * The to-do list user interface
 */
public class TodosView extends DomView<DivElement> {

    public static class Dom {
        private final DivElement root;
        private final InputElement addTodo;

        public Dom(DivElement root, InputElement addTodo) {
            this.addTodo = addTodo;
            this.root = root;
        }
    }

    private final InputElement addTodo;

    public TodosView(Dom dom) {
        super(dom.root);
        addTodo = dom.addTodo;
    }

    public static Dom create() {
        DivElement todos = DivElement.as(DOM.createDiv());
        RootPanel container = RootPanel.get("todo-placeholder"); // HACK this node should be passed as a parameter
        container.getElement().appendChild(todos);

        InputElement addTodo = InputElement.as(RootPanel.get("todo-input").getElement()); // HACK, too

        return new Dom(todos, addTodo);
    }

    @Override public void bindEvents() {
        super.bindEvents();
        Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(Event.NativePreviewEvent preview) {
                if (preview.getNativeEvent().getType().equalsIgnoreCase("keypress") && preview.getNativeEvent().getKeyCode() == 13 && preview.getNativeEvent().getEventTarget().cast() == addTodo) {
                    publish(new AddPressed(addTodo.getValue()));
                    addTodo.setValue("");
                }
            }
        });
    }

    public void update(List<TodoView> todos) {
        root.setInnerHTML("");
        for (TodoView todo : todos) {
            add(todo.root());
        }
    }

    public static class AddPressed {
        public final String value;
    
        private AddPressed(String value) {
            this.value = value;
        }
    }
}
