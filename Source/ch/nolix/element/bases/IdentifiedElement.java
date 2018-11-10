//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.skillInterfaces.Identified;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.Element;

//abstract class
public abstract class IdentifiedElement
extends Element
implements Identified {

	//attribute
	private final int id;
	
	//constructor
	public IdentifiedElement(final int id) {
		
		Validator
		.suppose(id)
		.thatIsNamed(VariableNameCatalogue.ID)
		.isPositive();
		
		this.id = id;
	}
	
	//method
	public List<DocumentNode> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		attributes.addAtEnd(
			new DocumentNode(
				VariableNameCatalogue.ID,
				DocumentNode.createFromLong(getId())
			)
		);
		
		return attributes;
	}
	
	//method
	public final int getId() {
		return id;
	}
}
