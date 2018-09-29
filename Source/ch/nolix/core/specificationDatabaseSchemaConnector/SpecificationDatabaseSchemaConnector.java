//package declaration
package ch.nolix.core.specificationDatabaseSchemaConnector;

//own imports
import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.databaseSchemaAdapter.EntitySet;
import ch.nolix.core.databaseSchemaAdapter.IDatabaseSchemaConnector;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.validator2.Validator;

//class
public final class SpecificationDatabaseSchemaConnector
implements IDatabaseSchemaConnector {
	
	//attribute
	private final DocumentNodeoid databaseSpecification;
	
	//constructor
	public SpecificationDatabaseSchemaConnector(final DocumentNodeoid databaseSpecification) {
		
		Validator
		.suppose(databaseSpecification)
		.thatIsNamed("database specification")
		.isInstance();
		
		this.databaseSpecification = databaseSpecification;
	}
	
	//method
	public void addEntitySet(final EntitySet entitySet) {
		
		if (containsEntitySet(entitySet.getName())) {
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
		
		databaseSpecification.addAttribute(entitySetSpecification);
	}
	
	//method
	public boolean containsEntitySet() {
		return databaseSpecification.containsAttribute(a -> a.hasHeader("EntitySet"));
	}
	
	//method
	public boolean containsEntitySet(final String name) {
		return 
		databaseSpecification.containsAttribute(
			a ->
				a.hasHeader("EntitySet")
				&& new EntitySetConnector(a).hasName(name)
		);
	}
	
	//method
	public void deleteEntitySet(final EntitySet es) {
		databaseSpecification.removeFirstAttribute(
			a ->
				a.hasHeader("EntitySet")
				&& new EntitySetConnector(a).hasName(es.getName())
		);
	}
	
	//method
	public EntitySetConnector getEntitySetConnector(final EntitySet entitySet) {
		return getEntitySetConnector(entitySet.getName());
	}
	
	//method
	public EntitySetConnector getEntitySetConnector(final String name) {
		return getEntitySetConnectors().getRefFirst(esc -> esc.hasName(name));
	}
	
	//method
	public List<EntitySetConnector> getEntitySetConnectors() {
		return
		databaseSpecification
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.to(a -> new EntitySetConnector(a));
	}
	
	//method
	public void initialize() {
		databaseSpecification.setHeader(PascalCaseNameCatalogue.DATABASE);
	}
	
	//method
	public boolean isInitialized() {
		return databaseSpecification.hasHeader("Database");
	}

	//method
	public void saveChanges(final IContainer<EntitySet> changedEntitySetsInOrder) {
		
		//Handles the created entity sets.
			final var createdEntitySets =
			changedEntitySetsInOrder.getRefSelected(es -> es.isCreated());
			
			for (final var es : createdEntitySets) {
				addEntitySet(es);
			}
		
		//Handles the updated entity sets.
			final var updatedEntitySets =
			changedEntitySetsInOrder.getRefSelected(e -> e.isUpdated());
			
			for (final var es : updatedEntitySets) {
				updatedEntitySet(es);
			}
		
		//Handles the deleted entity sets.
			final var deletedEntitySets =
			changedEntitySetsInOrder.getRefSelected(es -> es.isDeleted());
			
			for (final var es : deletedEntitySets) {
				deleteEntitySet(es);
			}
	}
	
	//method
	public void saveChanges(final Iterable<EntitySet> changedEntitySetsInOrder) {
		saveChanges(new ReadContainer<EntitySet>(changedEntitySetsInOrder));
	}

	//method
	public void updatedEntitySet(final EntitySet entitySet) {
		//TODO: Implement.
	}
}
