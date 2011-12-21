package todos.pacgwt;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for Widgets views
 * @param <C> Control type
 * @param <W> This widget type
 */
public abstract class WidgetView<C extends Control, W extends Widget> extends View<C, Widget, W> {

    public WidgetView(C control) {
        super(control);
    }

    public WidgetView(C control, W root) {
        super(control, root);
    }

    @Override public void remove() {
        root.removeFromParent();
    }

    @Override public <A extends Widget> void add(A child) {
        // Widget is not composite! Hence the following code...
        if (this.root instanceof HasWidgets) {
            ((HasWidgets)(this.root)).add(child);
        }
    }
}
