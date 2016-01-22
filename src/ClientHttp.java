
import java.io.BufferedReader;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketPermission;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.commons.codec.binary.*;

public class ClientHttp {


	public HttpURLConnection makeConnection(URL host, String user, String password){

		try{
			URL myURL = host;

			HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
			//SocketPermission p1 = new SocketPermission("localhost:8008", "connect,accept,listen");
			conn.setRequestProperty("Request-Method", "GET");  
			
			//ServerSocket serverSocket = new ServerSocket(8008);
			//Socket client = serverSocket.accept();
			
			
			if  (user != null && password!= null){
				String header = user + ':' + password;

				if (System.getProperty("java.version").startsWith("1.8.")) {
					Encoder encoder = Base64.getEncoder();
					header = encoder.encodeToString(header.getBytes());}
				else{
					header = org.apache.commons.codec.binary.Base64.encodeBase64String(header.getBytes());
					
				}
				conn.setRequestProperty("Authorization","Basic " + header);

			}

			conn.setDoInput(true);  //receive data
			conn.setDoOutput(false);   //send data
			conn.connect();	
			return conn;

		}catch(IOException e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println(e.getSuppressed());
			return null;
		}

	}

	
/*	public ServerSocket bindConnection(String host, int port){
		try {
			InetAddress address = InetAddress.getByName(host);
			SocketAddress sockaddr = new InetSocketAddress(addr, port);
			ServerSocket serverSocket = new ServerSocket(8008);
			serverSocket.bind(sockaddr);
			return serverSocket;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SocketPermission p1 = new SocketPermission("localhost:8008", "connect,accept,listen");
		
	}*/
	
	public void getResultFile(URL url, HttpURLConnection conn, File file) throws IOException{
		System.out.println("File input start");
		InputStream is = conn.getInputStream();
		String charset = "UTF-8";
		BufferedReader br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
		//BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		//StringBuffer resultContent =  new StringBuffer(300000);
		String t = "";
		//FileWriter fw = new FileWriter(file.getAbsoluteFile());
		//BufferedWriter bw = new BufferedWriter(fw);
		
		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
		encoder.onMalformedInput(CodingErrorAction.REPORT);
		encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()),encoder));
		
		System.out.println("File input continuing");
		while ((t= br.readLine()) != null) {
			bw.write(t);
			bw.newLine();

		}
		//String filecontent = resultContent.toString();
		br.close();
		bw.close();
		//fw.close();
	//	return filecontent;
	}

}



