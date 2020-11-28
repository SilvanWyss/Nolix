//package declaration
package ch.nolix.system.baseguiclient;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 20
 */
public enum BaseFrontGUIClientGUIType implements IElementEnum {
	LayerGUI,
	CanvasGUI;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link BaseFrontGUIClientGUIType} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static BaseFrontGUIClientGUIType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader());
	}
}
