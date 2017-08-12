/*
 * file:	ContainerRole.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	10
 */

//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.specification.StandardSpecification;

//enum
public enum ContainerRole {
	OverallContainer,
	MainContainer;
	
	public StandardSpecification getSpecificationAs(final String type) {
		return new StandardSpecification(type, toString());
	}
}
