//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2018-05-18
 * @lines 40
 */
public enum ButtonRole implements IElement<ButtonRole> {
	ACTION_BUTTON,
	LINK_BUTTON,
	CREATE_BUTTON,
	DELETE_BUTTON,
	SAVE_BUTTON,
	CONFIRM_BUTTON,
	CANCEL_BUTTON;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ButtonRole} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ButtonRole fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
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
