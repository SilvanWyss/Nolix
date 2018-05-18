//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

//enum
/**
 * A {@link ButtonRole} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 30
 */
public enum ButtonRole implements ISpecifiedEnum {
	ActionButton,
	LinkButton,
	CreateButton,
	DeleteButton,
	SaveButton,
	CancelButton;
	
	//constant
	public static final String TYPE_NAME = "ButtonRole";

	//static method
	/**
	 * @param specification
	 * @return a new {@link ButtonRole} from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static ButtonRole createFromSpecification(
		final Specification specification
	) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
