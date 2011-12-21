package todos.client;

/**
 * Define the contract of controls
 * @param <V> Type of the view associated with this control
 */
public interface Control<V extends View> {
    /**
     * @return The view associated with this control
     */
    // FIXME What about controls having multiple views?
    V view();
}
