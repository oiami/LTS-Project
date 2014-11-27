package model;

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
public abstract class Operator implements IForumla {

	private String name;
	
	private IForumla formula1;
	private IForumla formula2;
	
	public Operator() {
		
	}
	
	public Operator(IForumla formula1) {
		
	}
	
	public Operator(IForumla forumla1, IForumla forumla2) {
		
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
	 * @return the formula1
	 */
	public IForumla getFormula1() {
		return formula1;
	}

	/**
	 * @param formula1 the formula1 to set
	 */
	public void setFormula1(IForumla formula1) {
		this.formula1 = formula1;
	}

	/**
	 * @return the formula2
	 */
	public IForumla getFormula2() {
		return formula2;
	}

	/**
	 * @param formula2 the formula2 to set
	 */
	public void setFormula2(IForumla formula2) {
		this.formula2 = formula2;
	}
}
