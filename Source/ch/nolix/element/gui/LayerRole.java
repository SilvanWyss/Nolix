//package declaration
package ch.nolix.element.gui;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IElementEnum;

//enum
public enum LayerRole implements IElementEnum {
	BACKGROUND_LAYER,
	MAIN_LAYER,
	SUP_LAYER,
	DIALOG_LAYER;
	
	//static method
	public static LayerRole fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader());
	}
}
