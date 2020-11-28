//package declaration
package ch.nolix.system.filenodedatabaseschemaadapter;

//own imports
import ch.nolix.common.constant.MultiPascalCaseNameCatalogue;
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.filenode.FileNode;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.databaseschemaadapter.DatabaseState;
import ch.nolix.system.databaseschemaadapter.EntitySet;
import ch.nolix.system.databaseschemaadapter.IEntitySetAdapter;

//class
public final class FileNodeDatabaseSchemaAdapter extends DatabaseSchemaAdapter<FileNodeDatabaseSchemaAdapter> {
	
	//attribute
	private final BaseNode fileNodeDatabase;
	
	//constant
	private static final String DATABASE_PROPERTIES_HEADER = "DatabaseProperties";
	
	//constructor
	public FileNodeDatabaseSchemaAdapter(final BaseNode fileNodeDatabase) {
		
		Validator
		.assertThat(fileNodeDatabase)
		.thatIsNamed("document node database")
		.isNotNull();
		
		this.fileNodeDatabase = fileNodeDatabase;
		
		reset();
	}
	
	//constructor
	public FileNodeDatabaseSchemaAdapter(final String fileNodeFilePath) {
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
			.getRefFirstAttribute(PascalCaseNameCatalogue.STATE)
			.getOneAttributeHeader()
		);
	}
	
	//method
	protected IEntitySetAdapter getEntitySetAdapter(final EntitySet entitySet) {
		return getEntitySetAdapter(entitySet.getName());
	}
	
	//method
	protected IEntitySetAdapter getEntitySetAdapter(final String name) {
		return getEntitySetAdapters().getRefFirst(esc -> esc.hasName(name));
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
		
		fileNodeDatabase.setHeader(PascalCaseNameCatalogue.DATABASE);
		
		fileNodeDatabase.addAttribute(
			new Node(
				DATABASE_PROPERTIES_HEADER,
				new Node(
					PascalCaseNameCatalogue.STATE,
					"Ready"
				)
			)
		);
	}
	
	//method
	@Override
	protected void lockDatabase() {
		getDatabasePropertiesNode()
		.getRefFirstAttribute(PascalCaseNameCatalogue.STATE)
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
		createdEntitySets.forEach(this::addEntitySetToDatabase);
		
		//Handles the changed entity sets.
		changedEntitySets.forEach(this::changeEntitySetOnDatabase);
		
		//Handles the deleted entity sets.
		changedEntitySets.forEach(this::deleteEntitySetFromDatabase);
		
		setDatabaseReady();
	}
	
	//method
	private void addEntitySetToDatabase(final EntitySet entitySet) {
		
		final var entitySetSpecification = Node.fromString("EntitySet");
		
		entitySetSpecification.addAttribute(
			new Node(
				PascalCaseNameCatalogue.NAME,
				entitySet.getName()
			)
		);
		
		for (final var c : entitySet.getRefColumns()) {
			entitySetSpecification.addAttribute(c.getSpecification());
		}
		
		entitySetSpecification
		.addAttribute(Node.fromString(MultiPascalCaseNameCatalogue.ENTITIES));
		
		fileNodeDatabase.addAttribute(entitySetSpecification);
	}

	//method
	private void changeEntitySetOnDatabase(final EntitySet entitySet) {
		//TODO: Implement.
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
		.getRefFirstAttribute(PascalCaseNameCatalogue.STATE)
		.getRefOneAttribute()
		.setHeader(DatabaseState.READY.toString());	
	}
}
