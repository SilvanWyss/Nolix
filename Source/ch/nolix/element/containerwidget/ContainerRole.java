//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public enum ContainerRole implements IElementEnum {
	OVERALL_CONTAINTER,
	MAINT_CONTAINER,
	DIALOG_CONTAINER,
	TITLE_CONTAINER;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContainerRole} from the given specification.
	 */
	public static ContainerRole fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return new Node(StringHelper.toPascalCase(toString())).intoList();
	}
}
