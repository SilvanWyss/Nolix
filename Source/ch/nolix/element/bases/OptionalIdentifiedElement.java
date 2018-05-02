//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.element.core.Element;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class OptionalIdentifiedElement
extends Element {

	//optional attribute
	private final int id;
	
	//constructor
	public OptionalIdentifiedElement() {
		id = -1;
	}
	
	//constructor
	public OptionalIdentifiedElement(final int id) {
		
		Validator
		.suppose(id)
		.thatIsNamed(VariableNameCatalogue.ID)
		.isPositive();
		
		this.id = id;
	}
	
	//method
	public final int getId() {
		
		supposeHasId();
		
		return id;
	}
	
	//method
	public final boolean hasId() {
		return (id > 0);
	}
	
	//method
	private void supposeHasId() {
		if (!hasId()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.ID
			);
		}
	}
}
