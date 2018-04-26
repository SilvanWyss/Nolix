//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class DatabaseSchemaConnectorWrapper<C> {

	//attributes
	private final IDatabaseSchemaConnector<C> databaseSchemaConnector;
	
	//multi-attribute
	private final List<C> commandsForChanges = new List<C>();
	
	//constructor
	public DatabaseSchemaConnectorWrapper(
		final IDatabaseSchemaConnector<C> databaseSchemaConnector
	) {
		Validator
		.suppose(databaseSchemaConnector)
		.thatIsNamed("database schema connector")
		.isNotNull();
				
		this.databaseSchemaConnector = databaseSchemaConnector;
	}
	
	//method
	public boolean hasChanges() {
		return commandsForChanges.containsAny();
	}
	
	//method
	public void noteAddColumn(final Column column) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector
			.getEntitySetConnector(column.getRefEntitySet())
			.createCommandForAdd(column)
		);
	}
	
	//method
	public void noteAddEntitySet(final EntityType<Entity> entityType) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector
			.createCommandForAdd(entityType)
		);
	}
	
	//method
	public void noteDeleteColumn(final Column column) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector
			.getEntitySetConnector(column.getRefEntitySet())
			.createCommandForDelete(column)
		);
	}
	
	//method
	public void noteDeleteEntitySet(final EntitySet entitySet) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector.createCommandForDelete(entitySet)
		);
	}
	
	//method
	public void noteInitialize() {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector.createCommandForInitialize()
		);
	}

	//method
	public void noteRenameColumn(
		final Column column,
		final String header
	) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector
			.getEntitySetConnector(column.getRefEntitySet())
			.createCommandForRename(header)
		);
	}
	
	//method
	public void noteRenameEntitySet(
		final EntitySet entitySet,
		final String name
	) {
		commandsForChanges.addAtEnd(
			databaseSchemaConnector
			.getEntitySetConnector(entitySet)
			.createCommandForRename(name)
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
