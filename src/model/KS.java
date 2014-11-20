package model;

import java.util.HashMap;
import java.util.Vector;

/**
 * @author Fadi Asbih
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
public class KS {

	private String name;
	
	private Vector<Transition> transitions; // R
	private Vector<State> states; // S
	private Vector<State> initialStates; // S0
	private HashMap<State, Vector<AtomicProposition>> labels; // L

	public KS() {
		this("Kripke Structure");
	}
	
	public KS(String name) {
		states = new Vector<State>();
		setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}

	/**
	 * @return the states
	 */
	public Vector<State> getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(Vector<State> states) {
		this.states = states;
	}
	
	public void addState(State state) {
		states.add(state);
	}
	
	public void addInitialState(State state) {
		initialStates.add(state);
	}
	
	public void addTransition(Transition transition) {
		transitions.add(transition);
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void addLabel(State key, Vector<AtomicProposition> value) {
		labels.put(key, value);
	}
}
