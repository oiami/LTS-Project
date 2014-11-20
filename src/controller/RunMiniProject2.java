package controller;

import java.util.Vector;

import model.AtomicProposition;
import model.Event;
import model.KS;
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
		// *******KS 1**************
		KS light = new KS("Light");

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
		
		// Transitions, is a transition relation that must be left-total
		Transition t1 = new Transition(stateOff, stateLow);
		Transition t2 = new Transition(stateLow, stateHigh);
		Transition t3 = new Transition(stateLow, stateOff);
		Transition t4 = new Transition(stateHigh, stateOff);
		
		light.addTransition(t1);
		light.addTransition(t2);
		light.addTransition(t3);
		light.addTransition(t4);
		
		// AP, certain atomic propositions
		AtomicProposition lightOn = new AtomicProposition("lightOn");
		AtomicProposition highBattUse = new AtomicProposition("highBattUse");

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

		
		
	}

}
