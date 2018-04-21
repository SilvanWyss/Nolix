//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class InternalDatabaseSchemaAdapter<C> {

	//attributes
	private final IDatabaseSchemaConnector<C> databaseSchemaConnector;
	
	//multi-attribute
	private final List<C> commandsForChanges = new List<C>();
	
	//constructor
	public InternalDatabaseSchemaAdapter(
		final IDatabaseSchemaConnector<C> databaseSchemaConnector
	) {
		Validator
		.suppose(databaseSchemaConnector)
		.thatIsNamed("database schema connector")
		.isNotNull();
				
		this.databaseSchemaConnector = databaseSchemaConnector;
	}
	
	//method
	public List<EntitySet> getEntitySets() {
		return databaseSchemaConnector.getRefEntitySets();
	}
	
	//method
	public boolean hasChanges() {
		return commandsForChanges.containsAny();
	}
	
	//method
	public void noteAddColumn(final Column column) {
		commandsForChanges.addAtEnd(databaseSchemaConnector.createCommandForAddColumn(column));
	}
	
	//method
	public void noteAddEntitySet(final EntitySet entitySet) {
		commandsForChanges.addAtEnd(databaseSchemaConnector.createCommandForAddEntitySet(entitySet));
	}
	
	//method
	public void noteDeleteColumn(final Column column) {
		commandsForChanges.addAtEnd(databaseSchemaConnector.createCommandForDeleteColumn(column));
	}
	
	//method
	public void noteDeleteEntitySet(final EntitySet entitySet) {
		commandsForChanges.addAtEnd(databaseSchemaConnector.createCommandForDeleteEntitySet(entitySet));
	}

	//method
	public void noteRenameColumn(
		final EntitySet entitySet,
		final String columnHeader,
		final String newColumnHeader
	) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector.createCommandForRenameColumn(
				entitySet,
				columnHeader,
				newColumnHeader
			)
		);
	}
	
	//method
	public void noteRenameEntitySet(
		final String entitySetName,
		final String newEntitySetName
	) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector.createCommandForRenameEntitySet(
				entitySetName,
				newEntitySetName
			)
		);
	}
	
	//method
	public void reset() {
		commandsForChanges.clear();
	}
	
	//method
	public void saveChanges() {
		databaseSchemaConnector.run(commandsForChanges);
		reset();
	}
}
