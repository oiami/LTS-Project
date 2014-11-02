package model;

import java.io.File;
import java.util.Vector;

import view.GraphViz;

public class LTS implements Cloneable {

	private String name;
	private Vector<Transition> transitions;
	private Vector<State> states;
	private Vector<State> initialStates;
	
	public LTS()
	{
		transitions = new Vector<Transition>();
		states = new Vector<State>();
		initialStates = new Vector<State>();
	}
	
	public LTS(String name)
	{
		this.setName(name);
		transitions = new Vector<Transition>();
		states = new Vector<State>();
		initialStates = new Vector<State>();
	}
	
	public void addTransition(Transition t)
	{
		//Every state get extracted... 
		//It's actually not really necessary at this point, 
		//though its providing a better overview of the code
		if(!states.contains(t.getFirstState()))
				states.add(t.getFirstState());
		if(!states.contains(t.getSecondState()))
			states.add(t.getSecondState());
				
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
	
	public void printTransitions()
	{
		for(Transition t : transitions)
		{
			System.out.println("("+t.getFirstState().getName()+"->"+t.getEvent().getSymbol().toString()+"->"+t.getSecondState().getName()+")");
		}
		
	}
	
	public void generateGraph() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		
		for(Transition t : transitions)
		{
			gv.addln(""+t.getFirstState().getName()+"->"+t.getEvent().getSymbol().toString()+"->"+t.getSecondState().getName()+"");
		}
		
//		gv.addln("A -> B;");
//		gv.addln("A -> C;");
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
		
		File out = new File(gv.getImageDpi()+"."+ type);   // Mac, in Eclipse Project Folder.
		//      File out = new File("c:/eclipse.ws/graphviz-java-api/out." + type);    // Windows
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out );
	}
}
