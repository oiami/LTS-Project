package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import api.GraphViz;

public class LTS implements Cloneable {

	private String name;
	private Vector<Transition> transitions;
	private Vector<State> initialStates;

	private HashMap<State, Vector<AtomicProposition>> labels;
	
	public LTS()
	{
		transitions = new Vector<Transition>();
		initialStates = new Vector<State>();
		labels = new HashMap<State, Vector<AtomicProposition>>();
	}
	
	public LTS(String name)
	{
		this.setName(name);
		transitions = new Vector<Transition>();
		initialStates = new Vector<State>();
		labels = new HashMap<State, Vector<AtomicProposition>>();

	}
	
	public void printLabels() {
        System.out.print("{");

	    for(Entry<State, Vector<AtomicProposition>> e : labels.entrySet()) {
	        State key = e.getKey();
	        Vector<AtomicProposition> value = e.getValue();
	        
	        System.out.print("("+key+",{");
	        for (AtomicProposition ap : value) {
	        	if(ap == null) {
	        		break;
	        	}
	        	else {
		        	System.out.print(ap+",");
	        	}
	        }
	        System.out.print("}),");
	    }
        System.out.println("}");

	}
	
	public void addLabel(State key, Vector<AtomicProposition> value) {
		labels.put(key, value);
	}
	
	public void addTransition(Transition t)
	{
		//Preventing dublicates 
		for(Transition myT:transitions)
		{
			if(myT.getFirstState().getName().equals(t.getFirstState().getName())
					&& myT.getSecondState().getName().equals(t.getSecondState().getName())
					&& myT.getEvent().getName() == t.getEvent().getName())
				return;
		}
		transitions.add(t);
	}
	
	public Vector<Transition> getTransitions()
	{
		return transitions;
	}
	
	public Object clone() 
	{ 
	    try 
	    { 
	    	return super.clone(); 
    	} 
	    catch (CloneNotSupportedException e) {}
		return null; 
	  } 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Vector<State> getInitialStates() {
		return initialStates;
	}
	public void addInitialState(State initialState) {
		this.initialStates.addElement(initialState);
	}
	
	public void printToDotFile(String filename,LTS completeSystem) {
		PrintWriter writer;
		//int i = 0;
		try {
			writer = new PrintWriter(filename, "UTF-8");
	    	writer.println("digraph G {");
	    	for(Transition t : completeSystem.getTransitions())
	    	{
	    		if(completeSystem.getInitialStates().contains(t.getFirstState())) //InitalState!
				{
	    			writer.println(t.getFirstState().getName()+" [shape=box];");
	    			
	    			writer.println(t.getFirstState().getName()+" -> "+t.getSecondState().getName()+"[style=bold,label=\""+t.getEvent().getName().toString()+"\"];");
	    			
				}
	    		else
	    		{
	    			writer.println(t.getFirstState().getName()+" -> "+t.getSecondState().getName()+"[style=bold,label=\""+t.getEvent().getName().toString()+"\"];");
	    	    	
	    		}
    		}
	    	
	    	writer.println("}");
	    	
	    	writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printTransitions()
	{
		for(Transition t : transitions)
		{
			System.out.println("("+t.getFirstState().getName()+"->"+t.getEvent().getName().toString()+"->"+t.getSecondState().getName()+")");
		}
		
	}
	
	public void generateGraph() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
//		gv.addln("" + transitions.elementAt(0).getFirstState().getName() + " [shape=box]");
		for(Transition t : transitions)
		{
			gv.addln(""+t.getFirstState().getName()+"->"+t.getSecondState().getName()+" [label="+t.getEvent().getName().toString()+"]");
		}
		
		gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());

		gv.increaseDpi();   // 106 dpi

		String type = "gif";
		//      String type = "dot";
		//      String type = "fig";    // open with xfig
		//      String type = "pdf";
		//      String type = "ps";
		//      String type = "svg";    // open with inkscape
		//      String type = "png";
		//      String type = "plain";
		
		String repesentationType= "dot";
		//		String repesentationType= "neato";
		//		String repesentationType= "fdp";
		//		String repesentationType= "sfdp";
		// 		String repesentationType= "twopi";
		// 		String repesentationType= "circo";
		
		File out = new File(this.getName() + "."+ type);   // Mac, in Eclipse Project Folder.
		//      File out = new File("c:/eclipse.ws/graphviz-java-api/out." + type);    // Windows
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out );
	}
}
