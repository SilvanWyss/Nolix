//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * A {@link AccordionExpansionBehavior} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2018-08-13
 */
public enum AccordionExpansionBehavior {
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
	public static AccordionExpansionBehavior fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
