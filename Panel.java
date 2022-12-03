package windows;
import java.awt.*;
import javax.swing.*;
import message.*;

public class Panel extends JPanel
{
    Frame thisFrame;
    JLabel legend;
    JTextField texte;
    JTextArea txt;
    public JTextArea getTxt() {
        return txt;
    }
    public void setTxt(JTextArea txt) {
        this.txt = txt;
    }
    int x;
    int y;
    int height;
    int width;

    public JLabel getLegend()
    {
        return this.legend;
    }
    public JTextField getTexte()
    {
        return this.texte;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public int getHeight()
    {
        return this.height;
    }
    public int getWidth()
    {
        return this.width;
    }

    public Panel(Frame fr)
    {
        this.thisFrame=fr;
    }

    public JPanel userPanel()
    {
        JPanel result=new JPanel();
        result.setLayout(null);
        this.x=10;
        this.y=10;
        this.width=100;
        this.height=50;
        this.legend=new JLabel("username: ");
        legend.setBounds(x,y,width,height);
        result.add(legend);
        this.texte=new JTextField();
        texte.setBounds(width,y,width*2,height);
        result.add(texte);
        y+=height+5;
        JButton valid=new JButton("enterUser");
        valid.setFocusable(false);
        valid.setBounds((width*3+x)/3,y,width,height/2);
        valid.addActionListener(new AListner(thisFrame,valid,this));
        result.add(valid);
        result.setPreferredSize(new Dimension(width*3+x,y/2+y/4+height));
        return result;
    }
    public JPanel chatPanel(Client client)
    {
        JPanel result=new JPanel();
        result.setLayout(null);
        this.x=10;
        this.y=10;
        this.width=100;
        this.height=50;
        txt=new JTextArea();
        txt.setBounds(x,y,width*4,height*5);
        result.add(txt);
        txt.setEditable(false);
        y+=txt.getHeight()+5;
        texte=new JTextField();
        texte.setBounds(x,y,width*3,height/2);
        result.add(texte);
        JButton valid=new JButton("send");
        valid.setBounds(texte.getWidth()+12,y,width,height/2);
        valid.addActionListener(new AListner(thisFrame,valid,this,client));
        result.add(valid);
        result.setPreferredSize(new Dimension(txt.getWidth()+20,y+texte.getHeight()+7));
        client.listenForMessage(this);
        return result;
    }
}