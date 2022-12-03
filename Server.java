package message;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket)
    {
        this.serverSocket=serverSocket;
    }

    public void startServer()
    {
        try
        {
            while(!serverSocket.isClosed())
            {
                Socket socket=serverSocket.accept();
                System.out.println("A new client has connected");
                GestionClient client=new GestionClient(socket);
                Thread thread=new Thread(client);
                thread.start();
            }
        }
        catch(Exception e)
        {}
    }
    
    public static void main(String[] args)throws Exception
    {
        ServerSocket serverSocket=new ServerSocket(1234);
        Server server=new Server(serverSocket);
        server.startServer();
    }
}