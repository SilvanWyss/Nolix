//package declaration
package ch.nolix.element.bases;

import ch.nolix.core.constants.PascalCaseNameCatalogue;
//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.core.Element;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class OptionalIdentifiedElement extends Element {

	//optional attribute
	private int id;
	
	//constructor
	public OptionalIdentifiedElement() {
		id = -1;
	}
	
	//constructor
	public OptionalIdentifiedElement(final int id) {
		setId(id);
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (hasId()) {
			attributes.addAtEnd(
				new StandardSpecification(
					PascalCaseNameCatalogue.ID,
					String.valueOf(getId()))
			);
		}
		
		return attributes;
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
	public final boolean hasId(final int id) {
		
		if (!hasId()) {
			return false;
		}
		
		return (getId() == id);
	}
	
	//method
	protected void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ID:
				setId(attribute.getOneAttributeAsInt());
				break;
			default:
				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	private void setId(final int id) {
		
		Validator
		.suppose(id)
		.thatIsNamed(VariableNameCatalogue.ID)
		.isPositive();
		
		this.id = id;
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
