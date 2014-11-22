package controller;

import model.Event;
import model.LTS;
import model.Merger;
import model.State;
import model.Transition;


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


public class RunMiniProject1 {

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
		Event lts1_press = new Event("press");
		Event lts1_hold = new Event("hold");
		
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
		
		System.out.println("*********LTS1 - " + lts1.getName() + "********");
		lts1.printTransitions();
		System.out.println("");
		lts1.generateGraph(); // Generates a GIF Graph File.
		System.out.println("*********************");
		
		//*********LTS 2**************
		
		LTS lts2 = new LTS("Switch");
		
		//LTS 2 States
		State lts2_state_rel = new State("rel");
		State lts2_state_pr = new State("pr");
		
		// LTS 2 Events
		Event lts2_press = new Event("press");
		Event lts2_hold = new Event("hold");
		Event lts2_release = new Event("release");
		
		// LTS 2 Transitions
		Transition lts2_t1 = new Transition(lts2_state_rel, lts2_press, lts2_state_pr);
		Transition lts2_t2 = new Transition(lts2_state_pr, lts2_hold, lts2_state_pr);
		Transition lts2_t3 = new Transition(lts2_state_pr, lts2_release, lts2_state_rel);


		lts2.addTransition(lts2_t1);
		lts2.addTransition(lts2_t2);
		lts2.addTransition(lts2_t3);
		
		lts2.addInitialState(lts2_state_rel);
		
		System.out.println("*********LTS2 - "+ lts2.getName() +"********");
		lts2.printTransitions();
		System.out.println("");
		lts2.generateGraph(); // Generates a GIF Graph File.
		System.out.println("*********************");
		
		Merger merger = new Merger();
		
		LTS mergedLTS = merger.ComputeParallelComposition(lts1,lts2);
		//Merge two LTS
		
		System.out.println("*********MergedLTS - "+ mergedLTS.getName() +"********");
		mergedLTS.printTransitions();
		System.out.println("");
		mergedLTS.generateGraph();
		System.out.println("**************************");
		
		
//		merger.printToDotFile("LtsDiagram.dot",mergedLTS);
		
		
		LTS lts3 = new LTS("Converse");
		
		// The States that we have incl. the initial State.
		State lts3_state_0 = new State("x");
		State lts3_state_1 = new State("y");
		State lts3_state_2 = new State("z");
		
		// Events
		Event lts3_think = new Event("think");
		Event lts3_talk = new Event("talk");

		// Transitions
		Transition lts3_t1 = new Transition(lts3_state_0, lts3_think, lts3_state_1);
		Transition lts3_t2 = new Transition(lts3_state_1, lts3_talk, lts3_state_2);

		lts3.addTransition(lts3_t1);
		lts3.addTransition(lts3_t2);
		
		lts3.addInitialState(lts3_state_0);
		
		System.out.println("*********LTS3 - " + lts3.getName() + "********");
		lts3.printTransitions();
		System.out.println("");
		lts3.generateGraph(); // Generates a GIF Graph File.
		System.out.println("*********************");
		
		LTS lts4 = new LTS("Itch");
		
		// The States that we have incl. the initial State.
		State lts4_state_a = new State("a");
		State lts4_state_b = new State("b");
		
		// Events
		Event lts4_scratch = new Event("scratch");

		// Transitions
		Transition lts4_t1 = new Transition(lts4_state_a, lts4_scratch, lts4_state_b);

		lts4.addTransition(lts4_t1);
		
		lts4.addInitialState(lts4_state_a);
		
		System.out.println("*********LTS4 - " + lts4.getName() + "********");
		lts4.printTransitions();
		System.out.println("");
		lts4.generateGraph(); // Generates a GIF Graph File.
		System.out.println("*********************");
		
		Merger merger2 = new Merger();
		
		LTS mergedLTS2 = merger2.ComputeParallelComposition(lts3,lts4);
		//Merge two LTS
		
		System.out.println("*********MergedLTS - "+ mergedLTS2.getName() +"********");
		mergedLTS2.printTransitions();
		System.out.println("");
		mergedLTS2.generateGraph();
		System.out.println("**************************");
	}

}
