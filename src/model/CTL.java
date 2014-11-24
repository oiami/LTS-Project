package model;

import java.util.ArrayList;
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
public class CTL {

	private String pathQuantifier;
	private String temporalOperator;
	private String booleanOperator;
	private AtomicProposition atomicProposition;
	private ArrayList<String> markedStates;
	
	public CTL() {
		
	}
	
	public CTL(String pathQuantifier, String temporalOperator, String booleanOperator, AtomicProposition atomicProposition) {
		setPathQuantifier(pathQuantifier);
		setTemporalOperator(temporalOperator);
		setBooleanOperator(booleanOperator);
		setAtomicProposition(atomicProposition);
	}

	/**
	 * marks all states that satisfy EX
	 * @param lts
	 */
	public boolean checkEX(LTS lts) {
		lts = lts.mapToKS();
		Vector<State> states = lts.getInitialStates();
		
		return true;
	}
	
	/**
	 * marks all states that satisfy EG
	 * @param lts
	 */
	public void checkEG(LTS lts) {
		lts = lts.mapToKS();
	}
	
	/**
	 * marks all states that satisfy E[φ U ψ]
	 * @param lts
	 */
	public void checkEU(LTS lts) {
		lts = lts.mapToKS();
	}
	
	public void checkModel(LTS lts) {
		
	}
	/**
	 * @return the pathQuantifier
	 */
	public String getPathQuantifier() {
		return pathQuantifier;
	}

	/**
	 * @param pathQuantifier the pathQuantifier to set
	 */
	public void setPathQuantifier(String pathQuantifier) {
		this.pathQuantifier = pathQuantifier;
	}

	/**
	 * @return the temporalOperator
	 */
	public String getTemporalOperator() {
		return temporalOperator;
	}

	/**
	 * @param temporalOperator the temporalOperator to set
	 */
	public void setTemporalOperator(String temporalOperator) {
		this.temporalOperator = temporalOperator;
	}

	/**
	 * @return the booleanOperator
	 */
	public String getBooleanOperator() {
		return booleanOperator;
	}

	/**
	 * @param booleanOperator the booleanOperator to set
	 */
	public void setBooleanOperator(String booleanOperator) {
		this.booleanOperator = booleanOperator;
	}

	/**
	 * @return the atomicProposition
	 */
	public AtomicProposition getAtomicProposition() {
		return atomicProposition;
	}

	/**
	 * @param atomicProposition the atomicProposition to set
	 */
	public void setAtomicProposition(AtomicProposition atomicProposition) {
		this.atomicProposition = atomicProposition;
	}
	
	
}
