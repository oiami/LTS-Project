package model;

import java.util.Vector;

public class LTS implements Cloneable {

	private String name;
	private Vector<Transition> transitions;
	private Vector<State> states;
	private State initialState;
	
	public LTS()
	{
		
	}
	public LTS(String name)
	{
		this.setName(name);
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
//	public void printLTS()
//	{
//		for(Transition t1: transitions)
//		{
//			
//			for(Transition t2: transitions)
//			{
//				
//			}
//		}
//	}
	
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

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
