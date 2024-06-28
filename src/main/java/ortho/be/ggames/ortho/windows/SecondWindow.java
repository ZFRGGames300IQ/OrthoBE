package ortho.be.ggames.ortho.windows;

import javax.swing.*;

import ortho.be.ggames.ortho.MenuUtil;

public class SecondWindow extends JFrame {
    public SecondWindow() {
        setTitle("Second Window");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JMenuBar menuBar = MenuUtil.createMenuBar();

        JMenu secondSpecificMenu = new JMenu("Second Specific");
        menuBar.add(secondSpecificMenu);

        setJMenuBar(menuBar);
    }
}

