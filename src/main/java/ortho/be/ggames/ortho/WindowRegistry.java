package ortho.be.ggames.ortho;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class WindowRegistry {
    private Map<String, Class<? extends JFrame>> registry;

    public WindowRegistry() {
        registry = new HashMap<>();
    }

    public void registerWindow(String name, Class<? extends JFrame> windowClass) {
        registry.put(name, windowClass);
    }

    public void openWindow(String name) {
        Class<? extends JFrame> windowClass = registry.get(name);
        if (windowClass != null) {
            try {
                JFrame window = windowClass.getDeclaredConstructor().newInstance();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No window registered with name: " + name);
        }
    }
}
