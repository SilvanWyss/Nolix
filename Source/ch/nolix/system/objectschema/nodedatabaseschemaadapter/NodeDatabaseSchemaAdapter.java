//package declaration
package ch.nolix.system.objectschema.nodedatabaseschemaadapter;

import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.filenode.FileNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.databaseschemaadapter.Column;
import ch.nolix.system.objectschema.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.objectschema.databaseschemaadapter.DatabaseState;
import ch.nolix.system.objectschema.databaseschemaadapter.EntitySet;
import ch.nolix.system.objectschema.databaseschemaadapter.IEntitySetAdapter;

//class
public final class NodeDatabaseSchemaAdapter extends DatabaseSchemaAdapter<NodeDatabaseSchemaAdapter> {
	
	//attribute
	private final BaseNode fileNodeDatabase;
	
	//constant
	private static final String DATABASE_PROPERTIES_HEADER = "DatabaseProperties";
	
	//constructor
	public NodeDatabaseSchemaAdapter(final BaseNode fileNodeDatabase) {
		
		Validator
		.assertThat(fileNodeDatabase)
		.thatIsNamed("document node database")
		.isNotNull();
		
		this.fileNodeDatabase = fileNodeDatabase;
		
		reset();
	}
	
	//constructor
	public NodeDatabaseSchemaAdapter(final String fileNodeFilePath) {
		this(new FileNode(fileNodeFilePath));
	}
	
	//method
	public DatabaseState getDatabaseState() {
		
		if (!fileNodeDatabase.hasHeader()) {
			return DatabaseState.UNINITIALIZED;
		}
		
		return
		DatabaseState.valueOf(		
			getDatabasePropertiesNode()
			.getRefFirstAttribute(PascalCaseCatalogue.STATE)
			.getOneAttributeHeader()
		);
	}
	
	//method
	@Override
	protected LinkedList<IEntitySetAdapter> getEntitySetAdapters() {
		return
		fileNodeDatabase
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.to(EntitySetAdapter::new);
	}
	
	//method
	@Override
	protected void initializeDatabaseWhenNotInitialized() {
		
		fileNodeDatabase.setHeader(PascalCaseCatalogue.DATABASE);
		
		final var propertiesSpecification = new Node();
		propertiesSpecification.setHeader(DATABASE_PROPERTIES_HEADER);
		propertiesSpecification.addAttribute(
			Node.withHeaderAndAttribute(PascalCaseCatalogue.STATE, DatabaseState.READY.toString())
		);
		
		fileNodeDatabase.addAttribute(propertiesSpecification);
	}
	
	//method
	@Override
	protected void lockDatabase() {
		getDatabasePropertiesNode()
		.getRefFirstAttribute(PascalCaseCatalogue.STATE)
		.getRefOneAttribute()
		.setHeader(DatabaseState.LOCKED.toString());
	}
	
	//method
	@Override
	protected void saveChangesToDatabaseAndSetDatabaseReady(final IContainer<EntitySet> entitySets) {
		
		final var createdEntitySets = new LinkedList<EntitySet>();
		final var changedEntitySets = new LinkedList<EntitySet>();
		final var deletedEntitySets = new LinkedList<EntitySet>();
		
		//Iterates the given entity sets.
		for (final var es : entitySets) {
			
			//Enumerates the state of the current entity set.
			switch (es.getState()) {
				case NEW:
					createdEntitySets.addAtEnd(es);
					break;
				case EDITED:
					changedEntitySets.addAtEnd(es);
					break;
				case DELETED:
					deletedEntitySets.addAtEnd(es);
					break;
				default:
					throw new InvalidArgumentException(es, "has the state " + es.getState());
			}
		}
		
		//Handles the created entity sets.
		createdEntitySets.forEach(this::saveAddEntitySet);
		
		//Handles the changed entity sets.
		changedEntitySets.forEach(this::changeEntitySetOnDatabase);
		
		//Handles the deleted entity sets.
		changedEntitySets.forEach(this::deleteEntitySetFromDatabase);
		
		setDatabaseReady();
	}
	
	//method
	@Override
	protected void saveAddEntitySet(final EntitySet entitySet) {
		fileNodeDatabase.addAttribute(createSpecificationFor(entitySet));
	}
	
	//method
	private void changeEntitySetOnDatabase(final EntitySet entitySet) {
		//TODO: Implement.
	}
	
	//method
	private Node createSpecificationFor(final EntitySet entitySet) {
		
		final var node = Node.withHeader(ObjectProtocol.ENTITY_SET);
		
		node.addAttribute(Node.withHeaderAndAttribute(PascalCaseCatalogue.NAME, entitySet.getName()));
		
		for (final var c : entitySet.getRefColumns()) {
			node.addAttribute(createSpecificationFor(c));
		}
		
		node.addAttribute(Node.withHeader(ObjectProtocol.ENTITIES));
		
		return node;
	}
	
	//method
	private Node createSpecificationFor(final Column column) {
		return
		Node.withAttribute(
			Node.withHeaderAndAttribute(PascalCaseCatalogue.HEADER, column.getName()),
			Node.withHeader(column.getPropertyKind().toString())
		);
	}
	
	//method
	private void deleteEntitySetFromDatabase(final EntitySet es) {
		fileNodeDatabase.removeFirstAttribute(
			a ->
				a.hasHeader("EntitySet")
				&& new EntitySetAdapter(a).hasName(es.getName())
		);
	}
	
	//method
	private BaseNode getDatabasePropertiesNode() {
		return fileNodeDatabase.getRefFirstAttribute(DATABASE_PROPERTIES_HEADER);
	}
	
	//method
	private void setDatabaseReady() {
		getDatabasePropertiesNode()
		.getRefFirstAttribute(PascalCaseCatalogue.STATE)
		.getRefOneAttribute()
		.setHeader(DatabaseState.READY.toString());	
	}
}
