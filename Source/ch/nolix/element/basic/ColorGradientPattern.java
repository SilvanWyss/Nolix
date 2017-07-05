/*
 * file:	ColorGradientPattern.java
 * auhtor:	Silvan Wyss
 * month:	2016-07
 * lines:	20
 */

//package declaration
package ch.nolix.element.basic;

//own import
import ch.nolix.core.specification.StandardSpecification;

//enum
public enum ColorGradientPattern {
	LeftToRight,
	TopToBottom;
	
	//method
	/**
	 * @return the specification of this color gradient pattern
	 */
	public final StandardSpecification getSpecification() {
		return new StandardSpecification(toString());
	}
}
