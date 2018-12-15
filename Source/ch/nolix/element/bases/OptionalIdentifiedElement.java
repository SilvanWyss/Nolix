//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.Element;

//abstract class
public abstract class OptionalIdentifiedElement<OIE extends OptionalIdentifiedElement<OIE>>
extends Element<OIE> {

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
	@Override
	public List<DocumentNode> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (hasId()) {
			attributes.addAtEnd(
				new DocumentNode(
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
	@Override
	protected void addOrChangeAttribute(final DocumentNodeoid attribute) {
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
