//package declaration
package ch.nolix.core.documentNodeDatabaseAdapter;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.validator.Validator;
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;

//class
public final class DocumentNodeDatabaseAdapter extends DatabaseAdapter {
	
	//attribute
	private final DocumentNodeoid database;
	
	//multi-attribute
	private final List<EntitySetAdapter<Entity>> entitySetAdapters =
	new List<EntitySetAdapter<Entity>>();
	
	//constructor
	public DocumentNodeDatabaseAdapter(
		final DocumentNodeoid database,
		final Schema schema
	) {
		
		//Calls constructor of the base class.
		super(schema);
		
		//Checks if the given database is not null.
		Validator
		.suppose(database)
		.thatIsNamed(VariableNameCatalogue.DATABASE)
		.isNotNull();
		
		//Sets the database of the current document node database adapter.
		this.database = database;
		
		database
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.forEach(a -> entitySetAdapters.addAtEnd(new EntitySetAdapter<>(a)));
	}
	
	//method
	@Override
	public String getDatabaseName() {
		return
		database.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getOneAttributeAsString();
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected <E extends Entity> EntitySetAdapter<E> getEntitySetAdapter(
		final EntitySet<E> entitySet
	) {
		return
		(EntitySetAdapter<E>)
		entitySetAdapters.getRefFirst(esc -> esc.hasSameNameAs(entitySet));
	}
		
	//method
	@Override
	protected void saveChangesToDatabase(final IContainer<Entity> mutatedEntitiesInOrder) {
		
		final var createdEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isCreated());
		
		for (final var e : createdEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).add(e);
		}
		
		final var concernedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isConcerned());
		
		//TODO: Handle concerned entities more suitable.
		for (final var e : concernedEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).update(e);
		}
		
		final var changedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isChanged());
		
		for (final var e : changedEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).update(e);
		}
		
		final var deletedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isDeleted());
		
		for (final var e : deletedEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).delete(e);
		}
	}
}
