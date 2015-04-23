package ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import optimazation.Optimizer;
public class MusicRecommender {
    File directory = new File("C:\\Users\\ILMISS\\IdeaProjects\\myapp\\MRS PROJECT\\src\\database\\normalized");
    public static LinkedList ll2=new LinkedList();
    File[] fList = directory.listFiles();
    File file1=null;
    String []ip1=new String[5];
    String []ip2=new String[5];
    int i=0,j=0;
    String tmp="";
    BufferedReader br=null;
    Optimizer op=null;
    public MusicRecommender(int index)throws Exception
    {
        //	ll2.clear();
        op=new Optimizer();
        System.out.println("index"+index); //we pass index of selected file
        file1=fList[index];
        br=new BufferedReader(new FileReader(new File(file1.getAbsolutePath())));
        sendData(br);
    }
    static float compare(String []a,String []b) //implementation of ecludian distance
    {
        float sum=0;
        float msum=0;
        int j=0,i=0;
        for(i=0;i<5;i++)
        {
            String []a1=a[i].split(" ");
            String []b1=b[i].split(" ");
            sum=0;
            for(j=0;j<Math.min(a1.length,b1.length);j++)
            {
                float t1=Float.parseFloat(a1[j]);
                float t2=Float.parseFloat(b1[j]);
                sum+=(t1-t2)*(t1-t2);
            }
            sum=sum/j;
            msum+=sum;
        }
        blxAlphaCrossovar(a,b);
        return (float)Math.sqrt(msum);

    }

    void sendData(BufferedReader br) throws Exception //fn to store normalized values of selected song and songs in db
    {
        while(true)
        {
            String r=br.readLine();
            if(r==null)
            {
                break;
            }
            else
            {
                if(r.equals("RandomSegments:")||r.equals("Onsets:")||r.equals("Notes:")||r.equals("Chords:")||r.equals("Structure:"))
                {
                    String next="";
                    while(true)
                    {
                        next=""+br.readLine();
                        if(next.equals("#"))
                            break;
                        tmp=tmp+next+" ";
                    }
                    ip1[i]=tmp;
                    i++;
                    tmp="";
                }
            }
        }
        br.close();
        i=0;
        directory=new File("C:\\Users\\ILMISS\\IdeaProjects\\myapp\\MRS PROJECT\\src\\database\\db_text");
        fList= directory.listFiles();
        for(j=0;j<fList.length;j++) //here we send each music file's property in our db with selected songs property to compare
        {

            File file=fList[j];
            i=0;
            if (file.isFile())
            {
                System.out.println(file.getName());
                BufferedReader br1=new BufferedReader(new FileReader(new File(file.getAbsolutePath())));
                while(true)
                {
                    String r=br1.readLine();
                    if(r==null)
                    {
                        break;
                    }
                    else
                    {
                        if(r.equals("RandomSegments:")||r.equals("Onsets:")||r.equals("Notes:")||r.equals("Chords:")||r.equals("Structure:"))
                        {

                            tmp="";
                            while(true)
                            {
                                String n=br1.readLine().trim();
                                if(n.equals("#"))
                                    break;
                                tmp=tmp+n+" ";
                            }
                            ip2[i]=tmp;
                            i++;
                            tmp="";
                        }
                    }

                }

                br1.close();
                float t=compare(ip1,ip2);//here we get actual value by ecludian distance
                ll2.add(t);
                System.out.println("value"+t+"\n");
            }
        }


    }
    public static void blxAlphaCrossovar(String[] a, String[] b) {
    }
}
