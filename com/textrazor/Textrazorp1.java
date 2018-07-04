package com.textrazor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.daverog.tripliser.exception.TripliserException;

import com.textrazor.TextRazor;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.Topic;
import com.textrazor.annotations.AnalyzedText;
//import com.textrazor.annotations.Word;
	
	
public class Textrazorp1 {

	public static void text_razor_api(String name, String enroll, String email, String course,String stream, String batch) throws NetworkException, AnalysisException, InterruptedException, TripliserException {
		
		
		 String line ;
		  
		        //INPUT----------------------------------------------------- The name of the file to open.
		        String fileName = "C:/index.txt";

		        try {
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = 
		                new FileReader(fileName);
		            	System.out.println("open");
		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(fileReader);
		            
  List<List<EntityBean>> super_en = new ArrayList<List<EntityBean>>();
  
  List<List<String>> documents = new ArrayList<List<String>>();
	
  int senti = 0;
  int count = 0;
  while((line = bufferedReader.readLine()) != null) {
		                System.out.println(line+"\n -------------------------------------------------------");
   count++;
	
	String API_KEY = "fde4d9bf4ec0942438ad6627e002764e6e3fb1bf47a581f006e0879b";

	TextRazor client = new TextRazor(API_KEY);
	
	client.addExtractor("words");
	client.addExtractor("entities");
	
	//String a = "@ArvindKejriwal Machine Learning vs Semantic Web, Usain Bolt";
	AnalyzedText response = client.analyze(line);
	
	NLP.init();
   
    	System.out.println("printing sentiments");
    	senti= NLP.findSentiment(line);
    	System.out.println(line + ":::::: " +senti );
    	
    	List<String> doc1 = new ArrayList<String>();
	
	
	/*//for word related tasks
		for (Word w: response.getResponse().getWords()){
		
		System.out.println(w.getRelationToParent());
		System.out.println(w.getEntailments());
		System.out.println(w.getEntities());
		System.out.println(w.getParentWord());
	}*/
	

	List<EntityBean> enList = new ArrayList<EntityBean>();
	Map<String, String> entity_set = new HashMap<String, String>();  
	//for entity related tasks
	for ( Entity entity : response.getResponse().getEntities()) {
		
		System.out.println("**************************"+entity.getMatchedText()+"******");
		if( entity_set.get(entity.getMatchedText().toLowerCase()) == null ){
		EntityBean obj = new EntityBean();
		entity_set.put(entity.getMatchedText().toLowerCase(), entity.getMatchedText());
		obj.setCategory(entity.getFreebaseTypes().get(0));
		obj.setEname(entity.getMatchedText());
		obj.setWikilink(entity.getWikiLink());
		obj.setRating(count+"");
		obj.setSenti(senti+"");
		//adding to list of listnlp
		enList.add(obj);
		//entity.getMatchingWords();
		//System.out.println(w.getChildren());

	    //System.out.println("Matched Entity ID:        " + entity.getEntityId());
	    doc1.add(entity.getEntityId());
	 /*   System.out.println("Freebase Entity ID:       " + entity.getFreebaseId());
	    
	    System.out.println("Wikidata Entity ID:       " + entity.getWikidataId());
	    
	    System.out.println("Matching words"+entity.getMatchingWords()+ "\n \n "+entity.getMatchingWords());
	    System.out.println("Freebase Entity Type:     " + entity.getFreebaseTypes());
	    System.out.println("Matched Data: " + entity.getData());
	    System.out.println("Matched words:            " + entity.getMatchingWords());
	    System.out.println("Relevance score:        " +entity.getRelevanceScore());
	    System.out.println("Confidence score:        " +entity.getConfidenceScore());

	    System.out.println("Source Entity:            " + entity.getMatchedText()+"\n");
	    */
	    
	   //System.out.println("DBPedia Entity Type:      " + entity.getDBPediaTypes());
	   //System.out.println("WikiPedia Entity URL:     " + entity.getWikiLink());

	}
	}
	
	super_en.add(enList);
	System.out.println(doc1+"\n\n");
	documents.add(doc1);
 }
		        		      
 System.out.println( "\n Postwise Entity Set"+ documents);
		         //System.out.println( documents.get(1));
		         
		         //term holder
		    
                  List<String> th = new ArrayList<String>();
		          int count1 = 0;
		        
		         for (List<String> doc : documents) {
			            for (String word : doc) {
			                
			            	count1 = 0;
			            	for( String item: th )
			                {
			                	if( item.equalsIgnoreCase(word) ){
			                		count1++;
			                	  }
			                }
			            	 if ( count1 == 0 ){
			            		 th.add(word.toLowerCase());
			            	 }
			            } 
			     }
		         
		         
		         
                  Map<String, Integer> term_holder = new HashMap<String, Integer>();  
		          TFCalculator calculator = new TFCalculator();
		         
		         for( String  item : th ){
		        	  int tfidf = (int) calculator.idf(documents, item);
		        	  term_holder.put(item, tfidf);
		        	  System.out.println(item+" frequency: "+tfidf);
		         }
		         
		         //System.out.println("term holder "+term_holder);
		         
		         /*System.out.println("Mapppppppppppppppppppppppppppppp");
		         for(@SuppressWarnings("rawtypes") Map.Entry m:term_holder.entrySet()){  
		        	  // System.out.println("1"+m.getKey()+"1 "+m.getValue()+"");  
		         } 
		         for(List<EntityBean> ebl: super_en ){
			      		for(EntityBean eb: ebl ){
			      		//System.out.println("Bean values 1"+eb.getEname()+"1 "+term_holder.get(eb.getEname().toLowerCase()));
			      	}} */
		            
		      
			        UserBean uB = new UserBean();
			      	uB.setName(name);
			      	uB.setUserid(enroll);
			      	uB.setEmail(email);
			      	uB.setCourse(course);
			      	uB.setStream(stream);
			      	uB.setBatch(batch);
			      	uB.setElist(super_en);
			      	uB.setTerm_holder(term_holder);
			      	XMLStore.generateXML(uB);
                    Rdf.genrateRdf();
			      	
		   
	 // Always close files.
    bufferedReader.close(); 
    
}
catch(FileNotFoundException ex) {
    System.out.println(
        "Unable to open file '" + 
        fileName + "'");                
}
catch(IOException ex) {
    System.out.println(
        "Error reading file '" 
        + fileName + "'");   
    
    
  
    
    // Or we could just do this: 
    // ex.printStackTrace();
   }		        
  }
}
