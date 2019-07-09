/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants{

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init(){
		query = new JLabel("Name: ");
		add(query, NORTH);
		name = new JTextField(20);
		add(name, NORTH);
		name.addActionListener(this);
		Graph = new JButton("Graph");
		add(Graph, NORTH);
		Clear = new JButton("Clear");
		add(Clear, NORTH);
		graph = new NameSurferGraph();
		add(graph);
		addActionListeners(this);
		dataFile = new NameSurferDataBase(NAMES_DATA_FILE);
	}
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Clear")){
			graph.clear();
			graph.update();
		}else{
			String enteredName = name.getText();
			NameSurferEntry rankings = dataFile.findEntry(enteredName);
			if(rankings != null){
				graph.addEntry(rankings);
				graph.update();
			}
		}
	}
	private JLabel query;
	private JTextField name;
	private JButton Graph;
	private JButton Clear;
	private NameSurferDataBase dataFile;
	private NameSurferGraph graph;
}
