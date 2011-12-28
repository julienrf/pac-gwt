package todos.client.views;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import todos.client.controls.TodoCtl;
import todos.client.pacgwt.DomView;

/**
 * A single to-do item user interface
 */
public class TodoView extends DomView<TodoCtl, DivElement> {

    // HACK Unsafe because we have no guarantee these fields are initialized
    private InputElement done;
    private ButtonElement delete;

    public TodoView(TodoCtl control) {
        super(control);
    }

    public TodoView(TodoCtl control, DivElement root) {
        super(control, root);
        // TODO bind done and delete buttons
    }

    @Override public DivElement create() {

        DivElement root = DivElement.as(DOM.createDiv());

        done = InputElement.as(DOM.createInputCheck());
        done.setChecked(control().data().done);
        if (control().data().done) root.setClassName("done");

        delete = ButtonElement.as(DOM.createButton());
        delete.setInnerText("Delete");

        Element name = DOM.createSpan();
        name.setInnerText(control().data().name);

        root.appendChild(done);
        root.appendChild(name);
        root.appendChild(delete);

        return root;
    }

    @Override public void update() {
        done.setChecked(control().data().done);
        if (control().data().done) {
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
                        control().delete();
                    } else if (evt.getEventTarget().cast() == done) {
                        control().toggle();
                    }
                }
            }
        });
    }
}
