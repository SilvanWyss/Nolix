//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.elementapi.IElement;

//enum
/**
 * A {@link AccordionExpansionBehavior} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2018-08-13
 */
public enum AccordionExpansionBehavior implements IElement<AccordionExpansionBehavior> {
	OPEN_ONE_TAB_OR_NONE,
	OPEN_ONE_TAB,
	OPEN_SEVERAL_TABS_OR_NONE,
	OPEN_SEVERAL_TABS;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link AccordionExpansionBehavior} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static AccordionExpansionBehavior fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
