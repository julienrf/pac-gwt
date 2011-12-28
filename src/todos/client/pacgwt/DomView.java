package todos.client.pacgwt;

import com.google.gwt.dom.client.Node;

/**
 * An attempt of a base Dom view, but actually GWT API for DOM manipulation is not friendly to use
 * @param <N> Type of the root node of this view
 */
public abstract class DomView<N extends Node> extends View<Node, N> {

    public DomView(N root) {
        super(root);
    }
    
    @Override public <A extends Node> void add(A child) {
        root.appendChild(child);
    }

    @Override public void remove() {
        root.removeFromParent();
    }
}
