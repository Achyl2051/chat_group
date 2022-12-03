package windows;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import message.*;

public class AListner implements ActionListener
{
    JButton b;
    Panel p;
    Frame f;
    Client client;
    BufferedWriter bufferedWriter;
    public AListner(Frame f,JButton b,Panel p)
    {
        this.b=b;
        this.p=p;
        this.f=f;
    }
    public AListner(Frame f,JButton b,Panel p,Client client)
    {
        this.b=b;
        this.p=p;
        this.f=f;
        this.client=client;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b)
        {
            if(b.getText().equals("enterUser"))
            {
                String username=p.getTexte().getText();
                try
                {
                    Socket socket=new Socket("localhost",1234);
                    Client client=new Client(socket,username);
                    f.hide();
                    try 
                    {
                        this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        bufferedWriter.write(username+"\n");
                        bufferedWriter.flush();
                    } 
                    catch (Exception exc) 
                    {
                        // TODO: handle exception
                    }
                    new Frame().createChat(client);
                    System.out.println(username);
                }
                catch(Exception exc)
                {}
            }
            else if(b.getText().equals("send"))
            {
                client.send(p.getTexte().getText());
                p.getTexte().setText("");
            }
        }
    }
}