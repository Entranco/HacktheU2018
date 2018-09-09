package hacktheU;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.*;
public class clientandroid {

	public static void main(String[] args) {
		Scanner testInput = new Scanner(System.in);
		String input;
		String img = "";
		int dataNumber;
		try {
			while(true) {
			 Socket client = new Socket("localhost", 8888);
			 System.out.println("connected successfully to " + client.getRemoteSocketAddress());
			 OutputStream outStream =  client.getOutputStream();
	         DataOutputStream dataOut = new DataOutputStream(outStream);
	         
	         System.out.println("send request? y/n");
	         input = testInput.nextLine();
	         dataOut.writeUTF(input);
			 
			 InputStream inFromServer = client.getInputStream();
	         DataInputStream in = new DataInputStream(inFromServer);
			// dataOut.writeUTF(input);
	       //  System.out.println("B" +in.toString());
	        // System.out.println("a" +in.readInt());
	         
	         	 int loop = in.readInt();
	         	 System.out.println(loop);
	         	 
	         	 for(int i = 0; i < loop; i ++) {
	         		img = img.concat(in.readUTF());
	         	 	 //System.out.println(img);
	         	 	
	         	 }
	         	 System.out.println(img);
	         	
	         	 //System.out.println(in.readUTF());
			  //System.out.println(in.readUTF());
			 // byte[] byteArr = in.readAllBytes();
			//  System.out.println(byteArr);
			//  String imgByte = new String(byteArr, StandardCharsets.UTF_8);
			//  System.out.println("a" + imgByte);
		         //System.out.println(in.readUTF());
		         //System.out.println(in.readUTF());
			}
		}catch (IOException e) {
	         e.printStackTrace();
	      }

}
}
