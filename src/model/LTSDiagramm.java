package model;

import java.util.Vector;

public class LTSDiagramm {
	
	Vector<LTS> ltsSystems;
	LTS completeSystem;
	
	public LTSDiagramm() {
		ltsSystems = new Vector<LTS>();
		completeSystem = new LTS();
	}
	
	public void addLTS(LTS lts)
	{
		ltsSystems.addElement(lts);
	}
	
	public void ComputeParallelCompositionFromFirstTwoLts()
	{
		LTS ltsA = ltsSystems.elementAt(0);		
		LTS ltsB = ltsSystems.elementAt(1);
		
		boolean eventMatch=false;
		
		//The three following loops represent the code to realize the 3 rules for transitions
		//that were told us in the lecture
		
		for(Transition t1: ltsA.getTransitions())
		{
			for(Transition t2: ltsB.getTransitions())
			{
				if(t2.getEvent().getSymbol() == t1.getEvent().getSymbol())
				{
					State mergedState1 = new State(t1.getFirstState() + ", " + t2.getFirstState());
					State mergedState2 = new State(t1.getSecondState() + ", " + t2.getSecondState());
					
					Event mutualEvent = new Event(t1.getEvent().getSymbol());
					
					completeSystem.addTransition(new Transition(mergedState1,mutualEvent,mergedState2));
					
				}
			}
		}
		
		//This loop is actually not necessary and could be included in the previous one 
		//but like that the code is easier to understand 
		for(Transition t1: ltsA.getTransitions())
		{
			for(Transition t2: ltsB.getTransitions())
			{
				if(t2.getEvent().getSymbol() == t1.getEvent().getSymbol())
				{
					eventMatch =true;				
				}
			}
			if(!eventMatch)
			{
				addToTransitionSet(ltsB,t1);
			}
			eventMatch = false;
		}
		
		for(Transition t2: ltsB.getTransitions())
		{
			for(Transition t1: ltsA.getTransitions())
			{
				if(t2.getEvent().getSymbol() == t1.getEvent().getSymbol())
				{
					eventMatch =true;				
				}
			}
			if(!eventMatch)
			{
				addToTransitionSet(ltsA,t2);
			}
			eventMatch = false;
		}
		completeSystem.setName(ltsA.getName() + " || " + ltsB.getName());
	}	
	
	//Adds not included transition events to every single State of an lts
	private void addToTransitionSet(LTS lts, Transition addTr)
	{
		for(Transition t : lts.getTransitions())
		{
			if(!t.getFirstState().getMergedNames().contains(addTr.getFirstState().getName()))
			{
				State newState1 = new State(t.getFirstState().getName());
				newState1.mergeToState(addTr.getFirstState().getName());
				
				Event addEvent = new Event(addTr.getEvent().getSymbol());
				
				State newState2 = new State(t.getFirstState().getName());
				newState2.mergeToState(addTr.getSecondState().getName());

				Transition newTrans = new Transition(newState1, addEvent, newState2);
				
				completeSystem.addTransition(newTrans); // compare just between ltsA and ltsB but adding to complete LTS
				
			}
			else
			{ // in case there is State with the same name
				State newState1 = new State(t.getFirstState().getName());
				
				Event addEvent = new Event(addTr.getEvent().getSymbol());
				
				State newState2 = new State(t.getFirstState().getName());
				newState2.mergeToState(addTr.getSecondState().getName());

				Transition newTrans = new Transition(newState1, addEvent, newState2);
				
				completeSystem.addTransition(newTrans); // compare just between ltsA and ltsB but adding to complete LTS
				
			}
		}
	}

	
	
	public LTS getLTS()
	{
		return completeSystem;
	}
}
