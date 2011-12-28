package todos.client.pacgwt;

/**
 * Base class for views
 * @param <C> Type of the control associated with this view
 * @param <T> Base composite type of views
 * @param <V> Type of this view root component
 */
public abstract class View<C extends Control, T, V extends T> {
    
    private final C control; // FIXME Needed because this is the only way to retrieve the component interface from the view. It introduces a dependency between the view and its control, though
    protected final V root;

    /**
     * View creation constructor: this view root component is retrieved from the create method
     * @param control Control associated with this view
     */
    public View(C control) {
        this.control = control;
        this.root = create();
        bindEvents();
    }

    /**
     * Bind constructor: this view root component is retrieved from the root parameter (the create method is not called)
     * FIXME I should define a factory with a create and bind methods instead of overloading constructors
     * @param control Control associated with this view
     * @param root Root component of this view
     */
    public View(C control, V root) {
        this.control = control;
        this.root = root;
        bindEvents();
    }

    /**
     * @return the control associated with this view
     */
    public final C control() {
        return control;
    }

    /**
     * @return the root element of this view
     */
    public final V root() {
        return root;
    }

    /**
     * Create the full UI of this view
     * @return The root component of the UI
     */
    public abstract V create();

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
