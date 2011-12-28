package todos.client.pacgwt;

import java.util.HashMap;
import java.util.Map;

/**
 * Components registry
 */
public class Component {
    private static Map<View, Control> components = new HashMap<View, Control>();
    
    public static <V extends View, C extends Control> V register(V view, C ctl) {
        components.put(view, ctl);
        return view;
    }
    
    public static <C extends Control> C of(View view) {
        return (C)(components.get(view));
    }
}
