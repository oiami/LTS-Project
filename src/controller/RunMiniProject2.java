package controller;

import model.AtomicProposition;
import model.Event;
import model.LTS;
import model.Merger;
import model.State;
import model.Transition;

/**
 * @author Fadi Asbih
 *
 *         Copyright (c) 2014
 *
 *         TERMS AND CONDITIONS: This program is free software: you can
 *         redistribute it and/or modify it under the terms of the GNU General
 *         Public License as published by the Free Software Foundation, either
 *         version 3 of the License, or (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful, but
 *         WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *         General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
public class RunMiniProject2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// *******LTS - Kripke Structure**************
		LTS light = new LTS("Light");

		// The States that we have.
		State stateOff = new State("off");
		State stateLow = new State("low");
		State stateHigh = new State("high");

		// is a finite, non-empty set of states
		light.addState(stateOff);
		light.addState(stateLow);
		light.addState(stateHigh);

		// is a set of initial states
		light.addInitialState(stateOff);
		
		// AP
		AtomicProposition lightOn = new AtomicProposition("lightOn");
		AtomicProposition highBattUse = new AtomicProposition("highBattUse");
				
		light.addAtomicProposition(lightOn);
		light.addAtomicProposition(highBattUse);

		// Label each state with a set of atomic propositions
		stateOff.labelingFunction(lightOn, false);
		stateOff.labelingFunction(highBattUse, false);

		stateLow.labelingFunction(lightOn, true);
		stateLow.labelingFunction(highBattUse, false);

		stateHigh.labelingFunction(lightOn, true);
		stateHigh.labelingFunction(highBattUse, true);
		
		light.addLabel(stateOff, stateOff.getAtomicPropositions());
		light.addLabel(stateLow, stateLow.getAtomicPropositions());
		light.addLabel(stateHigh, stateHigh.getAtomicPropositions());
		
		// Events
		Event lightPress = new Event("press");
		Event lightHold = new Event("hold");

		// Transitions, is a transition relation that must be left-total, Kripke Structure
		Transition t1 = new Transition(stateOff, lightPress, stateLow);
		Transition t2 = new Transition(stateLow, lightHold, stateHigh);
		Transition t3 = new Transition(stateLow, lightPress, stateOff);
		Transition t4 = new Transition(stateHigh, lightPress, stateOff);
		
		light.addTransition(t1);
		light.addTransition(t2);
		light.addTransition(t3);
		light.addTransition(t4);
		
		System.out.println("print lables:");
		light.printLabels();
		
		
		
		//*********LTS Switch**************
		
		LTS switching = new LTS("Switch");
		
		//LTS 2 States
		State switching_state_rel = new State("rel");
		State switching_state_pr = new State("pr");
		
		// LTS Switch Events
		Event switching_press = new Event("press");
		Event switching_hold = new Event("hold");
		Event switching_release = new Event("release");
		
		// LTS Switch Transitions
		Transition switching_t1 = new Transition(switching_state_rel, switching_press, switching_state_pr);
		Transition switching_t2 = new Transition(switching_state_pr, switching_hold, switching_state_pr);
		Transition switching_t3 = new Transition(switching_state_pr, switching_release, switching_state_rel);


		AtomicProposition testAP1 = new AtomicProposition("a");
		AtomicProposition testAP2 = new AtomicProposition("b");
		
		switching.addAtomicProposition(testAP1);
		switching.addAtomicProposition(testAP2);
		
		// Label each state with a set of atomic propositions
		switching_state_rel.labelingFunction(testAP1, false);
		switching_state_rel.labelingFunction(testAP2, true);

		switching_state_pr.labelingFunction(testAP1, true);
		switching_state_pr.labelingFunction(testAP2, false);
		
		switching.addAtomicProposition(testAP1);
		switching.addAtomicProposition(testAP2);
		
		//add labels to switching

		switching.addLabel(switching_state_rel, switching_state_rel.getAtomicPropositions());
		switching.addLabel(switching_state_pr, switching_state_pr.getAtomicPropositions());
		
		switching.addTransition(switching_t1);
		switching.addTransition(switching_t2);
		switching.addTransition(switching_t3);
		
		switching.addInitialState(switching_state_rel);
		
		System.out.println("*********switching - "+ switching.getName() +"********");
		switching.printTransitions();
		System.out.println("");
		switching.generateGraph(); // Generates a GIF Graph File.
		System.out.println("*********************");
		
		
		Merger merger = new Merger();
		
		LTS mergedLTS = merger.ComputeParallelComposition(light,switching);
		//Merge two LTS
		
		System.out.println("*********MergedLTS - "+ mergedLTS.getName() +"********");
		mergedLTS.printTransitions();
		System.out.println("");
		mergedLTS.generateGraph();
		mergedLTS.printLabels();
		System.out.println("**************************");
	}

}
