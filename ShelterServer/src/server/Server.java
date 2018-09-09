package server;

import java.net.*;
import java.util.Scanner;
import java.io.*;

//import 	android.graphics.Bitmap;




public class Server extends Thread {
   private ServerSocket serverSocket;

  // BitMap bit;
   int users;
   public Server(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      //serverSocket.setSoTimeout(10000);
   }
   
   public String requestType(String request) {
	   // break up string - if statement to decide next path
	   Scanner scnr = new Scanner(request);
	   String requestType;
	   requestType = scnr.next();
	   String s1 = scnr.next();
	   String s2 = scnr.next();
	   //for request to see a new pet
	   if(requestType.equals("nextPet")) {
		   
		   return handleRequestInfo(s1,s2) + " " + handleRequestImage(s1,s2);
		   
	   }
	   if(requestType.equals("login")) {
		   
		   return loginHandler(s1,s2);
	   }
	   if(requestType.equals("contact")) {
		   
		   return contact(s1,s2);
	   }
	   return"";
   }
   public String handleRequestInfo(String filter,String queryNumber) {
	   //read from database
	   
	   
	   return filter + queryNumber + "ran through handleRequest ";
   }
   public String loginHandler(String userName,String password) {
	   //read from database
	   
	   
	   return userName + password + "ran through loginHandler ";
   }
   public String handleRequestImage(String filter,String queryNumber) {
	   //read from database
	   
	   
	   return filter + queryNumber + "ran through requestImage ";
   }
   public String contact(String filter,String queryNumber) {
	   //read from database
	   
	   
	   return filter + queryNumber + "ran through contact ";
   }
   public String shelterReqeust(String filter,String queryNumber) {
	   //read from database
	   
	   
	   return filter + queryNumber + "ran through shelterRequest ";
   }
   
   public void run() {
      while(true) {
         try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
           
            OutputStream outStream = server.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(outStream);
           
           
           dataOut.writeUTF(requestType(in.readUTF()));
           
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
      try {
         Thread t = new Server(8888);
         t.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
