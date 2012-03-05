import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

import org.jdom.JDOMException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import model.Tivoo;

public class TivooViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DateTimeFormatter myFormatter1 = DateTimeFormat
	.forPattern("yyyy/M/d");
	private Tivoo myModel;
	private JPanel myPanel;
	private JTextField myInput, myOutput;
	private JButton browseButton, loadButton, aggregateButton, previewButton, outputButton;
	private JComboBox filters;
	private String myFile;
	private List<String> myOldFiles;
	private static JFileChooser fc = new JFileChooser("./src");
	private String[] output = {
			"Day",
			"Month",
			"Week",
			"Sort"
	};
	private String defaultOut;
	private String[] filterString = {
			"keepKeyword",
			"removeKeyword",
			"startInOrder",
			"startReverseOrder",
			"endInOrder",
			"endReverseOrder",
			"nameOrder",
			"nameReverseOrder",
			"conflict",
			"timeFrame",
			"ClassSpecific"
	};
	
	public TivooViewer(){
		defaultOut = "Month";
		myOldFiles = new ArrayList<String>();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		myPanel = (JPanel) getContentPane();
		myPanel.setLayout(new BorderLayout());
		myPanel.add(makeInput(), BorderLayout.NORTH);
		myPanel.add(makeOption(), BorderLayout.CENTER);
		myPanel.add(makeOutput(), BorderLayout.SOUTH);
		pack();
		connectEvents();
//		setSize(400, 400);
		setVisible(true);
	}
	
	private JPanel makeInput()
	{
		JPanel p = new JPanel();
		JPanel ppp = new JPanel();
		JPanel pp = new JPanel();
		ppp.setLayout(new BorderLayout());
		p.setLayout(new BorderLayout());
		myInput = new JTextField(25);
		browseButton = new JButton("Browse");
		loadButton = new JButton("Load");
		aggregateButton = new JButton("Aggregate");
		loadButton.setEnabled(false);
		aggregateButton.setEnabled(false);
		ppp.setBorder(BorderFactory.createTitledBorder("choose file"));
		p.add(myInput, BorderLayout.CENTER);
		p.add(browseButton, BorderLayout.EAST);
		pp.add(loadButton, BorderLayout.WEST);
		pp.add(aggregateButton, BorderLayout.EAST);
		ppp.add(p, BorderLayout.NORTH);
		ppp.add(pp,BorderLayout.SOUTH);
		return ppp;
	}
	
	private JPanel makeOption()
	{
		JPanel p = new JPanel();
//		p.setLayout(new BorderLayout());
		filters = new JComboBox(filterString);
//		filters.setEditable(true);
		previewButton = new JButton("Preview");
		outputButton = new JButton("Output");
		previewButton.setEnabled(false);
		outputButton.setEnabled(false);
		p.setBorder(BorderFactory.createTitledBorder("options"));
		p.add(filters);
		p.add(previewButton);
		p.add(outputButton);
		return p;
	}
	
	private JPanel makeOutput()
	{
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		myOutput = new JTextField(32);
		p.setBorder(BorderFactory.createTitledBorder("message"));
		p.add(myOutput, BorderLayout.CENTER);
		return p;
	}
	
	private void connectEvents()
	{
		if (myInput == null)
			return;

		myInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				myFile = ev.getActionCommand();
				if(myFile != null)
				{
					myFile = ".\\src\\" + myFile;
					loadButton.setEnabled(true);
				}
//		        myOutput.setText(myFile);
			}
		});
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
		        int returnVal = fc.showOpenDialog(null);
		        if (returnVal == JFileChooser.APPROVE_OPTION)
		        {
		            myFile = fc.getSelectedFile().toString();
		            if(!myOldFiles.contains(myFile)){
		            	myInput.setText(myFile.substring(myFile.lastIndexOf("\\")+1));
		            	loadButton.setEnabled(true);
		            }else{
		            	showError("File already loaded");
		            }
		        }
//		        myOutput.setText(myFile);
			}
		});
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
		        if(myFile != null&&!myOldFiles.contains(myFile))
					try {
						myModel.loadFile(myFile);
						myOutput.setText(myFile.substring(myFile.lastIndexOf("\\")+1) + " Loaded...");
		            	myOldFiles.add(myFile);
						loadButton.setEnabled(false);
						aggregateButton.setEnabled(true);
						previewButton.setEnabled(true);
						outputButton.setEnabled(true);
					} catch (ClassNotFoundException e) {
						showError(e.toString());
					} catch (JDOMException e) {
						showError(e.toString());
					} catch (IOException e) {
						showError(e.toString());
					} catch (InstantiationException e) {
						showError(e.toString());
					} catch (IllegalAccessException e) {
						showError(e.toString());
					}
//		        myOutput.setText(myFile);
			}
		});
		aggregateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
		        if(myFile != null && !myOldFiles.contains(myFile))
					try {
						myModel.aggregateFiles(myFile);
						myOutput.setText(myFile.substring(myFile.lastIndexOf("\\")+1) + " Added...");
						loadButton.setEnabled(false);
						previewButton.setEnabled(true);
						outputButton.setEnabled(true);
//						aggregateButton.setEnabled(true);
					} catch (ClassNotFoundException e) {
						showError(e.toString());
					} catch (JDOMException e) {
						showError(e.toString());
					} catch (IOException e) {
						showError(e.toString());
					} catch (InstantiationException e) {
						showError(e.toString());
					} catch (IllegalAccessException e) {
						showError(e.toString());
					}
//		        myOutput.setText(myFile);
			}
		});
		filters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JComboBox cb = (JComboBox)ev.getSource();
				String selected = (String)cb.getSelectedItem();
				String defaultStr = "";
				String input = "";
				if (selected.contains("Order")){
					defaultStr = "reverse";
					defaultOut = "Sort";
				}
				else if (selected.contains("Keyword")) defaultStr = "keyword";
				else if (selected.contains("Specific")) defaultStr = "John";
				else if (selected.contains("time")) defaultStr = "2011/8/1 2011/8/31";
				if(!selected.contains("conflict")) input = (String) JOptionPane.showInputDialog(TivooViewer.this, "Input Keywords", defaultStr);
				List<Object> l = new ArrayList<Object>(Arrays.asList(input.split("\\s+")));
				if (selected.contains("time"))
				{
					List<Object> temp = new ArrayList<Object>();
					for(Object o : l)
					{
						String str = (String) o;
						temp.add(myFormatter1.parseDateTime(str));
					}
					l = temp;
				}
				try {
					myModel.filter(selected, l);
					myOutput.setText("Filtered by " + selected + " with " + l);
					previewButton.setEnabled(true);
					outputButton.setEnabled(true);
				} catch (Exception e) {
					showError(e.toString());
				}
//		        myOutput.setText(myFile);
			}
		});
		previewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String input = getOutputType();
				myModel.output(input);
				try {
					HTMLPreview p = new HTMLPreview(new File("./Output/"+ input+"Output.htm").toURI().toString());
					outputButton.setEnabled(true);
				} catch (IOException e) {
					showError(e.toString());
				}
//		        myOutput.setText(myFile);
			}
		});
		outputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String input = getOutputType();
				myModel.output(input);
//		        myOutput.setText(myFile);
			}
		});
	}
	
	private String getOutputType()
	{
		return (String) JOptionPane.showInputDialog(TivooViewer.this, null, "Output options", JOptionPane.QUESTION_MESSAGE, null, output, defaultOut);
	}
	
	public void showError(String s) {
        JOptionPane.showMessageDialog(this, s, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
	
	public void setModel(Tivoo tv)
	{
		myModel = tv;
	}
	
	public static void main(String[] args) throws ClassNotFoundException
	{
		TivooViewer tv = new TivooViewer();
		tv.setModel(new Tivoo());
	}
}
