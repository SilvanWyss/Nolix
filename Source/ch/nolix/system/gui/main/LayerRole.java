//package declaration
package ch.nolix.system.gui.main;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum LayerRole {
	BACKGROUND_LAYER,
	MAIN_LAYER,
	SUP_LAYER,
	DIALOG_LAYER;
	
	//static method
	public static LayerRole fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
