package Gui;

import javax.swing.*;
import java.awt.*;

public class Guide extends JFrame {

    JLabel pic = new JLabel();

    public Guide(){
        setSize(new Dimension(900,600));
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(51,51,51));
        arrangeFrame();
        setVisible(true);
    }

    public void arrangeFrame(){
        pic.setBounds(50,30,800,514);
        pic.setIcon(new ImageIcon("./src/main/resources/guide.png"));
        add(pic);
    }
}
