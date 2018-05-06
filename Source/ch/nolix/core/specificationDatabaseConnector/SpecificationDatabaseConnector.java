//package declaration
package ch.nolix.core.specificationDatabaseConnector;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.databaseAdapter.IDatabaseConnector;
import ch.nolix.core.databaseAdapter.IEntitySetConnector;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.core.specification.Specification;
import ch.nolix.primitive.validator2.Validator;

//class
public final class SpecificationDatabaseConnector
implements IDatabaseConnector<IFunction>{
	
	//attribute
	private final Specification databaseSpecification;
	private final List<EntitySetConnector<Entity>> entitySetConnectors = new List<EntitySetConnector<Entity>>();
	
	//constructor
	public SpecificationDatabaseConnector(final Specification databaseSpecification) {
		
		Validator
		.suppose(databaseSpecification)
		.thatIsNamed("database specification")
		.isNotNull();
		
		this.databaseSpecification = databaseSpecification;
		
		databaseSpecification
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.forEach(a -> entitySetConnectors.addAtEnd(new EntitySetConnector<>(a)));
	}

	//method
	@SuppressWarnings("unchecked")
	public <E extends Entity> EntitySetConnector<E> getEntitySetConnector(
		final EntitySet<E> entitySet
	) {
		return
		(EntitySetConnector<E>)
		entitySetConnectors.getRefFirst(esc -> esc.hasSameNameAs(entitySet));
	}
	
	//method
	public String getName() {
		return
		databaseSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getOneAttributeAsString();
	}

	//method
	public void run(IContainer<IFunction> commands) {
		commands.forEach(c -> c.run());
	}

	//method
	public void saveChanges(final Iterable<Entity> changedEntitiesInOrder) {
		
		final var createdEntities =
		new ReadContainer<Entity>(changedEntitiesInOrder)
		.getRefSelected(e -> e.isCreated());
		
		for (final var e : createdEntities) {
			getEntitySetConnector(e.getParentEntitySet()).add(e);
		}
		
		final var updatedEntities =
		new ReadContainer<Entity>(changedEntitiesInOrder)
		.getRefSelected(e -> e.isUpdated());
		
		for (final var e : updatedEntities) {
			getEntitySetConnector(e.getParentEntitySet()).update(e);
		}
		
		final var deletedEntities =
		new ReadContainer<Entity>(changedEntitiesInOrder)
		.getRefSelected(e -> e.isDeleted());
		
		for (final var e : deletedEntities) {
			getEntitySetConnector(e.getParentEntitySet()).delete(e);
		}
	}
}
