package SIEMsystem.dashboard;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {
    public static void main(String[] args) {
        new Dashboard();
    }

    private int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public Dashboard() {
        frame = new JFrame();

        JButton button = new JButton("Click me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                count++;
                label.setText("Number of clicks: " + count);
            }
        });

        label = new JLabel("Number of clicks: 0");

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SIEM System Dashboard");
        frame.pack();
        frame.setVisible(true);
    }

}
