//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public enum ContainerRole implements IElementEnum {
	OverallContainer,
	MainContainer,
	DialogContainer,
	TileContainer;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContainerRole} from the given specification.
	 */
	public static ContainerRole fromSpecification(final BaseNode specification) {
		return ContainerRole.valueOf(specification.getOneAttributeHeader());
	}
}
