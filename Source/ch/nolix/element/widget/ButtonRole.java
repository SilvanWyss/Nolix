//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 40
 */
public enum ButtonRole implements IElement {
	ACTION_BUTTON,
	LINK_BUTTON,
	CREATE_BUTTON,
	DELETE_BUTTON,
	SAVE_BUTTON,
	CONFIRM_BUTTON,
	CANCEL_BUTTON;
	
	//constant
	public static final String TYPE_NAME = "ButtonRole";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ButtonRole} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ButtonRole fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(StringHelper.toPascalCase(toString())));
	}
}
