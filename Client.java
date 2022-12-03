package message;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;
import windows.*;
import javax.swing.*;

public class Client
{
    Socket socket; 
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String name;

    public Client(Socket socket,String name)
    {
        try
        {
            this.socket=socket;
            this.name=name;
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(Exception e)
        {}
    }

    public void send(String messageToSend)
    {
        try
        {
            bufferedWriter.write(name+": "+messageToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch(Exception e)
        {}
    }

    public void listenForMessage(Panel panel)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                String messageFromGroupChat;
                while(socket.isConnected())
                {
                    try
                    {
                        messageFromGroupChat=bufferedReader.readLine();
                        if(messageFromGroupChat.equals("")==false||messageFromGroupChat.equals(" ")==false||messageFromGroupChat.equals("  ")==false||messageFromGroupChat.equals("   ")==false)
                        {
                            panel.getTxt().append(messageFromGroupChat+"\n");
                        }
                    }
                    catch(Exception e)
                    {}
                }
            }
        }

        ).start();
    }
}