//package declaration
package ch.nolix.core.specificationDatabaseAdapter;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.validator2.Validator;
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;

//class
public final class SpecificationDatabaseAdapter extends DatabaseAdapter {
	
	//attribute
	private final DocumentNodeoid databaseSpecification;
	private final List<EntitySetConnector<Entity>> entitySetConnectors = new List<EntitySetConnector<Entity>>();
	
	//constructor
	public SpecificationDatabaseAdapter(final DocumentNodeoid databaseSpecification, final Schema schema) {
		
		super(schema);
		
		Validator
		.suppose(databaseSpecification)
		.thatIsNamed("database specification")
		.isInstance();
		
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
	public void saveChangesToDatabase(final Iterable<Entity> changedEntitiesInOrder) {
		
		final var createdEntities =
		new ReadContainer<Entity>(changedEntitiesInOrder)
		.getRefSelected(e -> e.isCreated());
		
		for (final var e : createdEntities) {
			getEntitySetConnector(e.getParentEntitySet()).add(e);
		}
		
		final var changedEntities =
		new ReadContainer<Entity>(changedEntitiesInOrder)
		.getRefSelected(e -> e.isChanged());
		
		for (final var e : changedEntities) {
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
