//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @date 2018-05-18
 */
public enum ButtonRole {
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
	 * @throws InvalidArgumentException if the given specification does nor represent {@link ButtonRole}.
	 */
	public static ButtonRole fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
