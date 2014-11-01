package controller;

import model.Alphabet;
import model.Event;
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

		LTS lts1 = new LTS("Light");
		
		// The States that we have incl. the initial State.
		State initialState = new State("off");
		State low = new State("low");
		State high = new State("high");
		
		// Events
		Event press = new Event(Alphabet.symbol.press);
		Event hold = new Event(Alphabet.symbol.hold);
		
		// Transitions
		Transition t1 = new Transition(initialState, press, low);
		Transition t2 = new Transition(low, hold, high);
		Transition t3 = new Transition(low, press, initialState);
		Transition t4 = new Transition(high, hold, initialState);

		lts1.addTransition(t1);
		lts1.addTransition(t2);
		lts1.addTransition(t3);
		lts1.addTransition(t4);
		
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		System.out.println(t4);


	}

}
