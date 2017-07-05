/*
 * file:	LabelRole.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.element.GUI;

import ch.nolix.core.specification.StandardSpecification;

//enum
public enum LabelRole {
	Title,
	SubTitle,
	Level1Header,
	Level2Header,
	Level3Header,
	Level4Header,
	ParagraphHeader,
	DynamicInfoLabel;
	
	public StandardSpecification getSpecificationAs(final String type) {
		return new StandardSpecification(type, toString());
	}
}
