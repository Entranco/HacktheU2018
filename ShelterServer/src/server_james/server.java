package server_james;



import java.net.*;
import java.io.*;

//import 	android.graphics.Bitmap;




public class server extends Thread {
   private ServerSocket serverSocket;

  // BitMap bit;
   int users;
   public server(int port) throws IOException {
      serverSocket = new ServerSocket(8888);
      //serverSocket.setSoTimeout(10000);
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
           System.out.println(in.readUTF());
           
           dataOut.writeUTF("sent from server");
           
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