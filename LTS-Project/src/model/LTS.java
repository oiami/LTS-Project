package model;

import java.util.Vector;

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
}
