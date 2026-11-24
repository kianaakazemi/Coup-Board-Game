package Gui;

import Logic.Game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameEventWriter extends JFrame{
    JPanel scrollPanel = new JPanel();

    public static JTextArea eventsTextArea = new JTextArea();
    JLabel title = new JLabel("Game events");

    JScrollPane eventsScrollPane = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    Border border = BorderFactory.createLineBorder(Color.lightGray,3);

    public GameEventWriter(){
        setSize(new Dimension(400,600));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(51,51,51));
        arrangeFrame();
        setVisible(true);
    }

    public void arrangeFrame(){
        scrollPanel.setPreferredSize(new Dimension(300,3000));
        scrollPanel.setBackground(new Color(51,51,51));
        eventsScrollPane.setBounds(50,80,300,450);
        eventsScrollPane.setBorder(border);
        add(eventsScrollPane);

        eventsTextArea.setBounds(0,0,300,3000);
        eventsTextArea.setBackground(new Color(51,51,51));
        eventsTextArea.setOpaque(true);
        eventsTextArea.setText(Game.events);
        eventsTextArea.setEditable(false);
        eventsTextArea.setForeground(Color.lightGray);
        eventsTextArea.setFont(new Font("Serif",Font.PLAIN,18));
        scrollPanel.add(eventsTextArea);

        title.setBounds(50,30,300,50);
        title.setBackground(Color.lightGray);
        title.setFont(new Font("Serif",Font.PLAIN,20));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);
        add(title);
    }
}
