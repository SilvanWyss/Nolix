/*
 * file:	ContentOrientation.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	20
 */

//package declaration
package ch.nolix.element.dialog;

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
	
	public final static String SIMPLE_CLASS_NAME = "ContentOrientation";
	
	public final Specification getSpecification() {
		return new Specification(SIMPLE_CLASS_NAME, toString());
	}
}
