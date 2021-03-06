package model;

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
public class Transition {

	private State firstState;
	private Event event;
	private State secondState;
	
	/**
	 * is a transition relation that must be left-total used in Kripke Structure
	 * 
	 * @param firstState
	 * @param secondState
	 */
//	public Transition(State firstState, State secondState) {
//		setFirstState(firstState);
//		setSecondState(secondState);
//	}
	
	public Transition(State firstState, Event event, State secondState) {
		setFirstState(firstState);
		setEvent(event);
		setSecondState(secondState);
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * @return the firstState
	 */
	public State getFirstState() {
		return firstState;
	}

	/**
	 * @param firstState the firstState to set
	 */
	public void setFirstState(State firstState) {
		this.firstState = firstState;
	}

	/**
	 * @return the secondState
	 */
	public State getSecondState() {
		return secondState;
	}

	/**
	 * @param secondState the secondState to set
	 */
	public void setSecondState(State secondState) {
		this.secondState = secondState;
	}

	public String toString() {
		return this.getFirstState().toString() + "," + this.getEvent().toString() + "," + this.getSecondState().toString();
	}
}
