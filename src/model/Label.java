package model;

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
public class Label {

	private String name;
	private State state;
	private Vector<AtomicProposition> atomicPropositions;
	
	public Label () {
		
	}
	
	public Label(State state, AtomicProposition atomicProposition) {
		setState(state);
		setAtomicPropositions(atomicPropositions);
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

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the atomicPropositions
	 */
	public Vector<AtomicProposition> getAtomicPropositions() {
		return atomicPropositions;
	}

	/**
	 * @param atomicPropositions the atomicPropositions to set
	 */
	public void setAtomicPropositions(Vector<AtomicProposition> atomicPropositions) {
		this.atomicPropositions = atomicPropositions;
	}
}
