//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum TextMode {
	NORMAL,
	SECRET;
	
	//static method
	public static TextMode fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
