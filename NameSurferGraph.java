/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener{
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph(){
		addComponentListener(this);
		displayentery = new ArrayList<NameSurferEntry>();
	}

	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear(){
		displayentery.clear();
	}

	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry){
		displayentery.add(entry);
	}
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update(){
		removeAll();
		makeYlines();
		makeXlines();
		putX(); 
		if(displayentery.size() >= 0){
			for(int i = 0; i < displayentery.size(); i++){
				NameSurferEntry entries = displayentery.get(i); 
				drawEntry(entries, i);
			}
		}
	}
	private void makeYlines(){
		for(int i = 0; i<NDECADES; i++){
			double y1 = 0;
			double y2 = getHeight();
			double x = i * getWidth() / NDECADES;
			GLine line = new GLine(x, y1, x, y2);
			add(line);
		}
	}
	private void makeXlines(){
		double x1 = 0;
		double x2 = getWidth();
		double y1 = getHeight() - GRAPH_MARGIN_SIZE;
		GLine l1 = new GLine(x1, y1, x2, y1);
		add(l1);
		double y2 = GRAPH_MARGIN_SIZE;
		GLine l2 = new GLine(x1, y2, x2, y2);
		add(l2);
	}
	private void putX(){
		for(int i = 0; i<NDECADES; i++){
			int x1 = START_DECADE;
			x1 = x1 + 10 * i;
			String label = Integer.toString(x1);
			double y = getHeight() - GRAPH_MARGIN_SIZE/4.0;
			double x = getWidth()/(4.0*NDECADES) + i * getWidth()/NDECADES;
			GLabel xPoint = new GLabel(label, x, y);
			xPoint.setFont("Calibri");
			add(xPoint);
		}
	}
	private void drawEntry(NameSurferEntry entry, int entryNumber) {
		//draws the graph line
		for(int i = 0; i < NDECADES - 1; i++) {
			int rank = entry.getRank(i);
			int rank2 = entry.getRank(i+1);
			double x1 = i * (getWidth()/NDECADES);
			double x2 = (i+1) * (getWidth()/NDECADES);
			double y1 = 0;
			double y2 = 0;
			if(rank != 0 && rank2 != 0) {
				y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2.0) * rank/MAX_RANK;
				y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2.0) * rank2/MAX_RANK;
			}else if(rank == 0 && rank2 == 0) {
				y1 = getHeight() - GRAPH_MARGIN_SIZE;
				y2 = getHeight() - GRAPH_MARGIN_SIZE;
			}else if (rank == 0){
				y1 = getHeight() - GRAPH_MARGIN_SIZE;
				y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2.0) * rank2/MAX_RANK;
			}else if(rank2 == 0) {
				y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2.0) * rank/MAX_RANK;
				y2 = getHeight() - GRAPH_MARGIN_SIZE;
			}
			GLine line = new GLine(x1, y1, x2, y2);
			if(entryNumber%4 == 1) {
				line.setColor(Color.RED);
			}else if(entryNumber%4 == 2) {
				line.setColor(Color.BLUE);
			}else if(entryNumber%4 == 3) {
				line.setColor(Color.MAGENTA);
			}
			add(line);
		}
		for(int i = 0; i<NDECADES; i++){
			String name = entry.getName();
			int rank = entry.getRank(i);
			String rankString = Integer.toString(rank);
			String label = name + " " + rankString;
			double x = i * (getWidth()/NDECADES) + 5.0;
			double y = 0;
			if(rank != 0) {
				y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2.0) * rank/MAX_RANK - 5.0;
			}else{
				label = name + " *";
				y = getHeight() - GRAPH_MARGIN_SIZE - 5.0;
			}
			GLabel nameLabel = new GLabel(label, x, y);
			if(entryNumber%4 == 1){
				nameLabel.setColor(Color.RED);
			}else if(entryNumber%4 == 2){
				nameLabel.setColor(Color.BLUE);
			}else if(entryNumber%4 == 3){
				nameLabel.setColor(Color.MAGENTA);
			}
			add(nameLabel);
		}
	}
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	private ArrayList <NameSurferEntry> displayentery;
}