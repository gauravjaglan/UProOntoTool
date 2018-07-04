package com.textrazor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.daverog.tripliser.TripliserFactory;
import org.daverog.tripliser.exception.TripliserException;  

public class Rdf {

	public static void genrateRdf() throws FileNotFoundException, TripliserException {
		
		File fe=new File("E:\\projects\\final_project\\WebContent\\usermapping.xml");
        FileInputStream mappingXmlInputStream=new FileInputStream(fe);
        
        File fe2=new File("E:\\projects\\final_project\\WebContent\\File.xml");
        FileInputStream inputFileInputStream=new FileInputStream(fe2);
        
        File fe3=new File("E:\\projects\\final_project\\WebContent\\user.rdf");
        OutputStream rdfOutputStream=new FileOutputStream(fe3);

        
	    TripliserFactory  
	        .create(mappingXmlInputStream)  
	        .setInputStream(inputFileInputStream)  
	        .writeRdf(rdfOutputStream);  
	    
	    System.out.println("---------------RDF Generated--------------");
	}
	
}
