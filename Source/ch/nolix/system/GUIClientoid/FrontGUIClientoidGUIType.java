//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.core.node.BaseNode;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 20
 */
public enum FrontGUIClientoidGUIType implements IElementEnum {
	LayerGUI,
	CanvasGUI;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link FrontGUIClientoidGUIType} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static FrontGUIClientoidGUIType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
