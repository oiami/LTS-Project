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

	public State(String name) {
		this.name=name;
		mergedNames = new Vector<String>();
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
		this.name += ", " + name;
	}
	
	public String toString() {
		return this.getName();
	}

	public Vector<String> getMergedNames() {
		return mergedNames;
	}
	
	public boolean isEqualTo(State s)
	{
		if(this.getMergedNames().size() == s.getMergedNames().size())
		{
			for(String name : this.getMergedNames())
				if(!s.getMergedNames().contains(name))
				{
					return false;
				}
		}
		else
		{
			return false;
		}
		return true;

	}

}
