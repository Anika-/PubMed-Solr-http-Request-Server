package xmlParser;

import java.io.BufferedReader;

import java.io.File;



import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SimpleXMLParser {

	public List<String> findNames(String path){

		List<String> lista = new ArrayList<>();
		try{
			File file = new File(path);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(file);
			//document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName("variant");

			//System.out.println("nodeList:" +nodeList.item(2).getAttributes());
			for (int s = 0; s < nodeList.getLength(); s++) {

				Node fstNode = nodeList.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) fstNode;
					String geneName = element.getAttribute("base");  
					if (geneName.contains(" ") || geneName.matches(" ")){  //dealing with white spaces
						geneName = geneName.replace(" ","+");
						geneName = "%22".concat(geneName).concat("%22");
						lista.add(geneName);
					}else {
						lista.add(geneName);
					}
				}

			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}

		return lista;
	}

	
	public List<String> findNamesTxt(String path){
		List<String> lista = new ArrayList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				lista.add(line);
			}
		}catch(Exception e){
		}
		return lista;
	}
	
	
	
	public String jsonInputFormatter(String jsonString){
		
		JsonParser parser = new JsonParser();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonElement el = parser.parse(jsonString);
		jsonString = gson.toJson(el); // done
		jsonString = jsonString.replace("\u003c", "<").replace("\u003e", ">");
		return jsonString;
		
	}
	
	}



