package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

import prerequisite.Normalizer;
import prerequisite.Parser;
import ai.MusicRecommender;

public class MusicRecommenderSystem implements ActionListener {
    JFrame f;
    JButton[] bplay;
    JButton[] bpause;
    JLabel[] index;
    JLabel[] sname;
    JLabel no, name, play, pause, rating, value1;
    JSlider[] rate;
    JTextField[] value;
    JButton bsubmit, breset;

    public void blxAlphaCrossovar(String[] a, String[] b) {
    }

    public MusicRecommenderSystem() throws Exception {
        Parser p = new Parser();
        String inDirectory = "C:\\Users\\ILMISS\\IdeaProjects\\myapp\\MRS PROJECT\\src\\database\\xml";
        p.parse(inDirectory);
        Normalizer n = new Normalizer();
        String outDirectory = "C:\\Users\\ILMISS\\IdeaProjects\\myapp\\MRS PROJECT\\src\\database\\normalized";
        n.normalize(inDirectory, outDirectory);

        f = new JFrame("Music Recommended");
        f.setSize(1350, 1000);
        f.setBackground(new Color(34, 34, 34));
        f.setLayout(new GridLayout(12, 4, 8, 8));
        no = new JLabel("Number");
        name = new JLabel("Song name");
        play = new JLabel("play");
        pause = new JLabel("pause");
        rating = new JLabel("Rating");
        value1 = new JLabel("Value");
        bsubmit = new JButton("Submitt");
        bsubmit.addActionListener(this);

        breset = new JButton("Reset");
        f.add(no);
        f.add(name);
        f.add(rating);
        f.add(value1);
        index = new JLabel[10];
        sname = new JLabel[10];
        bplay = new JButton[10];
        bpause = new JButton[10];
        rate = new JSlider[10];
        value = new JTextField[10];
        File directory = new File("C:\\Users\\ILMISS\\IdeaProjects\\myapp\\MRS PROJECT\\src\\database\\normalized");
        File[] fList = directory.listFiles();
        for (int i = 0; i < 10; i++) {
            index[i] = new JLabel("" + (i + 1));

            sname[i] = new JLabel("" + fList[i].getName());
            bplay[i] = new JButton("play");
            bpause[i] = new JButton("pause");
            rate[i] = new JSlider(0, 10);
            value[i] = new JTextField(10);
            value[i].setEditable(false);
            f.add(index[i]);
            f.add(sname[i]);
            f.add(rate[i]);
            f.add(value[i]);
        }
        rate[0].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[0].setText("" + rate[0].getValue());
            }
        });
        rate[1].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[1].setText("" + rate[1].getValue());
            }
        });
        rate[2].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[2].setText("" + rate[2].getValue());
            }
        });
        rate[3].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[3].setText("" + rate[3].getValue());
            }
        });
        rate[4].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[4].setText("" + rate[4].getValue());
            }
        });
        rate[5].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[5].setText("" + rate[5].getValue());
            }
        });
        rate[6].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[6].setText("" + rate[6].getValue());
            }
        });
        rate[7].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[7].setText("" + rate[7].getValue());
            }
        });
        rate[8].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[8].setText("" + rate[8].getValue());
            }
        });
        rate[9].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                value[9].setText("" + rate[9].getValue());
            }
        });

        f.add(bsubmit);
        f.add(breset);
        f.setBackground(Color.RED);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e1) {
        JButton b = (JButton) e1.getSource();
        LinkedList ll = new LinkedList();
        LinkedList ll1 = new LinkedList();
        LinkedList ll3 = new LinkedList();
        File directory = new File("C:\\Users\\ILMISS\\IdeaProjects\\myapp\\MRS PROJECT\\src\\database\\db_text");
        File[] fList = directory.listFiles();
        int max = 0;
        if (b == bsubmit) {
            for (int i = 0; i < 10; i++) {
                try {
                    ll.add(Integer.parseInt(value[i].getText()));
                } catch (Exception e2) {
                    ll.add(0);
                }

            }
            max = (Integer) Collections.max(ll);
            for (int i = 0; i < 10; i++)//here we select max rating
            {
                if ((Integer) ll.get(i) == max)
                    ll1.add(i);
            }
            for (int k = 0; k < ll1.size(); k++) {
                try {

                    new MusicRecommender((Integer) ll1.get(k));
                    //Collections.sort(CompData.ll2);
                    float min = (Float) Collections.min(MusicRecommender.ll2);
                    for (int i = 0; i < 5; i++)//here we select best 5 songs
                    {
                        //JOptionPane.showMessageDialog(null,""+min);
                        for (int j = 0; j < MusicRecommender.ll2.size(); j++) {
                            if ((Float) MusicRecommender.ll2.get(j) == min) {
                                ll3.add(j);
                                MusicRecommender.ll2.remove(j);
                                MusicRecommender.ll2.add(j, 10.0F);
                                //JOptionPane.showMessageDialog(null,"\n"+j+"\n"+CompData.ll2);
                                break;
                            }

                        }
                        min = (Float) Collections.min(MusicRecommender.ll2);
                    }
                    String t = "";
                    for (int j = 0; j < ll3.size(); j++)
                        t = t + fList[(Integer) ll3.get(j)] + "\n";
                    JOptionPane.showMessageDialog(null, "Best Match is: \n" + t);

                    ll3.clear();
                    MusicRecommender.ll2.clear();

                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new MusicRecommenderSystem();
    }
}
