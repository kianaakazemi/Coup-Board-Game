package Gui;

import Logic.Players.BotType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Start extends JFrame {
    JLabel background = new JLabel();
    JLabel firstLabel = new JLabel();
    JLabel secondLabel = new JLabel();
    JLabel thirdLabel = new JLabel();

    JButton start = new JButton();

    public static JComboBox first;
    public static JComboBox second;
    public static JComboBox third;
    public static boolean run;


    public Start(){
        setSize(new Dimension(1200,570));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        arrangeFrame();
        setVisible(true);
    }

    public void arrangeFrame(){
        background.setBounds(0,0,1200,563);
        background.setIcon(new ImageIcon("/Users/KianaKazemi/Documents/my projects/exercise4/src/main/resources/background.jpeg"));
        add(background);

        start.setBounds(200,460,150,30);
        start.setText("Start game");
        start.setBackground(new Color(0x52756B));
        start.setForeground(Color.white);
        start.setBorderPainted(false);
        start.setOpaque(true);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new Board();
                    run = true;
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        background.add(start);

      // BotType[] bots = {BotType.Saver,BotType.CautiousKiller,BotType.CoupMaker,BotType.Paranoid};
      // first = new JComboBox(bots);
      // first.setBounds(40,400,150,30);
      // background.add(first);
      //
      // firstLabel.setBounds(50,370,100,30);
      // firstLabel.setText("First opponent:");
      // firstLabel.setForeground(new Color(0x52756B));
      // firstLabel.setFont(new Font("Serif",Font.PLAIN,14));
      // background.add(firstLabel);
      //
      // second = new JComboBox(bots);
      // second.setBounds(200,400,150,30);
      // background.add(second);
      //
      // secondLabel.setBounds(210,370,120,30);
      // secondLabel.setText("Second opponent:");
      // secondLabel.setForeground(new Color(0x52756B));
      // secondLabel.setFont(new Font("Serif",Font.PLAIN,14));
      // background.add(secondLabel);
      //
      // third = new JComboBox(bots);
      // third.setBounds(360,400,150,30);
      // background.add(third);
      //
      // thirdLabel.setBounds(370,370,100,30);
      // thirdLabel.setText("Third opponent:");
      // thirdLabel.setForeground(new Color(0x52756B));
      // thirdLabel.setFont(new Font("Serif",Font.PLAIN,14));
      // background.add(thirdLabel);
    }

    public static BotType selectedBot(JComboBox bot){
        return (BotType) bot.getSelectedItem();
    }

}
