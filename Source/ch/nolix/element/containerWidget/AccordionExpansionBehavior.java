//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
/**
 * A {@link AccordionExpansionBehavior} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 30
 */
public enum AccordionExpansionBehavior implements IElementEnum {
	SingleOrNone,
	Single,
	MultiOrNone,
	Multi;
	
	//constant
	public static final String TYPE_NAME = "ExpansionBehavior";

	//static method
	/**
	 * @param specification
	 * @return a new {@link AccordionExpansionBehavior} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static AccordionExpansionBehavior fromSpecification(
		final BaseNode specification
	) {
		return valueOf(specification.getOneAttributeAsString());
	}
}