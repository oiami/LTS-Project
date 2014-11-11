package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Merger {
	
	public Merger() {

	}
	
	public LTS ComputeParallelComposition(LTS ltsA,LTS ltsB)
	{

		LTS completeSystem = new LTS();
		boolean eventMatch=false;
		
		//The three following loops represent the code to realize the 3 rules for transitions
		//that were told us in the lecture
		
		for(Transition t1: ltsA.getTransitions())
		{
			for(Transition t2: ltsB.getTransitions())
			{
				if(t2.getEvent().getSymbol() == t1.getEvent().getSymbol())
				{
					
					State mergedState1 = new State(t1.getFirstState().getName());
					mergedState1.mergeToState( t2.getFirstState().getName());
					
					State mergedState2 = new State(t1.getSecondState().getName());
					mergedState2.mergeToState(t2.getSecondState().getName());
					
					Event mutualEvent = new Event(t1.getEvent().getSymbol());
					
					if(ltsA.getInitialStates().contains(t1.getFirstState())
							&& ltsB.getInitialStates().contains(t2.getFirstState()))
							{
								completeSystem.addInitialState(mergedState1);
							}
					
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
				addToTransitionSet(ltsB,t1,completeSystem);
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
				addToTransitionSet(ltsA,t2,completeSystem);
			}
			eventMatch = false;
		}
		completeSystem.setName(ltsA.getName() + " || " + ltsB.getName());
		return completeSystem;
	}	
	
	//Adds not included transition events to every single State of an lts
	private void addToTransitionSet(LTS lts, Transition addTr,LTS completeSystem)
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
				
//				if(lts.getInitialStates().contains(t.getFirstState()))
//						{
//							completeSystem.addInitialState(newState1);
//						}
				
				completeSystem.addTransition(newTrans); // compare just between ltsA and ltsB but adding to complete LTS
				
			}
			else
			{ // in case there is State with the same name
				State newState1 = new State(t.getFirstState().getName());
				
				Event addEvent = new Event(addTr.getEvent().getSymbol());
				
				State newState2 = new State(t.getFirstState().getName());
				newState2.mergeToState(addTr.getSecondState().getName());

				Transition newTrans = new Transition(newState1, addEvent, newState2);
				
//				if(lts.getInitialStates().contains(t.getFirstState()))
//				{
//					completeSystem.addInitialState(newState1);
//				}
				completeSystem.addTransition(newTrans); // compare just between ltsA and ltsB but adding to complete LTS
				
			}
		}
	}
}
