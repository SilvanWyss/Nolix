//package declaration
package ch.nolix.core.specificationDatabaseConnector;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.skillInterfaces.Identified;
import ch.nolix.core.validator2.Validator;

//class
public final class EntityConnector<E extends Entity> implements Identified {

	//attribute
	private final DocumentNodeoid entitySpecification;
	
	//package-visible constructor
	EntityConnector(final DocumentNodeoid entitySpecification) {
		
		Validator
		.suppose(entitySpecification)
		.thatIsNamed("entity specification")
		.isInstance();
		
		this.entitySpecification = entitySpecification;
	}
	
	//method
	public E createPersistedEntity(final EntityType<E> entityType) {
		return entityType.createPersistedEntity(
			getId(),
			entitySpecification.getRefAttributes().getContainerWithoutFirst()
		);
	}
	
	//method
	public int getId() {
		return entitySpecification.getFirstAttributeAsInt();
	}
	
	//method
	public void updateFrom(final E entity) {
		entitySpecification.resetAttributes(
			entity.getRowSpecification().getRefAttributes()
		);
	}
}
