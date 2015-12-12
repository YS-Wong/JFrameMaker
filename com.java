import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class com {
	private JComponent com;
	private static JFrame FRM;
	private Point p;
	private Object resizeX,resizeY,c,xy;
	private String kind;
	private MouseAdapter 拖拽,鼠标效果;
	com(String kind,String text){
		c='"';
		this.kind=kind;
		FRM=fm.FRM;
		switch(this.kind){
		case "JButton":{com=new JButton(text);break;}
		case "JLabel":{com=new JLabel(text);com.setOpaque(true);break;}
		case "JTextField":{com=new JTextField(text);break;}
		case "JTextArea":{com=new JTextArea(text);break;}
		case "JCheckBox":{com=new JCheckBox(text);break;}
		}
		FRM.add(com);
		com.setBounds(0,0,150,30);
		com.setBackground(Color.WHITE);
		拖拽=new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				if(com.getWidth()-e.getX()<10&&com.getHeight()-e.getY()>10)
					FRM.setCursor(Cursor.getPredefinedCursor(11));
				else if(com.getHeight()-e.getY()<10&&com.getWidth()-e.getX()>10)
					FRM.setCursor(Cursor.getPredefinedCursor(9));
				else
					FRM.setCursor(Cursor.getPredefinedCursor(13));
			}
			public void mousePressed(MouseEvent e) {
				if(com.getWidth()-e.getX()<10&&com.getHeight()-e.getY()>10){
					xy=com.getWidth()-e.getX();
					resizeX=true;
					resizeY=false;
				}else if(com.getHeight()-e.getY()<10&&com.getWidth()-e.getX()>10){
					xy=com.getHeight()-e.getY();
					resizeY=true;
					resizeX=false;
				}else{
					p=e.getPoint();
					resizeX=false;
					resizeY=false;
				}
			}
			public void mouseDragged(MouseEvent e) {
				com.setEnabled(false);
				if((boolean)resizeX){
					if(e.getX()+(int)xy>35)com.setSize(e.getX()+(int)xy,com.getHeight());
					FRM.setCursor(Cursor.getPredefinedCursor(11));
				}else if((boolean)resizeY){
					if(e.getY()+(int)xy>20)com.setSize(com.getWidth(),e.getY()+(int)xy);
					FRM.setCursor(Cursor.getPredefinedCursor(9));
				}else{
					com.setLocation(com.getX()+e.getX()-p.x,com.getY()+e.getY()-p.y);
					FRM.setCursor(Cursor.getPredefinedCursor(13));
				}
			}
		};
		鼠标效果=new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				e.getComponent().setEnabled(false);
			}
			public void mouseExited(MouseEvent e) {
				e.getComponent().setEnabled(true);
				FRM.setCursor(Cursor.getPredefinedCursor(0));
			}
		};
		com.addMouseListener(拖拽);
		com.addMouseMotionListener(拖拽);
		com.addMouseListener(鼠标效果);
	}
	public String getFirstName(){
		String fn = null;
		switch(this.kind){
		case "JButton":{fn="jb";break;}
		case "JLabel":{fn="jl";break;}
		case "JTextField":{fn="jtf";break;}
		case "JTextArea":{fn="jta";break;}
		case "JCheckBox":{fn="jc";break;}
		}
		return fn;
	}
	public String toString(int i){
		return kind+" "+getFirstName()+i+"=new "+kind+"("+c+getText()+c+");\n"+"jf.add("+getFirstName()+i+");\n" +
				getFirstName()+i+".setBounds("+com.getX()+","+com.getY()+","+com.getWidth()+","+com.getHeight()+");\n";
	}
	public void setText(String text){
		switch(this.kind){
		case "JButton":{((JButton) com).setText(text);break;}
		case "JLabel":{((JLabel) com).setText(text);break;}
		case "JTextField":{((JTextField) com).setText(text);break;}
		case "JTextArea":{((JTextArea) com).setText(text);break;}
		case "JCheckBox":{((JCheckBox) com).setText(text);break;}
		}
	}
	public String getText(){
		String text = null;
		switch(this.kind){
		case "JButton":{text=((JButton) com).getText();break;}
		case "JLabel":{text=((JLabel) com).getText();break;}
		case "JTextField":{text=((JTextField) com).getText();break;}
		case "JTextArea":{text=((JTextArea) com).getText();break;}
		case "JCheckBox":{text=((JCheckBox) com).getText();break;}
		}
		return text;
	}
	public String getKind(){
		return this.kind;
	}
	private void removeListener(){
		com.removeMouseListener(拖拽);
		com.removeMouseMotionListener(拖拽);
		com.removeMouseListener(鼠标效果);
	}
	public void delete(){
		this.removeListener();
		com.setVisible(false);
		this.com=null;
		this.c=null;
		this.kind=null;
		this.p=null;
		this.xy=null;
		this.拖拽=null;
		this.鼠标效果=null;
		this.resizeX=null;
		this.resizeY=null;
	}
}
