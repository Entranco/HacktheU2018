package server;




import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
//import 	android.graphics.Bitmap;
import java.io.*;
import java.util.Base64;

class FileToString {
 	static String encodeFileToBase64Binary(File file) {
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = new String(Base64.getEncoder().encode(bytes), "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return encodedfile;
	}
}

public class server extends Thread {
	private boolean sendPic = false;
	private int queryNum;
	private ServerSocket serverSocket;
	// BitMap bit;
	int users;
	public server(int port) throws IOException {
		serverSocket = new ServerSocket(8888);
		//serverSocket.setSoTimeout(10000);
    }
   
   public String requestType(String request) {
	   // break up string - if statement to decide next path
	   System.out.println(request);
	   Scanner scnr = new Scanner(request);
	   String requestType;
	   requestType = scnr.next();
	   String s1 = scnr.next();
	   String s2 = scnr.next();
	   System.out.println(s1+s2+requestType);
	   //for request to see a new pet
	   if(requestType.equals("nextPet")) {
		   System.out.println("reaches the nextPet method");
		   return handleRequestInfo(s1, Integer.parseInt(s2));
		   
	   }
	   if(requestType.equals("login")) {
		   
		   return loginHandler(s1, s2);
	   }
	   if(requestType.equals("contact")) {
		   
		   return contact(s1,Integer.parseInt(s2));
	   }
	   return"";
   }
   public String handleRequestInfo(String filter, int queryNumber) {
	   sendPic = true;
	   
	   return new ShelterAnimalDatabase().getAnimals().get(queryNumber).toString();
   }
   public String loginHandler(String username, String password) {
	   //read from database
	   return new UserDatabase().verifyUser(username, password);
   }
   public byte[] handleRequestImage(String filter, int queryNumber) {
	   //read from database
	   FileToString imgBase64 = new FileToString();
	   File image = new File("src/server/images/" + new ShelterAnimalDatabase().getAnimals().get(queryNumber).getimgURL()
			   + ".jpg");
	   
	   //convert image to base 64 and send as string
	 // ByteArrayOutputStream baos = new ByteArrayOutputStream();
	   byte[] byteArr = imgBase64.encodeFileToBase64Binary(image).getBytes();
	   
	   return byteArr;
   }
   public String contact(String filter, int queryNumber) {
	   //read from database
	   
	   
	   return filter + queryNumber + "ran through contact ";
   }
   public String shelterReqeust(String filter,String queryNumber) {
	   //read from database
	   
	   
	   return filter + queryNumber + "ran through shelterRequest ";
   }
   public String[] imgChunks(String img) { 
	   int length = img.length();
	   int MAX_VALUE = 24000;
	   System.out.println("running chunks");
	   String[] chunks = new String[(length/MAX_VALUE)+1];
	   
	   //read from database
	   for(int i = 0; i < length; i += MAX_VALUE) {
		   String substr = img.substring(i, Math.min(i + MAX_VALUE, length));
		   chunks[i/MAX_VALUE] =substr;
	   }
	   
	   return chunks;
   }
   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            
          //  System.out.println(in.toString());
            //OutputStream outStream = server.getOutputStream();
            //DataOutputStream dataOut = new DataOutputStream(outStream);
            
            InputStream inFromClient = server.getInputStream();
            DataInputStream in = new DataInputStream(server.getInputStream());
            
            OutputStream outStream = server.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(outStream);
           // dataOut.writeUTF(requestType(in.readUTF()));
            
          // dataOut.writeUTF(requestType(in.readUTF()));
           // if(sendPic) {
	            System.out.println("length of bytearray is "+handleRequestImage(null, queryNum).length);
	            String imgByte = new String(handleRequestImage(null, queryNum), StandardCharsets.UTF_8);
	            
	            System.out.println(imgByte);
	            //System.out.println(in.readUTF());
	            // put in a different method
	            
	           
	            //dataOut.write(handleRequestImage("a","a").length);
	            //dataOut.writeUTF(requestType(in.readUTF()));
	            System.out.println(in.readUTF());
	            
	            String[] chunks = imgChunks(imgByte);
	            dataOut.writeInt(chunks.length);
	            //dataOut.
	           for (String el: chunks) {
	        	   System.out.println(el);
	        	   dataOut.writeUTF(el);
	           }
         //}
         //sendPic = false;
         //  dataOut.write(handleRequestImage("a","a"));
          // DataOutputStream out = new DataOutputStream(server.getOutputStream());
         
      
            
              
         //   server.close();
            
         } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         } catch (IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = 8888;
      try {
         Thread t = new server(8888);
         t.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
