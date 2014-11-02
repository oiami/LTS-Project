package controller;

import model.Alphabet;
import model.Event;
import model.LTSDiagramm;
import model.State;
import model.Transition;
import model.LTS;


/**
 * @author fadi
 *
 * Copyright (c) 2014
 *
 * TERMS AND CONDITIONS:
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */


public class RunApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//*******LTS 1**************
		LTS lts1 = new LTS("Light");
		
		// The States that we have incl. the initial State.
		State lts1_state_off = new State("off");
		State lts1_state_low = new State("low");
		State lts1_state_high = new State("high");
		
		// Events
		Event lts1_press = new Event(Alphabet.symbol.press);
		Event lts1_hold = new Event(Alphabet.symbol.hold);
		
		// Transitions
		Transition lts1_t1 = new Transition(lts1_state_off, lts1_press, lts1_state_low);
		Transition lts1_t2 = new Transition(lts1_state_low, lts1_hold, lts1_state_high);
		Transition lts1_t3 = new Transition(lts1_state_low, lts1_press, lts1_state_off);
		Transition lts1_t4 = new Transition(lts1_state_high, lts1_press, lts1_state_off);

		lts1.addTransition(lts1_t1);
		lts1.addTransition(lts1_t2);
		lts1.addTransition(lts1_t3);
		lts1.addTransition(lts1_t4);
		
		lts1.addInitialState(lts1_state_off);
		
		//*********LTS 2**************
		
		LTS lts2 = new LTS("Switch");
		
		//LTS 2 States
		State lts2_state_rel = new State("rel");
		State lts2_state_pr = new State("pr");
		
		// LTS 2 Events
		Event lts2_press = new Event(Alphabet.symbol.press);
		Event lts2_hold = new Event(Alphabet.symbol.hold);
		Event lts2_release = new Event(Alphabet.symbol.release);
		
		// LTS 2 Transitions
		Transition lts2_t1 = new Transition(lts2_state_rel, lts2_press, lts2_state_pr);
		Transition lts2_t2 = new Transition(lts2_state_pr, lts2_hold, lts2_state_pr);
		Transition lts2_t3 = new Transition(lts2_state_pr, lts2_release, lts2_state_rel);


		lts2.addTransition(lts2_t1);
		lts2.addTransition(lts2_t2);
		lts2.addTransition(lts2_t3);
		
		lts2.addInitialState(lts2_state_rel);
		
		LTSDiagramm diagramm = new LTSDiagramm();
		
		//Merge the two LTS's
		diagramm.addLTS(lts1);
		diagramm.addLTS(lts2);
		diagramm.ComputeParallelCompositionFromFirstTwoLts();
		
		System.out.println("*********LTS1********");
		lts1.printTransitions();
		System.out.println("*********************");
		System.out.println("*********LTS2********");
		lts2.printTransitions();
		System.out.println("*********************");
		System.out.println("*********MergedLTS********");
		diagramm.getLTS().printTransitions();
		System.out.println("**************************");
		
		


	}

}
