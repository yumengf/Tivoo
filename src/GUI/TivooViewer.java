package GUI;

//import TivooModel;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.jdom.JDOMException;

//import GUI.HTMLexample.LinkFollower;





public class TivooViewer extends JFrame{
	protected JList myOutput;
    protected TivooModel myModel;
    protected String myTitle;
    protected JTextField myMessage;
    protected int myModelRoundTripCounter;
    private JEditorPane myPage;
    private JTextField myURLDisplay;
    private JButton myBackButton;
    private JButton myNextButton;
    private JButton myHomeButton;
    private JButton myAddButton;
    private String myHome;

    protected static final JFileChooser ourChooser = new JFileChooser(".");
    protected static final Font ourFont = new Font("SansSerif", Font.BOLD, 12);
    protected static final Font ourFixedFont = new Font("Monospaced", Font.BOLD, 12);
    public static final Dimension SIZE = new Dimension(800, 600);
    
    public TivooViewer(String title, TivooModel model) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setModel(model);
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new BorderLayout());
        setTitle(title);
        myTitle = title;

        add(makeInputPanel(), BorderLayout.NORTH);
        panel.add(makeOutput(), BorderLayout.CENTER);
        panel.add(makeMessage(), BorderLayout.SOUTH);
        
        
        
        // must be first since other panels may refer to page
        add(makePageDisplay(), BorderLayout.CENTER);
        
        makeMenus();
        pack();
        setVisible(true);
    }
    
    public void setModel(TivooModel model) {
        myModel = model;
        model.addView(this);
    }
    protected JPanel makeMessage() {
        JPanel p = new JPanel(new BorderLayout());
        myMessage = new JTextField(30);
        p.setBorder(BorderFactory.createTitledBorder("message"));
        p.add(myMessage, BorderLayout.CENTER);
        return p;
    }

    protected JPanel makeOutput() {

        JPanel p = new JPanel();
        BoxLayout layout = new BoxLayout(p, BoxLayout.PAGE_AXIS);
        p.setLayout(layout);
//        for (int k = 0; k < GUESSES; k++) {
//            myGuesses[k] = new JottoPanel();
//            p.add(myGuesses[k]);
//            p.add(Box.createRigidArea(new Dimension(0, 5)));
//        }
//        myGuesses[0].setEnabled(true);
        p.setBorder(BorderFactory.createTitledBorder("guesses"));
        return p;

    }
    private JComponent makeInputPanel ()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(makeNavigationPanel(), BorderLayout.NORTH);
        return panel;
    }
    private JComponent makeNavigationPanel ()
    {
        JPanel panel = new JPanel();

        myHomeButton = new JButton("Home");
        myHomeButton.addActionListener(new HomeAction());
        panel.add(myHomeButton);

        // if user presses return, load/show the URL
        myURLDisplay = new JTextField(35);
        myURLDisplay.addActionListener(new ShowPageAction());
        panel.add(myURLDisplay);

        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ShowPageAction());
        panel.add(goButton);

        return panel;
    }
    private class HomeAction implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
		    showPage(myHome);
		    
		}
	}
    private class ShowPageAction implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
		    showPage(myURLDisplay.getText());
		    //myModel.addToHistory(myURLDisplay.getText());
		}
	}
    public void showPage (String url)
    {
        try
        {
        	// let user leave off initial protocol
//            if (! url.startsWith("http"))
//            {
//                url = "http://" + url;
//            }
            // check for a valid URL before updating model, view
            new URL(url);
            // must be a valid URL, now update model and display results
            //myModel.go(url);
            update(url);
        }
        catch (MalformedURLException e)
        {
            showError("Could not load " + url);
        }
    }



    protected JMenu makeFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(new AbstractAction("Open") {
            public void actionPerformed(ActionEvent ev) {
                int retval = ourChooser.showOpenDialog(null);
                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = ourChooser.getSelectedFile();
                    try {
						myModel.loadAndParseFile(file);
						
					} catch (JDOMException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });

        fileMenu.add(new AbstractAction("Quit") {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        return fileMenu;
    }
    protected JMenu makeViewMenu() {
        JMenu fileMenu = new JMenu("Preview");

        fileMenu.add(new AbstractAction("Month Output") {
            public void actionPerformed(ActionEvent ev) {
               myModel.monthView();
            }
        });
        fileMenu.add(new AbstractAction("Week Output") {
            public void actionPerformed(ActionEvent ev) {
               myModel.weekView();
            }
        });


        return fileMenu;
    }
    
    public void setHome(String s)
    {
    	myHome = s;
    }
    protected void makeMenus() {
        JMenuBar bar = new JMenuBar();
        bar.add(makeFileMenu());
        bar.add(makeFilterMenu());
        bar.add(makeViewMenu());
        setJMenuBar(bar);
    }
    public String getInput()
    {
    	String s = (String) JOptionPane.showInputDialog(this,
				"Provide a keyword to filter:\n");
    	return s;
    }
    
    protected JMenu makeFilterMenu() {
        JMenu gameMenu = new JMenu("Filter");

        gameMenu.add(new AbstractAction("by Keyword") {
			public void actionPerformed(ActionEvent ev) {
				try {
					
						myModel.filter("keyword");

				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        gameMenu.add(new AbstractAction("Filter Out Keyword") {
			public void actionPerformed(ActionEvent ev) {
				try {
					
						myModel.filter("RemoveKeyword");

				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        gameMenu.add(new AbstractAction("by StartTime") {
            public void actionPerformed(ActionEvent ev) {
                try {
					myModel.filter("start in order");
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        gameMenu.add(new AbstractAction("remove conflicts") {
            public void actionPerformed(ActionEvent ev) {
                try {
					myModel.filter("conflict");
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        return gameMenu;
    }
    public void update(String url)
    {
        try
        {
            myPage.setPage(url);
            myURLDisplay.setText(url);
            
        }
        catch (IOException e)
        {
        	// should never happen since only checked URLs make it this far ...
            showError("Could not load " + url);
        }
    }
    public void showError (String message)
    {
        JOptionPane.showMessageDialog(this,
        		                      message, 
        		                      "Browser Error",
        		                      JOptionPane.ERROR_MESSAGE);
    }
    private JComponent makePageDisplay ()
	{
        // displays the web page
        myPage = new JEditorPane();
        myPage.setPreferredSize(SIZE);
        // allow editor to respond to link-clicks/mouse-overs
        myPage.setEditable(false);
        //myPage.addHyperlinkListener(new LinkFollower());
        myPage.addHyperlinkListener(new LinkFollower());
		return new JScrollPane(myPage);
	}
    public void showMessage(String s) {
        myMessage.setText(s);
    }
    private class LinkFollower implements HyperlinkListener
    {
        public void hyperlinkUpdate (HyperlinkEvent evt)
        {
            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
            {
                // user clicked a link, load it and show it
                try
                {
                    myPage.setPage((evt.getURL().toString()));
                }
                catch (Exception e)
                {
                    String s = evt.getURL().toString();
                    JOptionPane.showMessageDialog(TivooViewer.this,
                            "loading problem for " + s + " " + e,
                            "Load Problem", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    

}
