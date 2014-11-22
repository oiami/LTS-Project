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
	private Vector<AtomicProposition> atomicPropositions;

	private HashMap<State, Vector<AtomicProposition>> labels;
	
	public LTS()
	{
		transitions = new Vector<Transition>();
		initialStates = new Vector<State>();
		labels = new HashMap<State, Vector<AtomicProposition>>();
		atomicPropositions = new Vector<AtomicProposition>();
	}
	
	public LTS(String name)
	{
		this.setName(name);
		transitions = new Vector<Transition>();
		initialStates = new Vector<State>();
		labels = new HashMap<State, Vector<AtomicProposition>>();
		atomicPropositions = new Vector<AtomicProposition>();
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
	        System.out.print("}),\n");
	    }
        System.out.println("}");

	}
	
	public void addLabel(State key, Vector<AtomicProposition> value) 
	{
		  for(Entry<State, Vector<AtomicProposition>> e : labels.entrySet()) 
		  {
		        if(key.getName() == e.getKey().getName())
		        {
		        	return;
		        	
		        }
		  }
		labels.put(key, value);
	}
	
	public void addTransition(Transition t)
	{
		//Preventing dublicates 
		for(Transition myT:transitions)
		{
			if(myT.getFirstState().equals(t.getFirstState())
					&& myT.getSecondState().equals(t.getSecondState())
					&& myT.getEvent() == t.getEvent())
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
	
	public Vector<AtomicProposition> getAPforState(State s)
	{
		 for(Entry<State, Vector<AtomicProposition>> e : labels.entrySet()) {
		        if(e.getKey() == s)
		        {
		        	return e.getValue();
		        }
		 }
		 return new Vector<AtomicProposition>();
	}
	
	public String getAPStringForState(State s)
	{
		String apstring = "";
		 for(Entry<State, Vector<AtomicProposition>> e : labels.entrySet()) {
		        if(e.getKey() == s)
		        {
		        	boolean hatAP = false;
		        	
		        	for(AtomicProposition ltsAp :atomicPropositions)
		        	{
		        		hatAP = false;
		        		for(AtomicProposition ap :e.getValue())
		        		{
		        			if(ltsAp.getName() == ap.getName())
		        			{
		        				hatAP = true;
		        				apstring += ap.getName()+",\n";
		        			}
		        		}
		        		if(!hatAP)
		        		{
		        			apstring += "!"+ltsAp.getName()+",\n";
		        		}

		       		}
		        	break;
		        }
		 }
		 return apstring;
	}
	
	public void printToDotFile(String filename) {
		PrintWriter writer;
		//int i = 0;
		try {
			writer = new PrintWriter(filename, "UTF-8");
	    	writer.println("digraph G {");
	    	for(Transition t : transitions)
	    	{
	    		if(initialStates.contains(t.getFirstState())) //InitalState!
				{
	    			writer.println(t.getFirstState()+" [shape=box];");
	    			
	    			writer.println(t.getFirstState()+" -> "+t.getSecondState()+"[style=bold,label=\""+t.getEvent().toString()+"\"];");
	    			
				}
	    		else
	    		{
	    			writer.println(t.getFirstState()+" -> "+t.getSecondState()+"[style=bold,label=\""+t.getEvent().toString()+"\"];");
	    	    	
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
			System.out.println("("+t.getFirstState()+"->"+t.getEvent().toString()+"->"+t.getSecondState()+")");
		}
		
	}
	private Vector<State> getAllStates()
	{
		Vector<State> allStateVec = new Vector<State>();

		for(Transition t : transitions)
		{
			boolean exist = false;

			System.out.println(t.getFirstState().getName());
			for(State s : allStateVec)
			{
				

				if(t.getFirstState().getName().equals(s.getName()))
				{
					exist =true;
					break;
				}
			}
			if(!exist)
			{
				allStateVec.addElement(t.getFirstState());
			}
			
			exist = false;
			for(State s : allStateVec)
			{
				if(t.getSecondState().getName().equals(s.getName()))
				{
					exist =true;
					break;
				}
			}
			if(!exist)
			{
				allStateVec.addElement(t.getSecondState());
			}
			
			}
		return allStateVec;
	}
	
	public void generateGraph() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
//		gv.addln("" + transitions.elementAt(0).getFirstState() + " [shape=box]");
		
		for(State s : getAllStates())
		{
			String labelname = s.getName() +"_label";
			
			gv.addln(labelname +"[color=white, shape=box, label=\""+this.getAPStringForState(s)+"\"];");
			gv.addln(""+s+" -> "+labelname+" [arrowhead=none, style=dotted];");
		}
		for(Transition t : transitions)
		{
			if(initialStates.contains(t.getFirstState())) //InitalState!
			{
				gv.addln(t.getFirstState()+" [fillcolor=yellowgreen, shape=box];");
    		}
			
			gv.addln(""+t.getFirstState()+"-> "+t.getSecondState()+" [label="+t.getEvent().getName()+"];");
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

	public Vector<AtomicProposition> getAtomicPropositions() {
		return atomicPropositions;
	}

	public void addAtomicProposition(AtomicProposition atomicProposition) {
		this.atomicPropositions.add(atomicProposition);
	}
}
