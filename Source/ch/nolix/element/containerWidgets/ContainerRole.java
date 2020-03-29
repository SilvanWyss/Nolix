//package declaration
package ch.nolix.element.containerWidgets;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public enum ContainerRole implements IElementEnum {
	OverallContainer,
	MainContainer;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContainerRole} from the given specification.
	 */
	public static ContainerRole fromSpecification(final BaseNode specification) {
		return ContainerRole.valueOf(specification.getOneAttributeAsString());
	}
}
