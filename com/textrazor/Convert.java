package com.textrazor;




import org.xml.sax.SAXException;

//import no.acando.xmltordf.Builder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Convert {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("userprofilefile.xml"));
        
        // For TURTLE Format
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output1.ttl"));   
        //Builder.getAdvancedBuilderStream().build().convertToStream(in, out);
       
        
        // For RDF/XML format
        //Dataset dataset = Builder.getAdvancedBuilderJena().build().convertToDataset(in);
        //dataset.getDefaultModel().write(new FileOutputStream("output6.rdf"), "RDF/XML");
        System.out.println("RDF Generated");
    }

}
