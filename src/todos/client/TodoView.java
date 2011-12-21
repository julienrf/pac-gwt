package todos.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;
import todos.pacgwt.WidgetView;

/**
 * A single to-do item user interface
 */
public class TodoView extends WidgetView<TodoCtl, HorizontalPanel> {

    // HACK Unsafe because we have no guarantee these fields are initialized
    private CheckBox done;
    private Button delete;

    public TodoView(TodoCtl control) {
        super(control);
    }

    public TodoView(TodoCtl control, HorizontalPanel root) {
        super(control, root);
        // TODO bind done and delete buttons
    }

    @Override public HorizontalPanel create() {
        HorizontalPanel root = new HorizontalPanel();
        done = new CheckBox();
        done.setValue(control().done);
        if (control().done) done.addStyleName("done");
        delete = new Button("Delete");

        root.add(done);
        root.add(new Label(control().name));
        root.add(delete);

        return root;
    }

    @Override public void update() {
        done.setValue(control().done);
        if (control().done) {
            root.addStyleName("done");
        } else {
            root.removeStyleName("done");
        }
    }

    // Bind events and delegate logic handling to the control
    @Override public void bindEvents() {
        super.bindEvents();
        delete.addClickHandler(new ClickHandler() {
            @Override public void onClick(ClickEvent clickEvent) {
                control().delete();
            }
        });

        done.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override public void onValueChange(ValueChangeEvent<Boolean> e) {
                control().toggle();
            }
        });
    }
}
