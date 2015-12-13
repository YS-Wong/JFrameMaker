import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class fm {
	static JFrame frm,FRM;
	static JPanel NEW,ADD,SET,SEE,LOG;
	static JButton see3;
	static TextArea see;
	static char c;
	static JTabbedPane panel;
	static ArrayList<com> a;
	static com comPointer;
	
	void nf(String title){//This method is used to create a new JFrame which we add Components to 
		FRM=new JFrame(title+"-Look And Feel");
		FRM.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		FRM.setBounds(30,30,400,270);
		FRM.setLayout(null);
		see.append("JFrame jf=new JFrame("+c+title+c+");\n" +
				"jf.setDefaultCloseOperation(3);\ntry{UIManager.setLookAndFeel" +
				"\n(UIManager.getSystemLookAndFeelClassName());" +
				"\n}catch(Exception e){e.printStackTrace();}"+
					"\njf.setLayout(null);\n");
		FRM.setVisible(true);
	}
	void initialize(){
		comPointer=null;
		see.setText(null);//"see" is a JTextArea which shows Swing Code
		a.clear();
	}
	fm(){//All Component of the main JFrame produced by this method include five JPanels
		c='"';
		a=new ArrayList<com>();
		final JTextField set2=new JTextField();
		final JLabel set1=new JLabel("Set JFrame");
		//=====================================================NEW
		NEW=new JPanel();
		GridLayout ng=new GridLayout(17,0);
		JLabel new8=new JLabel("Create a new JFrame");
		new8.setFont(new Font("DialogInput",0,15));
		NEW.add(new8);
		NEW.add(new JLabel("Title of Frame"));
		final JTextField new5=new JTextField();NEW.add(new5);
		final JCheckBox new7=new JCheckBox("Unresizable——Make your JFrame cann't be resized");NEW.add(new7);
		NEW.add(new JLabel());
		final JButton new6=new JButton("Create");NEW.add(new6);
		NEW.setLayout(ng);
		new6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					initialize();
					nf(new5.getText());
					set2.setText(new5.getText());
					if(new7.isSelected()){
						see.append("jf.setResizable(false);\n");}
					panel.removeAll();
					panel.insertTab("add",null,ADD,null,0);
					panel.insertTab("set",null,SET,null,1);
					panel.insertTab("About me",null,LOG,null,2);
				}catch(Exception E){
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
					"InputType is dangerous!",
					"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				}
			}
		});
		//=====================================================ADD
		ADD=new JPanel();
		ADD.setLayout(ng);
		JLabel add4=new JLabel("Add a new Component");
		add4.setFont(new Font("DialogInput",0,15));
		ADD.add(add4);
		ADD.add(new JLabel("Kind of Component"));
		String[] comp=new String[]{"JButton","JLabel","JTextField","JTextArea","JCheckBox"};
		final JComboBox<?> add1=new JComboBox<Object>(comp);ADD.add(add1);
		ADD.add(new JLabel("Text of Component"));
		final JTextField add2=new JTextField();ADD.add(add2);
		ADD.add(new JLabel());
		JButton add3=new JButton("add");ADD.add(add3);
		final JButton add5=new JButton("delete");ADD.add(add5);
		add5.setEnabled(false);
		JButton add6=new JButton("finish");ADD.add(add6);
		add3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){//Create Component and "com" is a public class
				try {
					if(add1.getSelectedIndex()==0){
						a.add(new com("JButton",add2.getText()));
					}
					if(add1.getSelectedIndex()==1){
						a.add(new com("JLabel",add2.getText()));
					}
					if(add1.getSelectedIndex()==2){
						a.add(new com("JTextField",add2.getText()));
					}
					if(add1.getSelectedIndex()==3){
						a.add(new com("JTextArea",add2.getText()));
					}
					if(add1.getSelectedIndex()==4){
						a.add(new com("JCheckBox",add2.getText()));
					}
						comPointer=a.get(a.size()-1);
						set1.setText("Set "+comPointer.getKind());
						set2.setText(add2.getText());
						add2.setText(null);
					} catch (Exception e1) {
						Object[] options = {"I knew"};
						JOptionPane.showOptionDialog(null,
						"InputType is dangerous!",
						"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,
						null,options,options[0]);}
			}
		});
		add5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				a.get(a.size()-1).delete();
				a.remove(a.size()-1);
				if(a.isEmpty()){
					add5.setEnabled(false);
					comPointer=null;
				}else
					comPointer=a.get(a.size()-1);
			}
		});
		add6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panel.removeAll();
				panel.insertTab("Code",null,SEE,null,0);
				panel.insertTab("About me",null,LOG,null,1);
				panel.insertTab("new",null,NEW,null,0);
				see.append("jf.setBounds("+FRM.getLocation().x+","+FRM.getLocation().y+","
					+FRM.getWidth()+","+FRM.getHeight()+");\n");
				if(!a.isEmpty()){
					for(int i=0;i<a.size();i++){
						see.append(a.get(i).toString(i+1));
						a.get(i).delete();
					}
				}
				new6.setEnabled(true);
				new5.setText(null);
				new7.setSelected(false);
				add5.setEnabled(false);
				see3.setEnabled(true);
				see.append("jf.setVisible(true);");
				FRM.setVisible(false);
				FRM=null;
			}
		});
		//=====================================================SET
		SET=new JPanel();
		SET.setLayout(ng);
		SET.add(set1);
		set1.setFont(new Font("DialogInput",0,15));
		SET.add(new JLabel("Text"));
		SET.add(set2);
		set2.getDocument().addDocumentListener(new DocumentListener(){
	        public void changedUpdate(DocumentEvent e) {
	        	if(comPointer==null)FRM.setTitle(set2.getText()+"-Look and Feel");
	        	else comPointer.setText(set2.getText());
	        }
	        public void insertUpdate(DocumentEvent e) {
	        	if(comPointer==null)FRM.setTitle(set2.getText()+"-Look and Feel");
	        	else comPointer.setText(set2.getText());
	        }
	        public void removeUpdate(DocumentEvent e) {
	        	if(comPointer==null)FRM.setTitle(set2.getText()+"-Look and Feel");
	        	else comPointer.setText(set2.getText());
	        }
	    });
		//=====================================================SEE
		SEE=new JPanel();
		SEE.setLayout(null);
		JPanel see1=new JPanel();
		see1.setLayout(new GridLayout(1,1));
		see=new TextArea();see.setEditable(false);
		see.setBackground(Color.white);
		see.setForeground(Color.DARK_GRAY);
		see1.add(see);
		SEE.add(see1);
		JButton see2=new JButton("Copy");
		SEE.add(see2);
		see3 =new JButton("Save as File");
		SEE.add(see3);
		see3.setEnabled(false);
		see2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StringSelection stsel = new StringSelection("import javax.swing.*;" +
						"\npublic class frm{\npublic static void main(String[] args){\n"+see.getText()+"}\n}");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
				Object[] options = {"I knew"};
				JOptionPane.showOptionDialog(null,
				"Copied sussesfully",
				"Imformation",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
			}
		});
		see3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				see3.setEnabled(false);
				File txt=new File(System.getProperties().getProperty("user.home")+"/桌面/haha.java");
				try {
					txt.createNewFile();
					BufferedWriter bw=new BufferedWriter(new FileWriter(txt));
					bw.write("import javax.swing.*;" +
							"\npublic class frm{\npublic static void main(String[] args){\n"
							+see.getText()+"}\n}");
					bw.close();
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
							"Saved on Desktop sussesfully\nThe name of the class is "+c+"frm"+c,
					"Imformation",JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				}catch (Exception E){
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
					"Saved failed",
					"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				}
			}
		});
		see1.setBounds(0,0,330,350);
		see2.setBounds(0,352,330,23);
		see3.setBounds(0,375,330,23);
		//=====================================================LOG
		LOG=new JPanel();
		LOG.setLayout(new FlowLayout(FlowLayout.CENTER));
		LOG.add(new JLabel("Login to enjoy more services:"));
		final JTextField log2=new JTextField(50);LOG.add(log2);
		final JButton log3=new JButton("  L  O  G  I  N  ");LOG.add(log3); 
		LOG.add(new JLabel("      * * * * * * * * * * *      "));
		LOG.add(new JLabel("JFrameMaker as a Software-development tools which"));
		LOG.add(new JLabel("independent developmented  by WangYixin"));
		LOG.add(new JLabel("and generate Java Swing code,"));
		LOG.add(new JLabel("it will be more and more useful for Swing Programmers."));
		LOG.add(new JLabel("You can change the Size and Location of your own JFrame"));
		LOG.add(new JLabel("and dominate your own JComponent with JFrameMaker."));
		LOG.add(new JLabel("      * * * * * * * * * * *      "));
		LOG.add(new JLabel("   Plaease click here to learn more……   "));
		final JLabel log1=new JLabel("===============My Web page===============");
		log1.setFont(new Font("Serif",0,15));log1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		LOG.add(log1);
		LOG.add(new JLabel("      * * * * * * * * * * *      "));
		LOG.add(new JLabel("Copyright © 2015 WangYixin's Original Software."));
		LOG.add(new JLabel("All rights reserved."));
		log1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					URI uri=new URI("http://user.qzone.qq.com/2826509864/main");
					Desktop dtp=Desktop.getDesktop();
					if(Desktop.isDesktopSupported()&&dtp.isSupported(Desktop.Action.BROWSE)){
						dtp.browse(uri);
					}else{
						Object[] options = {"I knew"};
						JOptionPane.showOptionDialog(null,
						"Visit the web page fails",
						"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,
						null,options,options[0]);
					}
				}catch(Exception E){
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
					"Visit the web page fails",
					"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,
					null,options,options[0]);
				}
			}
			public void mouseEntered(MouseEvent e) {
				log1.setForeground(Color.RED);
			}
		    public void mouseExited(MouseEvent e) {
		    	log1.setForeground(Color.BLACK);
		    }
		});
		log3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					double password=0;
					byte[] b=log2.getText().getBytes();//Log in
					for(int i=0;i<b.length;i++){
						password=password+b[i];	
					}
					System.out.println(password);
					if(password==719){
						log3.setEnabled(false);
						log2.setEditable(false);
					}else{
						log2.setText(null);
						Object[] options = {"I knew"};
						JOptionPane.showOptionDialog(null,
						"The password is not true",
						"Imformation",JOptionPane.INFORMATION_MESSAGE,JOptionPane.ERROR_MESSAGE,
						null,options,options[0]);
					}
				}catch(Exception e1){
					log2.setText(null);
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
					"The password is not true",
					"Imformation",JOptionPane.INFORMATION_MESSAGE,JOptionPane.ERROR_MESSAGE,
					null,options,options[0]);
				}
			}
		});
		}
	void start(){//Program start from here
		frm=new JFrame();
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension sc=kit.getScreenSize();
		int x=(sc.width-350)/2;
		int y=(sc.height-470)/2;
		frm.setBounds(x,y,350,470);
		frm.setTitle("JFrameMaker");
		frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frm.setResizable(false);
		frm.setLayout(null);
		panel=new JTabbedPane();
		panel.addTab("new",NEW);
		panel.addTab("About me",LOG);
		frm.addWindowListener(new WindowAdapter(){
		    public void windowClosing(WindowEvent e){
		    	Object[] options = {"Yes","Cancel"};
				int i=JOptionPane.showOptionDialog(null,
				"Exit JFrameMaker?",
				"Confirm",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		    	if(JOptionPane.OK_OPTION ==i)
		    		System.exit(0);
		    	}
		    });
		frm.add(panel);
		panel.setBounds(5,5,335,430);
		frm.setVisible(true);
	}
	public static void main(String[] args)throws Exception{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//Change Look-and-feel
		new fm().start();
	}
}
