//package declaration
package ch.nolix.system.nodeDatabaseAdapter;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.fileNode.FileNode;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;
import ch.nolix.system.databaseAdapter.Schema;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.IEntitySet;

//class
public final class NodeDatabaseAdapter extends DatabaseAdapter {
	
	//attribute
	private final BaseNode database;
	
	//multi-attribute
	private final LinkedList<EntitySetAdapter<Entity>> entitySetAdapters = new LinkedList<>();
	
	//constructor
	public NodeDatabaseAdapter(final BaseNode database, final Schema schema) {
		
		//Calls constructor of the base class.
		super(schema);
		
		//Asserts that the given database if not null.
		Validator.assertThat(database).thatIsNamed(VariableNameCatalogue.DATABASE).isNotNull();
		
		//Sets the database of the current document node database adapter.
		this.database = database;
		
		for (final var a : database.getRefAttributes(a -> a.hasHeader("EntitySet"))) {
			entitySetAdapters.addAtEnd(
				new EntitySetAdapter<>(
					a,
					schema.getRefEntityTypeByName(a.getRefFirstAttribute().getOneAttributeHeader()),
					getValueCreator()
				)
			);
		}
	}
	
	//constructor
	public NodeDatabaseAdapter(final String fileNodePath, final Schema schema) {
		
		//Calls other constructor.
		this(new FileNode(fileNodePath), schema);
	}
	
	//method
	@Override
	public DatabaseAdapter createNewDatabaseAdapter() {
		return new NodeDatabaseAdapter(database, getSchema());
	}

	//method
	@Override
	public String getDatabaseName() {
		return
		database.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getOneAttributeHeader();
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected <E extends Entity, ES extends IEntitySet<E>> EntitySetAdapter<E> getEntitySetAdapter(
		final ES entitySet
	) {
		return
		(EntitySetAdapter<E>)
		entitySetAdapters.getRefFirst(esc -> esc.hasSameNameAs(entitySet));
	}
		
	//method
	@Override
	protected void saveChangesToDatabase(final IContainer<Entity> mutatedEntitiesInOrder) {
		
		mutatedEntitiesInOrder.forEach(Entity::supposeCanBeSaved);
		
		final var newEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isNew());
		
		for (final var e : newEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).add(e);
		}
		
		final var concernedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isConcerned());
		
		//TODO: Handle concernedEntities more suitable.
		for (final var e : concernedEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).update(e);
		}
		
		final var editedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isEdited());
		
		for (final var e : editedEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).update(e);
		}
		
		final var deletedEntities =
		mutatedEntitiesInOrder.getRefSelected(e -> e.isDeleted());
		
		for (final var e : deletedEntities) {
			getEntitySetAdapter(e.getParentEntitySet()).delete(e);
		}
	}
}
