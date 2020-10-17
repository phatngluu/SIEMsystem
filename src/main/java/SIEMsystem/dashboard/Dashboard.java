package SIEMsystem.dashboard;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class Dashboard {
    public static void main(String[] args) {
        new Dashboard();
    }
    public Dashboard(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0,1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SIEM System Dashboard");
        frame.pack();
        frame.setVisible(true);
    }
}
