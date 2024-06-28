package ortho.be.ggames.ortho.windows;

import javax.swing.*;

import ortho.be.ggames.ortho.MenuUtil;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Main Window");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = MenuUtil.createMenuBar();

        JMenu mainSpecificMenu = new JMenu("Dossiers patients");
        menuBar.add(mainSpecificMenu);

        setJMenuBar(menuBar);
    }
}
