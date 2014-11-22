package model;

import java.util.Vector;

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
public class State {
	
	private String name;
	private Vector<String> mergedNames;
	private Vector<AtomicProposition> atomicPropositions;

	public State(String name) {
		this.name=name;
		mergedNames = new Vector<String>();
		atomicPropositions = new Vector<AtomicProposition>();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void mergeToState(String name)
	{
		mergedNames.add(name);
		this.name += "_" + name;
	}
	
	public Vector<String> getMergedNames() {
		return mergedNames;
	}	
	
	/**
	 * is a labeling function that labels each state with a set of Atomic Propositions that are true in this state.
	 * 
	 * @param atomicProposition
	 * @param active
	 */
	public void labelingFunction(AtomicProposition atomicProposition, boolean active) {
		if(active) { // Add only set of atomic propositions that are true in this state	
			atomicPropositions.add(atomicProposition);
		}
		else {
			atomicPropositions.add(null);
		}
	}


	public Vector<AtomicProposition> getAtomicPropositions() {
		return atomicPropositions;
	}
	
	public void setAtomicPropositions(Vector<AtomicProposition> atomicPropositions) {
		this.atomicPropositions = atomicPropositions;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
}
