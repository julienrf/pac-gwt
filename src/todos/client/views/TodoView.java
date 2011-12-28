package todos.client.views;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import todos.client.pacgwt.DomView;

/**
 * A single to-do item user interface
 */
public class TodoView extends DomView<DivElement> {

    public static class Data {
        private final String name;
        private final boolean done;

        public Data(String name, boolean done) {
            this.done = done;
            this.name = name;
        }
    }

    public static class Dom {
        private final DivElement root;
        private final InputElement done;
        private final ButtonElement delete;

        public Dom(DivElement root, InputElement done, ButtonElement delete) {
            this.delete = delete;
            this.done = done;
            this.root = root;
        }
    }

    private final InputElement done;
    private final ButtonElement delete;

    public TodoView(Dom dom) {
        super(dom.root);
        done = dom.done;
        delete = dom.delete;
    }

    public static Dom create(Data data) {

        DivElement root = DivElement.as(DOM.createDiv());

        InputElement done = InputElement.as(DOM.createInputCheck());
        done.setChecked(data.done);
        if (data.done) root.setClassName("done");

        ButtonElement delete = ButtonElement.as(DOM.createButton());
        delete.setInnerText("Delete");

        Element nameSpan = DOM.createSpan();
        nameSpan.setInnerText(data.name);

        root.appendChild(done);
        root.appendChild(nameSpan);
        root.appendChild(delete);

        return new Dom(root, done, delete);
    }

    public void update(boolean isDone) {
        done.setChecked(isDone);
        if (isDone) {
            root.setClassName("done");
        } else {
            root.setClassName("");
        }
    }

    // Bind events and delegate logic handling to the control
    @Override public void bindEvents() {
        super.bindEvents();
        Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            @Override public void onPreviewNativeEvent(Event.NativePreviewEvent preview) {
                NativeEvent evt = preview.getNativeEvent();
                if (evt.getType().equalsIgnoreCase("click")) {
                    if (evt.getEventTarget().cast() == delete) {
                        publish(new DeleteClicked());
                    } else if (evt.getEventTarget().cast() == done) {
                        publish(new ToggleClicked());
                    }
                }
            }
        });
    }

    public static class DeleteClicked {
        private DeleteClicked() { }
    }
    public static class ToggleClicked {
        private ToggleClicked() { }
    }
}
