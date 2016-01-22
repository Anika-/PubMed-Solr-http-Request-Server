import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xmlParser.SimpleXMLParser;



public class Main {


	public static void writeStringtoFile(File file, String data, String encoding){

		try{
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.close();
		System.out.println("End of execution");		}
		catch (IOException e){
			e.printStackTrace();
			System.out.println(e.getMessage() + file.getAbsolutePath());
		}

	}

	public final static void main(String[] args){

		SimpleXMLParser sXMLp = new SimpleXMLParser();
		List<String> lista = new ArrayList<String>();
		String resultPath = "";
		//String host = "http://localhost:8983/solr";
		//String host = "http://localhost:8983/solr/medline-citations";
		String host = "http://129.100.19.193:8983/solr/medline-citations";
		
		//reading names from file
		//lista = sXMLp.findNames("HGNClastDict.xml");
		
		Path currentRelativePath = Paths.get("");
		String currentPath = currentRelativePath.toAbsolutePath().toString();
		Utils.createDiretory(currentPath+File.separatorChar+"Dictionary");
		Utils.createDiretory(currentPath+File.separatorChar+"Files"+File.separatorChar+"HighFrequencyContext");
		//lista = sXMLp.findNames(currentPath+File.separatorChar+"Dictionary"+File.separatorChar+"HGNClastDict.xml");
		lista = sXMLp.findNamesTxt(currentPath+File.separatorChar+"Dictionary"+File.separatorChar+"HighFrequencyGenes.txt");
		resultPath = currentPath+File.separatorChar+"Files"+File.separatorChar+"HighFrequencyContext"+File.separatorChar;
		
		
		/*
		String op = System.getProperty("os.name", "generic").toLowerCase();
		if (op.indexOf("nix") >= 0 || op.indexOf("nux") >= 0){
			lista = sXMLp.findNames("Files/HGNClastDict.xml");
			resultPath = "Files/";
		}else if (op.indexOf("win") >= 0){
			lista = sXMLp.findNames(".\\Files\\HGNClastDict.xml");
			resultPath = ".\\Files\\";
		}else if (op.indexOf("mac") >= 0){
			lista = sXMLp.findNames("Files/HGNClastDict.xml");
			resultPath = "Files/";
		}else{
			lista = sXMLp.findNames("Files/HGNClastDict.xml");
			resultPath = "Files/";
		}*/
		
		QueryMaker query = new QueryMaker();
		ClientHttp client = new ClientHttp();
		URL myURL;
		

		for (Iterator<String> iterator = lista.iterator(); iterator.hasNext();) {
			String word = (String) iterator.next();
			String completeQuery = query.completeQuery(word, false);

			
			try {
				myURL = new URL(host.concat(completeQuery) );
			//	myURL = new URL(host);
				HttpURLConnection conn = client.makeConnection(myURL, "test", "soscip");
			//	HttpURLConnection conn = client.makeConnection(myURL, null, null);
				//System.out.println(conn.getResponseCode());
				//System.out.println(conn.getResponseMessage());
				File file = new File(resultPath.concat(word).concat(".xml"));  
			//	String filecontent = client.getResultFile(myURL, conn, file);
				client.getResultFile(myURL, conn, file);
			//snippet for the json files	
			/*	filecontent = sXMLp.jsonInputFormatter(filecontent);
				File file = new File(resultPath.concat(word).concat(".json"));   
			*/	
				
			
			//	writeStringtoFile(file, filecontent, null);
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage() +"\n" +resultPath.concat(word).concat(".xml"));
			}

		}

		}
	}