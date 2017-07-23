//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 40
 */
public final class LineStructure extends WidgetStructure<LineStructure> {
	
	//method
	/**
	 * @throws InvalidArgumentException
	 */
	protected void addOrChangeAttribute(final StandardSpecification attribute) {
		throw new InvalidArgumentException(
			new ArgumentName("attribute"),
			new Argument(attribute)
		);		
	}

	//method
	/**
	 * @return the attributes of this line structure.
	 */
	protected List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>();
	}

	//method
	/**
	 * Removes the attributes of this line structure.
	 */
	protected void removeAttributes() {}
}
