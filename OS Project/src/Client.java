import javax.swing.*;
import java.lang.reflect.Executable;
import java.net.*;
import java.io.*;

public class Client {
    Client(){
        try{
            Socket s=new Socket("localhost",200);
            InputStream is=s.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            OutputStream os=s.getOutputStream();
            PrintWriter pw=new PrintWriter(os,true);
            String name=JOptionPane.showInputDialog("Enter your name: ");
            pw.println(name);

            String msg=br.readLine();
            JOptionPane.showMessageDialog(null,msg);
            s.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args){
        new Client();
    }
}