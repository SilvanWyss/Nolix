//package declaration
package ch.nolix.system.fileNodeDatabaseAdapter;

import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.fileNode.FileNode;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.databaseAdapter.Schema;

//class
public final class FileNodeDatabaseAdapter extends DatabaseAdapter {
	
	//attribute
	private final BaseNode database;
	
	//multi-attribute
	private final List<EntitySetAdapter<Entity>> entitySetAdapters = new List<>();
	
	//constructor
	public FileNodeDatabaseAdapter(final BaseNode database, final Schema schema) {
		
		//Calls constructor of the base class.
		super(schema);
		
		//Checks if the given database if not null.
		Validator.suppose(database).thatIsNamed(VariableNameCatalogue.DATABASE).isNotNull();
		
		//Sets the database of the current document node database adapter.
		this.database = database;
		
		for (final var a : database.getRefAttributes(a -> a.hasHeader("EntitySet"))) {
			entitySetAdapters.addAtEnd(
				new EntitySetAdapter<>(
					a,
					schema.getRefEntityTypeByName(a.getRefFirstAttribute().getOneAttributeAsString()),
					getValueCreator()
				)
			);
		}
	}
	
	//constructor
	public FileNodeDatabaseAdapter(final String fileNodePath, final Schema schema) {
		
		//Calls other constructor.
		this(new FileNode(fileNodePath), schema);
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
