package todos.client.pacgwt;

import java.util.HashMap;
import java.util.Map;

/**
 * Quick and dirty event handling.
 * Known limitations: only one handler is allowed for a given event type
 * Maybe removed in the future in favor of a more reactive system
 */
public class EventSource {

    public interface Handler<E> {
        void apply(E event);
    }

    private final Map<Class<?>, Handler> handlers = new HashMap<Class<?>, Handler>();
    
    public <E> void publish(E e) {
        Handler<E> handler = handlers.get(e.getClass());
        if (handler != null) {
            handler.apply(e);
        }
    }
    
    public <E> void subscribe(Class<E> type, Handler<? super E> handler) {
        handlers.put(type, handler);
    }
}
