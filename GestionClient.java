package message;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;

public class GestionClient implements Runnable
{
    Socket socket;
    public static ArrayList<GestionClient> clients=new ArrayList<GestionClient>(); 
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String clientName;

    public GestionClient(Socket socket)
    {
        try
        {
            this.socket=socket;
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientName=bufferedReader.readLine();
            clients.add(this);
            messageForAll("SERVER: "+clientName+" dia tafiditra");
        }
        catch(Exception e)
        {}
    }

    public void run()
    {
        String messageFromClient;
        while(socket.isConnected())
        {
            try
            {
                messageFromClient=bufferedReader.readLine();
                messageForAll(messageFromClient);
            }
            catch(Exception e)
            {}
        }
    }

    public void messageForAll(String message)
    {
        for(GestionClient client:clients)
        {
            try
            {
                if(!client.clientName.equals(clientName))
                {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            }
            catch(Exception e)
            {}
        }
    }
}