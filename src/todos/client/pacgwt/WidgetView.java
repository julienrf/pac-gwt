package todos.client.pacgwt;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for Widgets views
 * @param <W> This widget type
 */
public abstract class WidgetView<W extends Widget> extends View<Widget, W> {

    public WidgetView(W root) {
        super(root);
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
