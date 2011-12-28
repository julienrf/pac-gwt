package todos.client.pacgwt;

/**
 * Base class for views
 * @param <T> Base composite type of views
 * @param <V> Type of this view root component
 */
public abstract class View<T, V extends T> extends EventSource {
    
    protected final V root;

    /**
     * View creation constructor: this view root component is retrieved from the create method
     * @param root Root node of this view
     */
    public View(V root) {
        this.root = root;
        bindEvents();
    }

    /**
     * @return the root element of this view
     */
    public final V root() {
        return root;
    }

    /**
     * Automatically called when a view is created or bound.
     * Event handlers should be attached to events here.
     */
    protected void bindEvents() {}

    /**
     * Add a child view to this view
     * @param child Child
     * @param <A> Child type
     */
    public abstract <A extends T> void add(A child);

    /**
     * Remove this view from its view container
     */
    public abstract void remove();
}
