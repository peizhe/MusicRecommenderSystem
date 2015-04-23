package addsong;


import javafx.scene.text.TextBoundsType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import prerequisite.Parser;
import prerequisite.Normalizer;

public class AddSong extends JFrame implements ActionListener{

    String url="";
    JTextField t=null;
    JButton add=null;
    JLabel cmd;
    public AddSong ()
    {
        t=new JTextField(400);
        add=new JButton("ADD");
        this.setName("File Addition");
        this.setVisible(true);
        this.setLocation(50, 50);
        this.setSize(500, 500);
        this.setLayout(null);
        t.setBounds(60, 150, 400, 20);
        add.setBounds(100,200,100,20);
        add.addActionListener(this);

        cmd=new JLabel("add url for song");
        cmd.setBounds(20,20,200,30);
        this.add(cmd);
        this.add(t);
        this.add(add);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        url=t.getText();
        Parser p=new Parser();
        Normalizer n=new Normalizer();
        try {
            p.parse(url);

            String outputDirectory="C:\\Users\\ILMISS\\IdeaProjects\\myapp\\MRS PROJECT\\src\\database\\db_text";
            n.normalize(url,outputDirectory);

            JOptionPane.showMessageDialog(null,"Added song in the File");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddSong();
    }
}
