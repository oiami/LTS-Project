package model;

import java.util.Vector;


public class Merger {
	
	public Merger() {

	}
	
	public LTS ComputeParallelComposition(LTS ltsA,LTS ltsB)
	{

		LTS completeSystem = new LTS();
		boolean eventMatch=false;
		
		
		//Merge the APs of both LTS
		Vector<AtomicProposition> mergedAPs = new Vector<AtomicProposition>();
		mergedAPs.addAll(ltsA.getAtomicPropositions());
		for(AtomicProposition p :ltsB.getAtomicPropositions())
		{
			if(!mergedAPs.contains(p))
				mergedAPs.add(p);
		}
		for(AtomicProposition p :mergedAPs)
		{
			completeSystem.addAtomicProposition(p);
		}
		
		//The three following loops represent the code to realize the 3 rules for transitions
		//that were told us in the lecture
		
		for(Transition t1: ltsA.getTransitions())
		{
			for(Transition t2: ltsB.getTransitions())
			{
				try{
				if(t2.getEvent().getName().equals(t1.getEvent().getName()))
				{
					
					State mergedState1 = new State(t1.getFirstState().getName());
					mergedState1.mergeToState( t2.getFirstState().getName());
					
					State mergedState2 = new State(t1.getSecondState().getName());
					mergedState2.mergeToState(t2.getSecondState().getName());
					
					Event mutualEvent = new Event(t1.getEvent().getName());
										
					//APs for mergedState1 in mergedLTS
					Vector<AtomicProposition> aps1 = new Vector<AtomicProposition>();
					aps1.addAll(ltsA.getAPforState(t1.getFirstState()));
					aps1.addAll(ltsB.getAPforState(t2.getFirstState()));
					mergedState1.setAtomicPropositions(aps1);
				
					//APs for mergedState2 in mergedLTS
					Vector<AtomicProposition> aps2 = new Vector<AtomicProposition>();
					aps2.addAll(ltsA.getAPforState(t1.getSecondState()));
					aps2.addAll(ltsB.getAPforState(t2.getSecondState()));
					mergedState1.setAtomicPropositions(aps2);
					
					if(ltsA.getInitialStates().contains(t1.getFirstState())
							&& ltsB.getInitialStates().contains(t2.getFirstState()))
							{
								completeSystem.addInitialState(mergedState1);
							}
					
					completeSystem.addTransition(new Transition(mergedState1,mutualEvent,mergedState2));
					completeSystem.addLabel(mergedState1, aps1);
					completeSystem.addLabel(mergedState2, aps2);
				}}
				catch(Exception e)
				{
					
				}
			}
		}
		
		//This loop is actually not necessary and could be included in the previous one 
		//but like that the code is easier to understand 
		for(Transition t1: ltsA.getTransitions())
		{
			for(Transition t2: ltsB.getTransitions())
			{
				if(t2.getEvent().getName() == t1.getEvent().getName())
				{
					eventMatch =true;				
				}
			}
			if(!eventMatch)
			{
				addToTransitionSet(ltsB,t1,ltsA,completeSystem);
			}
			eventMatch = false;
		}
		
		for(Transition t2: ltsB.getTransitions())
		{
			for(Transition t1: ltsA.getTransitions())
			{
				if(t2.getEvent().getName() == t1.getEvent().getName())
				{
					eventMatch =true;				
				}
			}
			if(!eventMatch)
			{
				addToTransitionSet(ltsA,t2,ltsB,completeSystem);
			}
			eventMatch = false;
		}
		completeSystem.setName(ltsA.getName() + " || " + ltsB.getName());
		return completeSystem;
	}	

	//Adds not included transition events to every single State of an lts
	private void addToTransitionSet(LTS toLts, Transition addTr,LTS fromLts,LTS completeSystem)
	{
		for(Transition t : toLts.getTransitions())
		{
			if(!t.getFirstState().getMergedNames().contains(addTr.getFirstState().getName()))
			{
				
				State newState1 = new State(t.getFirstState().getName());
				newState1.mergeToState(addTr.getFirstState().getName());
				
				Event addEvent = new Event(addTr.getEvent().getName());
				
				State newState2 = new State(t.getFirstState().getName());
				newState2.mergeToState(addTr.getSecondState().getName());
				
				//APs for newState1 in merged LTS
				Vector<AtomicProposition> aps1 = new Vector<AtomicProposition>();
				aps1.addAll(toLts.getAPforState(t.getFirstState()));
				aps1.addAll(fromLts.getAPforState(addTr.getFirstState()));
				newState1.setAtomicPropositions(aps1);
				
				//APs for newState2 in merged LTS
				Vector<AtomicProposition> aps2 = new Vector<AtomicProposition>();
				aps2.addAll(toLts.getAPforState(t.getSecondState()));
				aps2.addAll(fromLts.getAPforState(addTr.getSecondState()));
				newState2.setAtomicPropositions(aps2);

				Transition newTrans = new Transition(newState1, addEvent, newState2);
				
//				if(lts.getInitialStates().contains(t.getFirstState()))
//						{
//							completeSystem.addInitialState(newState1);
//						}
				
				completeSystem.addLabel(newState1, aps1);
				completeSystem.addLabel(newState2, aps2);
				
				completeSystem.addTransition(newTrans); // compare just between ltsA and ltsB but adding to complete LTS
			}
			
			else
			{ // in case there is State with the same name
				State newState1 = new State(t.getFirstState().getName());
				
				Event addEvent = new Event(addTr.getEvent().getName());
				
				State newState2 = new State(t.getFirstState().getName());
				newState2.mergeToState(addTr.getSecondState().getName());

				Transition newTrans = new Transition(newState1, addEvent, newState2);
				
				//APs for newState1 in merged LTS
				Vector<AtomicProposition> aps1 = new Vector<AtomicProposition>();
				aps1.addAll(toLts.getAPforState(t.getFirstState()));
				aps1.addAll(fromLts.getAPforState(addTr.getFirstState()));
				newState1.setAtomicPropositions(aps1);
				
				//APs for newState2 in merged LTS
				Vector<AtomicProposition> aps2 = new Vector<AtomicProposition>();
				aps2.addAll(toLts.getAPforState(t.getSecondState()));
				aps2.addAll(fromLts.getAPforState(addTr.getSecondState()));
				newState2.setAtomicPropositions(aps2);
//				if(lts.getInitialStates().contains(t.getFirstState()))
//				{
//					completeSystem.addInitialState(newState1);
//				}
				completeSystem.addLabel(newState1, aps1);
				completeSystem.addLabel(newState2, aps2);
				completeSystem.addTransition(newTrans); // compare just between ltsA and ltsB but adding to complete LTS
				
			}
		}
	}
}
