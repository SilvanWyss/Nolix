//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum ItemMenuItemRole {
	NORMAL_ITEM,
	EMPTY_ITEM;
	
	//static method
	public static ItemMenuItemRole fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
