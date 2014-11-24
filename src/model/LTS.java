package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import api.GraphViz;

public class LTS implements Cloneable {

	private String name;
	
	private Vector<State> states;
	private Vector<State> initialStates;
	private Vector<AtomicProposition> atomicPropositions;
	private Vector<Transition> transitions;
	private HashMap<State, Vector<AtomicProposition>> labels;
	
	public LTS()
	{
		states = new Vector<State>();
		initialStates = new Vector<State>();
		transitions = new Vector<Transition>();
		labels = new HashMap<State, Vector<AtomicProposition>>();
		atomicPropositions = new Vector<AtomicProposition>();
	}
	
	public LTS(String name)
	{
		this.setName(name);
		states = new Vector<State>();
		initialStates = new Vector<State>();
		transitions = new Vector<Transition>();
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
		        if(key.getName().equals(e.getKey().getName()))
		        {
		        	return;
		        	
		        }
		  }
		  labels.put(key, value);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void addState(State state) {
		states.add(state);
	}
	
	public void addTransition(Transition t)
	{
		//Preventing dublicates 
		for(Transition myT:transitions)
		{
			if(myT.getFirstState().getName().equals(t.getFirstState().getName())
					&& myT.getSecondState().getName().equals(t.getSecondState().getName())
					&& myT.getEvent().getName().equals(t.getEvent().getName()))
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
	
	public String createApStringForState(State s)
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
		        			if(ap!=null)
		        			{
			        			if(ltsAp.getName().equals(ap.getName()))
			        			{
			        				hatAP = true;
			        				apstring += ap.getName()+",\n";
			        			}
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
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
	
	/**
	 * essentially, it is the LTS without the state labels(ignore the transition labels)
	 * but keeping the state's AP labels, so we just set the Event labels to an empty String, L4 s11.
	 * 
	 * @return KS
	 */
	public LTS mapToKS() {
		for(Transition transition : getTransitions()) {
			transition.getEvent().setName("");
		}
		return this;
	}
	
	public void generateGraph() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
//		gv.addln("" + transitions.elementAt(0).getFirstState() + " [shape=box]");
		
		for(State s : getAllStates())
		{
			String labelname = s.getName() +"_label";
			
			gv.addln(labelname +"[color=white, shape=box, label=\""+this.createApStringForState(s)+"\"];");
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
