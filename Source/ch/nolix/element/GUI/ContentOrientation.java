/*
 * file:	ContentOrientation.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.common.specification.Specification;

//enum
public enum ContentOrientation {
	LeftTop,
	Left,
	LeftBottom,
	Top,
	Center,
	Bottom,
	RightTop,
	Right,
	RightBottom;
	
	public static final String SIMPLE_CLASS_NAME = "ContentOrientation";
	
	public final Specification getSpecification() {
		return new Specification(SIMPLE_CLASS_NAME, toString());
	}
}
