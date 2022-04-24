//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2018-05-18
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
