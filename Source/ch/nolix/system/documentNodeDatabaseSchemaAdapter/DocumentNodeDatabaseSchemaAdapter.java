//package declaration
package ch.nolix.system.documentNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.system.databaseSchemaAdapter.DatabaseState;
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.databaseSchemaAdapter.IEntitySetAdapter;

//class
public final class DocumentNodeDatabaseSchemaAdapter
extends DatabaseSchemaAdapter<DocumentNodeDatabaseSchemaAdapter> {
	
	//attribute
	private final DocumentNodeoid documentNodeDatabase;
	
	//constant
	private static final String DATABASE_PROPERTIES_HEADER = "DatabaseProperties";
	
	//constructor
	public DocumentNodeDatabaseSchemaAdapter(final DocumentNodeoid documentNodeDatabase) {
		
		Validator
		.suppose(documentNodeDatabase)
		.thatIsNamed("document node database")
		.isNotNull();
		
		this.documentNodeDatabase = documentNodeDatabase;
		
		reset();
	}
	
	//method
	public DatabaseState getDatabaseState() {
		
		if (!documentNodeDatabase.hasHeader()) {
			return DatabaseState.Uninitialized;
		}
		
		return
		DatabaseState.valueOf(		
			getDatabasePropertiesDocumentNode()
			.getRefFirstAttribute(PascalCaseNameCatalogue.STATE)
			.getOneAttributeAsString()
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
	protected List<IEntitySetAdapter> getEntitySetAdapters() {
		return
		documentNodeDatabase
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.to(a -> new EntitySetAdapter(a));
	}
	
	//method
	@Override
	protected void initializeDatabaseWhenNotInitialized() {
		
		documentNodeDatabase.setHeader(PascalCaseNameCatalogue.DATABASE);
		
		documentNodeDatabase.addAttribute(
			new DocumentNode(
				DATABASE_PROPERTIES_HEADER,
				new DocumentNode(
					PascalCaseNameCatalogue.STATE,
					"Ready"
				)
			)
		);
	}
	
	//method
	@Override
	protected void lockDatabase() {
		getDatabasePropertiesDocumentNode()
		.getRefFirstAttribute(PascalCaseNameCatalogue.STATE)
		.getRefOneAttribute()
		.setHeader(DatabaseState.Locked.toString());
	}
	
	//method
	@Override
	protected void saveChangesToDatabaseAndSetDatabaseReady(final IContainer<EntitySet> entitySets) {
		
		final var createdEntitySets = new List<EntitySet>();
		final var changedEntitySets = new List<EntitySet>();
		final var deletedEntitySets = new List<EntitySet>();
		
		//Iterates the given entity sets.
		for (final var es : entitySets) {
			
			//Enumerates the state of the current entity set.
			switch (es.getState()) {
				case CREATED:
					createdEntitySets.addAtEnd(es);
					break;
				case CHANGED:
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
		createdEntitySets.forEach(es -> addEntitySetToDatabase(es));
		
		//Handles the changed entity sets.
		changedEntitySets.forEach(es -> changeEntitySetOnDatabase(es));
		
		//Handles the deleted entity sets.
		changedEntitySets.forEach(es -> deleteEntitySetFromDatabase(es));
		
		setDatabaseReady();
	}
	
	//method
	private void addEntitySetToDatabase(final EntitySet entitySet) {
		
		final var entitySetSpecification = DocumentNode.createFromString("EntitySet");
		
		entitySetSpecification.addAttribute(
			new DocumentNode(
				PascalCaseNameCatalogue.NAME,
				entitySet.getName()
			)
		);
		
		for (final var c : entitySet.getRefColumns()) {
			entitySetSpecification.addAttribute(c.getSpecification());
		}
		
		entitySetSpecification
		.addAttribute(DocumentNode.createFromString(MultiPascalCaseNameCatalogue.ENTITIES));
		
		documentNodeDatabase.addAttribute(entitySetSpecification);
	}

	//method
	private void changeEntitySetOnDatabase(final EntitySet entitySet) {
		//TODO: Implement.
	}
	
	//method
	private void deleteEntitySetFromDatabase(final EntitySet es) {
		documentNodeDatabase.removeFirstAttribute(
			a ->
				a.hasHeader("EntitySet")
				&& new EntitySetAdapter(a).hasName(es.getName())
		);
	}
	
	//method
	private DocumentNodeoid getDatabasePropertiesDocumentNode() {
		return documentNodeDatabase.getRefFirstAttribute(DATABASE_PROPERTIES_HEADER);
	}
	
	//method
	private void setDatabaseReady() {
		getDatabasePropertiesDocumentNode()
		.getRefFirstAttribute(PascalCaseNameCatalogue.STATE)
		.getRefOneAttribute()
		.setHeader(DatabaseState.Ready.toString());	
	}
}
