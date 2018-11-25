//package declaration
package ch.nolix.core.documentNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.databaseSchemaAdapter.EntitySet;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.validator2.Validator;

//class
public final class DocumentNodeDatabaseSchemaAdapter
extends DatabaseSchemaAdapter<DocumentNodeDatabaseSchemaAdapter> {
	
	//attribute
	private final DocumentNodeoid documentNodeDatabase;
	
	//constructor
	public DocumentNodeDatabaseSchemaAdapter(final DocumentNodeoid documentNodeDatabase) {
		
		Validator
		.suppose(documentNodeDatabase)
		.thatIsNamed("document node database")
		.isInstance();
		
		this.documentNodeDatabase = documentNodeDatabase;
	}
	
	//method
	public void addEntitySet(final EntitySet entitySet) {
		
		if (databaseContainsEntitySet(entitySet.getName())) {
			throw
			new InvalidStateException(
				this,
				"contains already an entity set with the name " + entitySet.getNameInQuotes()
			);
		}
		
		final var entitySetSpecification = new DocumentNode("EntitySet");
		
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
		.addAttribute(new DocumentNode(MultiPascalCaseNameCatalogue.ENTITIES));
		
		documentNodeDatabase.addAttribute(entitySetSpecification);
	}
	
	//method
	public boolean databaseContainsEntitySet(final String name) {
		return 
		documentNodeDatabase.containsAttribute(
			a ->
				a.hasHeader("EntitySet")
				&& new EntitySetAdapter(a).hasName(name)
		);
	}
	
	//method
	public void deleteEntitySetOnDatabase(final EntitySet es) {
		documentNodeDatabase.removeFirstAttribute(
			a ->
				a.hasHeader("EntitySet")
				&& new EntitySetAdapter(a).hasName(es.getName())
		);
	}
	
	//method
	public EntitySetAdapter getEntitySetAdapter(final EntitySet entitySet) {
		return getEntitySetAdapter(entitySet.getName());
	}
	
	//method
	public EntitySetAdapter getEntitySetAdapter(final String name) {
		return getEntitySetAdapters().getRefFirst(esc -> esc.hasName(name));
	}
	
	//method
	public List<EntitySetAdapter> getEntitySetAdapters() {
		return
		documentNodeDatabase
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.to(a -> new EntitySetAdapter(a));
	}
	
	//method
	public DocumentNodeDatabaseSchemaAdapter initialize() {
		
		documentNodeDatabase.setHeader(PascalCaseNameCatalogue.DATABASE);
		
		return this;
	}
	
	//method
	public boolean isInitialized() {
		return documentNodeDatabase.hasHeader("Database");
	}

	//method
	public void saveChanges(final IContainer<EntitySet> changedEntitySetsInOrder) {
		
		//Handles the created entity sets.
			final var createdEntitySets =
			changedEntitySetsInOrder.getRefSelected(es -> es.isCreated());
			
			for (final var es : createdEntitySets) {
				addEntitySet(es);
			}
		
		//Handles the changed entity sets.
			final var changedEntitySets =
			changedEntitySetsInOrder.getRefSelected(e -> e.isChanged());
			
			for (final var es : changedEntitySets) {
				changeEntitySet(es);
			}
		
		//Handles the deleted entity sets.
			final var deletedEntitySets =
			changedEntitySetsInOrder.getRefSelected(es -> es.isDeleted());
			
			for (final var es : deletedEntitySets) {
				deleteEntitySet(es);
			}
	}
	
	//method
	public void saveChangesToDatabase(final Iterable<EntitySet> changedEntitySetsInOrder) {
		saveChanges(new ReadContainer<EntitySet>(changedEntitySetsInOrder));
	}

	//method
	public void changeEntitySet(final EntitySet entitySet) {
		//TODO: Implement.
	}
}
