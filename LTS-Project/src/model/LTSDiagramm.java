package model;

import java.util.Vector;

public class LTSDiagramm {
	
	Vector<LTS> ltsSystems;
	LTS completeSystem;
	
	public void addSystemAsParallelComposition(LTS ltsB)
	{
		Vector<Transition> notInB =new Vector<Transition>();
		Vector<Transition> notInA =new Vector<Transition>();
		
		LTS ltsA = new LTS(); // lts to merge
		ltsA = (LTS)completeSystem.clone(); //Copy the previous complete System to ltsA
		
		completeSystem = new LTS();// allocate new space for the now to build lts
		
		boolean eventMatch=false;
		
		
		
		for(Transition t2: ltsB.getTransitions())
		{
			for(Transition t1: ltsA.getTransitions())
			{
				if(t2.getEvent() == t1.getEvent())
				{
					eventMatch = true;

						State mergedState1 = new State(t1.getFirstState() + ", \n" + t2.getFirstState());
					
						completeSystem.addTransition(new Transition(t1.getFirstState()));
					
				}

			}
			if(!eventMatch)
				// if there is no Event match, the Transitions of ltsB have no mutual Events with ltsA  
			{
				eventMatch=false;
				
				new Transition()
				
			}
			//completeSystem.getName() + " || " + ltsB.getName()
		}
	}
	

}
