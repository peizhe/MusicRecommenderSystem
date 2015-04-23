package prerequisite;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {

    public void parse(String inDirectory) throws Exception
    {
        File fXmlFile=null;
        File f=null;
        FileWriter fw=null;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = null;

        File directory = new File(inDirectory);

        // get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList)
        {
            String check=""+file.getAbsolutePath();

            if (file.isFile()&&check.substring(check.length()-4,check.length()).equals(".xml"))
            {
                fXmlFile = new File(""+file.getAbsolutePath());

                doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();
                String nm=""+file.getAbsolutePath();
                String tmp=nm.substring(0,nm.length()-4);

                nm=""+tmp+".txt";

                f=new File(""+nm);
                fw=new FileWriter(f);



                NodeList nList = doc.getElementsByTagName("ScopePool");



                Node nNode = nList.item(0);


                String s="";
                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {


                    Element eElement = (Element) nNode;
                    s=""+eElement.getElementsByTagName("AttributePool").item(8).getTextContent() ;

                    fw.write("RandomSegments:");
                    fw.write("\r\n");
                    fw.write(s);
                    fw.write("\r\n");
                    fw.write("#");
                    fw.write("\r\n");


                    s=""+eElement.getElementsByTagName("AttributePool").item(9).getTextContent();

                    fw.write("Onsets:");
                    fw.write("\r\n");
                    fw.write(s);
                    fw.write("\r\n");
                    fw.write("#");
                    fw.write("\r\n");



                    s=""+eElement.getElementsByTagName("AttributePool").item(10).getTextContent();

                    fw.write("Notes:");
                    fw.write("\r\n");
                    fw.write(s);
                    fw.write("\r\n");
                    fw.write("#");
                    fw.write("\r\n");



                    s=""+eElement.getElementsByTagName("AttributePool").item(11).getTextContent();

                    fw.write("Chords:");
                    fw.write("\r\n");
                    fw.write(s);
                    fw.write("\r\n");
                    fw.write("#");
                    fw.write("\r\n");



                    s=""+eElement.getElementsByTagName("AttributePool").item(12).getTextContent();

                    fw.write("Structure:");
                    fw.write("\r\n");
                    fw.write(s);
                    fw.write("\r\n");
                    fw.write("#");
                    fw.write("\r\n");


                    fw.close();
                }

            }
        }
    }
}
