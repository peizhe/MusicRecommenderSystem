package prerequisite;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Normalizer {

    public void normalize(String inDirectory,String outDirectory) throws Exception
    {
        File directory = new File(inDirectory);

        // get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList)
        {


            String check=""+file.getAbsolutePath();
            if (file.isFile()&&check.substring(check.length()-4,check.length()).equals(".txt"))
            {

                File f=new File(""+file.getAbsolutePath());
                //String t=check.substring(11,check.length());
                String t=f.getName();

                File nf=new File(outDirectory+"\\"+t);

                FileWriter fw=new FileWriter(nf);
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                while(true)
                {
                    String in=br.readLine();
                    if(in==null)
                    {
                        fw.close();
                        break;
                    }
                    if(in.equals("RandomSegments:")||in.equals("Onsets:")||in.equals("Notes:")||in.equals("Chords:")||in.equals("Structure:"))
                    {
                        fw.write(in);
                        fw.write("\r\n");
                        String val="";
                        while(true)
                        {
                            String  flg=""+br.readLine();
                            if(flg.equals("#"))
                                break;
                            val=val+flg;
                        }
                        String []splt=val.split(" ");
                        float []v=new float[splt.length];
                        for(int i=0;i<splt.length;i++)
                        {
                            v[i]=Float.parseFloat(splt[i]);
                        }
                        float max=v[0],min=v[0];
                        for(int i=0;i<v.length;i++)
                        {
                            if(v[i]>max)
                                max=v[i];
                            else
                            {
                                if(v[i]<min)
                                    min=v[i];
                            }
                        }
                        for(int i=0;i<v.length;i++) //formula to normalize values
                        {
                            float tmp=((v[i]-min)/(max-min));
                            fw.write(""+tmp+" ");
                        }
                        fw.write("\r\n");
                        fw.write("#");
                        fw.write("\r\n");
                    }
                }
            }
        }
    }
}