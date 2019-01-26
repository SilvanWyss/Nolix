//package declaration
package ch.nolix.core.documentNodeDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.databaseSchemaAdapter.EntitySet;
import ch.nolix.core.databaseSchemaAdapter.IEntitySetAdapter;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
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
		.isNotNull();
		
		this.documentNodeDatabase = documentNodeDatabase;
		
		reset();
	}
	
	//method
	@Override
	public boolean isInitialized() {
		return documentNodeDatabase.hasHeader(PascalCaseNameCatalogue.DATABASE);
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
	}
	
	//method
	@Override
	protected void saveChangesToDatabase(final IContainer<EntitySet> entitySets) {
		
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
	}
	
	//method
	private void addEntitySetToDatabase(final EntitySet entitySet) {
		
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
}
