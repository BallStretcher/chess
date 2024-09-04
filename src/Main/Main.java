package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        Panel pan = new Panel();
        window.add(pan);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        pan.launch();
    }
}
