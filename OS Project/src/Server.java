import java.net.*;
import java.io.*;

public class Server {
    Server() {
        try {
            ServerSocket ss = new ServerSocket(200);
            System.out.println("Server Started");
            while (true) {
                Socket s = ss.accept();
                System.out.println("Connection request recieved");

                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = s.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);

                String str = br.readLine();
                String msg = str + " How are you?";
                pw.println(msg);
                s.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args){
        new Server();
    }
}
