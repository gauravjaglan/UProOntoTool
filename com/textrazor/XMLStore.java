package com.textrazor;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLStore {
	
	
	public static void generateXML(UserBean u) {
		
		System.out.println("0000000000");
		
		for(List<EntityBean> el: u.getElist()){
		for(EntityBean e : el ){
		System.out.println("\nName: "+e.getEname()+"\nCategory: " +e.getCategory()+"\nWIkipediaLink: "+e.getWikilink()+"\nPreference-score: "+e.getRating()+"\nSentiment-score: "+e.getSenti()+"\n");
		}
		}
	  try {
        //System.out.println("333333");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		
		
		Element rootElement = doc.createElement("UserProfile");
		doc.appendChild(rootElement);

		// description elements
		Element description = doc.createElement("description");
		rootElement.appendChild(description);
		
		// user elements
	Element user = doc.createElement("User");
					description.appendChild(user);

		// set attribute to staff element
		Attr attr_id = doc.createAttribute("id");
		attr_id.setValue(u.getUserid());
		user.setAttributeNode(attr_id);

		// shorten way
		// staff.setAttribute("id", "1");

		// first name elements
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(u.getName()));
		user.appendChild(name);
		
		Element email = doc.createElement("email");
		email.appendChild(doc.createTextNode(u.getEmail()));
		user.appendChild(email);
		
		Element course = doc.createElement("course");
		course.appendChild(doc.createTextNode(u.getCourse()));
		user.appendChild(course);
		
		Element stream = doc.createElement("stream");
		stream.appendChild(doc.createTextNode(u.getStream()));
		user.appendChild(stream);
		
		Element batch = doc.createElement("batch");
		batch.appendChild(doc.createTextNode(u.getBatch()));
		user.appendChild(batch);
		
		
		Element Interests = doc.createElement("Interests");
		rootElement.appendChild(Interests);
		
		
		//Entity List-----------------------------
		int count = 1;
		
		for( List<EntityBean> en_List : u.getElist() ){
		for (EntityBean e: en_List){
		// user elements
		Element entity = doc.createElement("Entity");
		Interests.appendChild(entity);

		// set attribute to staff element
		Attr e_id = doc.createAttribute("id");
		e_id.setValue(count+"");
		entity.setAttributeNode(e_id);

		// shorten way
		// staff.setAttribute("id", "1");

		// first name elements
		Element ename = doc.createElement("name");
		ename.appendChild(doc.createTextNode(e.getEname()));
		entity.appendChild(ename);
		
		Element wiki = doc.createElement("WikiLink");
		wiki.appendChild(doc.createTextNode(e.getWikilink()));
		entity.appendChild(wiki);
		
		Element cat = doc.createElement("cat");
		cat.appendChild(doc.createTextNode(e.getCategory()));
		entity.appendChild(cat);
		
		Element rat = doc.createElement("rating");
		rat.appendChild(doc.createTextNode(e.getRating()));
		entity.appendChild(rat);
		
		Element senti = doc.createElement("sentiment-score");
		senti.appendChild(doc.createTextNode(e.getSenti()));
		entity.appendChild(senti);
		count++;
		}
	}
		//System.out.println("11111");
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("E:\\projects\\final_project\\WebContent\\File.xml"));
        //System.out.println("22222");
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
        System.out.println("src "+source+" dom "+result);
		transformer.transform(source, result);

		System.out.println("File saved!");

	  }
	  catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } 
	  catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}

