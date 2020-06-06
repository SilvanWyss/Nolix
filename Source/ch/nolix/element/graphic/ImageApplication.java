//package declaration
package ch.nolix.element.graphic;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
public enum ImageApplication implements IElementEnum {
	ScaleToFrame,
	Repeate;
	
	//static method
	public static ImageApplication fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
