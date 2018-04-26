//package declaration
package ch.nolix.core.specificationDatabaseConnector;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.interfaces.Identifiable;
import ch.nolix.core.specification.Specification;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntityConnector<E extends Entity> implements Identifiable {

	//attribute
	private final Specification entitySpecification;
	
	//package-visible constructor
	EntityConnector(final Specification entitySpecification) {
		
		Validator
		.suppose(entitySpecification)
		.thatIsNamed("entity specification")
		.isNotNull();
		
		this.entitySpecification = entitySpecification;
	}
	
	//method
	public int getId() {
		return entitySpecification.getFirstAttributeAsInt();
	}
	
	//method
	public void updateFrom(final E entity) {
		entitySpecification.resetAttributes(entity.getRowSpecification().getRefAttributes());
	}
}
