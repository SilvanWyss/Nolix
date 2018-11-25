//package declaration
package ch.nolix.core.documentNodeDatabaseAdapter;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
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
	private final List<EntitySetAdapter<Entity>> entitySetAdapters = new List<EntitySetAdapter<Entity>>();
	
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
		.forEach(a -> entitySetAdapters.addAtEnd(new EntitySetAdapter<>(a)));
	}

	//method
	@SuppressWarnings("unchecked")
	public <E extends Entity> EntitySetAdapter<E> getEntitySetConnector(
		final EntitySet<E> entitySet
	) {
		return
		(EntitySetAdapter<E>)
		entitySetAdapters.getRefFirst(esc -> esc.hasSameNameAs(entitySet));
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
	public void saveChangesToDatabase(final IContainer<Entity> mutatedEntitiesInOrder) {
		
		final var createdEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isCreated());
		
		for (final var e : createdEntities) {
			getEntitySetConnector(e.getParentEntitySet()).add(e);
		}
		
		//TODO: Handle concerned entities more suitable.
		final var concernedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isConcerned());
		
		for (final var e : concernedEntities) {
			getEntitySetConnector(e.getParentEntitySet()).update(e);
		}
		
		final var changedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isChanged());
		
		for (final var e : changedEntities) {
			getEntitySetConnector(e.getParentEntitySet()).update(e);
		}
		
		final var deletedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isDeleted());
		
		for (final var e : deletedEntities) {
			getEntitySetConnector(e.getParentEntitySet()).delete(e);
		}
	}
}
