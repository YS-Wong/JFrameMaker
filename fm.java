 class com {
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
public class frm{
  public static void main(String[] args){
    JFrame jf=new JFrame();
    jf.setDefaultCloseOperation(3);
    try{
      UIManager.setLookAndFeel
        (UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){e.printStackTrace();}
    jf.setLayout(null);
    jf.setBounds(30,30,400,270);
    JButton jb1=new JButton();
    jf.add(jb1);
    jb1.setBounds(114,97,150,30);
    jb1.setCursor(new Cursor(5));
    jf.setVisible(true);}
}

public class fm {
	static JFrame frm,FRM;
	static JPanel WEL,NEW,ADD,SET,SEE,LOG;
	static JButton see3;
	static TextArea see;
	static char c;
	static JTabbedPane panel;
	static ArrayList<com> a;
	static com comPointer;
	
	void nf(String title){
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
	void 初始化(){
		comPointer=null;
		see.setText(null);
		a.clear();
	}
	fm(){
		c='"';
		a=new ArrayList<com>();
		final JTextField set2=new JTextField();
		final JLabel set1=new JLabel("Set JFrame");
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
					初始化();
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
		//=====================================================
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
			public void actionPerformed(ActionEvent e){
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
						"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);}
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
				see.append("jf.setBounds("+FRM.getLocation().x+","+FRM.getLocation().y+","+FRM.getWidth()+","+FRM.getHeight()+");\n");
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
		//=====================================================
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
		//=====================================================
		SEE=new JPanel();
		SEE.setLayout(null);
		JPanel see1=new JPanel();
		see1.setLayout(new GridLayout(1,1));
		see=new TextArea();see.setEditable(false);
		see.setBackground(Color.white);
		see.setFont(new Font("Default",2,14));
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
				"Imformation",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
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
							"\npublic class frm{\npublic static void main(String[] args){\n"+see.getText()+"}\n}");
					bw.close();
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
							"Saved on Desktop sussesfully\nThe name of the class is "+c+"frm"+c,
					"Imformation",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
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
		//=====================================================
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
		LOG.add(new JLabel("Copyright ? 2015 WangYixin's Original Software."));
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
						"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
					}
				}catch(Exception E){
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
					"Visit the web page fails",
					"Error",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
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
					byte[] b=log2.getText().getBytes();
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
						"Imformation",JOptionPane.INFORMATION_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
					}
				}catch(Exception e1){
					log2.setText(null);
					Object[] options = {"I knew"};
					JOptionPane.showOptionDialog(null,
					"The password is not true",
					"Imformation",JOptionPane.INFORMATION_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				}
			}
		});
		}
	void frm(){
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
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new fm().frm();
	}
}
