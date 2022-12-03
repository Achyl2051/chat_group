package windows;
import java.awt.*;
import javax.swing.*;
import message.*;

public class Frame extends JFrame
{
    public void createUserName()
    {
        Panel jo=new Panel(this);
        this.add(jo.userPanel());
        this.setTitle("index");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void createChat(Client client)
    {
        Panel jo=new Panel(this);
        this.add(jo.chatPanel(client));
        this.setTitle("chat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}