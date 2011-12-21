package todos.pacgwt;

import com.google.gwt.dom.client.Node;

/**
 * An attempt of a base Dom view, but actually GWT API for DOM manipulation is not friendly to use
 * @param <C>
 * @param <N>
 */
public abstract class DomView<C extends Control, N extends Node> extends View<C, Node, N> {

    public DomView(C control) {
        super(control);
    }
    
    public DomView(C control, N root) {
        super(control, root);
    }

    @Override public <A extends Node> void add(A child) {
        root.appendChild(child);
    }

    @Override public void remove() {
        root.removeFromParent();
    }
}
