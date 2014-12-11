package cmpe272.editor;
import cmpe272.app.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import cmpe272.app.ClassCreator;

import java.awt.Component;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTree;

import jsyntaxpane.DefaultSyntaxKit;

import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Date;


public class EditorFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
    private JFrame jEditor;
    //final JEditorPane codeEditor;
    private  JEditorPane codeEditor;
    public JTextArea consoleTextArea;
    public static PrintStream standardOut;
    //private ClassCreator cc;
    
    public EditorFrame(String x){
    	System.out.println(x);
    }

	public EditorFrame() {
		System.out.println("Creating UI components");
		JFrame f = new JFrame(EditorFrame.class.getName());
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        DefaultSyntaxKit.initKit();
        try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //System.out.print(DefaultSyntaxKit.EndOfLineStringProperty);

        codeEditor = new JEditorPane();
        
        JMenuBar myBar = new JMenuBar();
        JMenu myMenu = getFileMenu();
        myBar.add(myMenu); 
        f.setJMenuBar(myBar);
        f.setTitle("Speech Coder");
        JScrollPane scrPane = new JScrollPane(codeEditor);
        
        
        
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		f.setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.18);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOneTouchExpandable(true);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPane_1);
		splitPane.setRightComponent(scrPane);
		
		consoleTextArea = new JTextArea(50,10);
		setCustomConsole(consoleTextArea);
		JScrollPane scrPane1 = new JScrollPane(consoleTextArea);
		
		//formatting
		consoleTextArea.setBackground(Color.BLACK);
		consoleTextArea.setForeground(Color.LIGHT_GRAY);
		
		splitPane_1.setRightComponent(scrPane1);
		
		JButton btnStartButton = new JButton("Speech Recognition mode");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Starting coder now");
				consoleTextArea.setText("");
				startCoder();
				
			}
		});
		splitPane_1.setLeftComponent(btnStartButton);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{splitPane}));
		
		codeEditor.setContentType("text/java");
	    //codeEditor.setText(code);
	        
	        
	    f.setSize(800, 600);
	    f.setVisible(true);
	    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	}
	
	
	public JEditorPane getJEditorPane()
	{
		return codeEditor;
	}
	
	/**
	 * Update the jSyntaxpane.
	 */
	public void updateUI1(String code, int lineno) {
//		try {
//		      Document doc = this.codeEditor.getDocument();
//		      System.out.println(doc.getLength());
//		      code=code.replace('\\', '\n');
//		      if(lineno !=-1){
//		    	  
//		    	  doc.insertString(lineno, code, null);		    	  }
//		      else
//		      { doc.insertString(doc.getLength(), code, null);
//		      }
//		     } catch(BadLocationException exc) {
//		      exc.printStackTrace();
//		   }	
		
		this.codeEditor.setText(code);
	}
	
	
	/**
	 * Start Speech Coding
	 */
	public void startCoder()
	{
		System.out.println("Inside startCoder");
		
		
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            	ClassCreator cc = new ClassCreator();
            	cc.createClass();
                //while (true) {
                    System.out.println("Time now is " + (new Date()));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        ex.printStackTrace();
//                    }
//                }
            }
        });
        thread.start();
		
	}
	
	//setting Custom Console as a Console
	void setCustomConsole(JTextArea x)
	{
		JTextArea textArea = x;
		textArea.setEditable(false);
		standardOut = System.out;
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea),true);
		System.setOut(printStream);
		standardOut.println("CustomConsole is set to JTextPane");
		//System.out.println("CustomConsole is set to JTextPane");
		//System.setErr(printStream);
	}
	
	
	//Adding Menu to frame
	private JMenu getFileMenu() {
	      JMenu myMenu = new JMenu("File");
	      JMenuItem myItem = new JMenuItem("Open");
	      myItem.addActionListener(this);
	      myMenu.add(myItem);

	      myItem = new JMenuItem("Save");
	      myItem.addActionListener(this);
	      myMenu.add(myItem);
	      
	      myItem = new JMenuItem("Compile/Run");
	      myItem.addActionListener(this);
	      myMenu.add(myItem);
	      return myMenu;
	   }
	
	//Adding Command Listener to Menu
	   public void actionPerformed(ActionEvent e) {
	      String cmd = ((AbstractButton) e.getSource()).getText();
	      try {
	         if (cmd.equals("Open")) {
	        	 openFile("Test");
	         } else if (cmd.equals("Save")) 
	        	 saveFile("Test");
	         else if (cmd.equals("Compile/Run"))
	         {
	        	 compileApp("Test");
	        	 runApp("Test");
	         } 	 
	      } catch (Exception f) {
	      	 f.printStackTrace();
	      }
	   }
	   
	   
	public void openFile(String filename){
		FileReader in;
		try {
			in = new FileReader(filename+".java");
		
        char[] buffer = new char[2048];
        int n = in.read(buffer);
        String text = new String(buffer, 0, n);
        codeEditor.setText(text);
        in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   
	
	public void saveFile(String filename){
		File f= new File(filename+".java");
		FileWriter out;
		try {
			out = new FileWriter(f.getName());
			System.out.println("Saving file "+filename+".java .....");
			out.write(codeEditor.getText());
	        out.close();
	        if(f.exists())
			{	
			System.out.println(filename+".java Saved Successfully.");
			}
	        else
	        {
	        	System.out.println(filename+".java save operation failed");
	        }	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	} 
	
	public void compileApp(String filename)
	{
		System.out.println("I have reached compileApp");
		String s="";
		File f= new File(filename+".java");
		File f1= new File(filename+".class");
		if(!f.exists())
			System.out.println("Java File not found");
		else
		{
		try {
			System.out.println("Compliling Java program "+f.getName()+".......");
			Process p1=Runtime.getRuntime().exec("C:\\Program Files\\Java\\jdk1.7.0_65\\bin\\javac "+f.getName());
			if(f1.exists()&& !f.isDirectory())
			{	
				System.out.println(filename+".class Created.");
			}	
			else
			{
				System.out.println("Error in Complilation.");
				BufferedReader error = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
				while ((s = error.readLine()) != null) {
	                System.out.println(s);
	            }			
			}
			int exitVal = p1.waitFor();
			//System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void  runApp(String filename)
	{
		System.out.println("I have reached runApp");
		String s="";
		File f= new File(filename+".java");
		File f1= new File(filename+".class");
		if(!f.exists())
			System.out.println("Java File not found");
		else 
		if(!f1.exists())
		{
			System.out.println("Compile file first");
		}
		else
		{
			try {
				System.out.println("Running Java program "+filename+".java.......\n --------------output------------");
				Process p = Runtime.getRuntime().exec("java "+filename);			
				BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = output.readLine()) != null) {
                System.out.println(s);
            }
			while ((s = output.readLine()) != null) {
                System.out.println(s);
            }
			int exitVal = p.waitFor();
			//System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//SpeechCoder sc = new SpeechCoder();
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
}
