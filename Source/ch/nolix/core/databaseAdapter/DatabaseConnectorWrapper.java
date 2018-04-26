//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class DatabaseConnectorWrapper<C> {

	//attribute
	private final IDatabaseConnector<C> databaseConnector;
	
	//multi-attributes
	private final List<C> commandsForAdding = new List<C>();
	private final List<C> commandsForDeletion = new List<C>();
	
	//constructor
	public DatabaseConnectorWrapper(final IDatabaseConnector<C> databaseConnector) {
		
		Validator
		.suppose(databaseConnector)
		.thatIsNamed("database connector")
		.isNotNull();
		
		this.databaseConnector = databaseConnector;
	}
	
	//method
	public <E extends Entity> List<E> getEntities(final EntitySet<E> entitySet) {
		return
		databaseConnector
		.getEntitySetConnector(entitySet)
		.getEntities(entitySet.getEntityType());
	}
	
	//method
	public <E extends Entity> E getEntity(final int id, final EntitySet<E> entitySet) {
		return
		databaseConnector
		.getEntitySetConnector(entitySet)
		.getEntity(id, entitySet.getEntityType());
	}
	
	//method
	public <E extends Entity> void noteAddEntity(final EntitySet<E> entitySet, final E entity) {
		commandsForAdding
		.addAtEnd(
			getDatabaseConnector()
			.getEntitySetConnector(entitySet)
			.createCommandForAdd(entity)
		);
	}
	
	//method
	public <E extends Entity> void noteDeleteEntity(final EntitySet<E> entitySet, final E entity) {
		commandsForAdding
		.addAtEnd(
			getDatabaseConnector()
			.getEntitySetConnector(entitySet)
			.createCommandForDelete(entity)
		);
	}
	
	//method
	public <E extends Entity> void noteEditEntity(final EntitySet<E> entitySet, final E entity) {
		commandsForAdding
		.addAtEnd(
			getDatabaseConnector()
			.getEntitySetConnector(entitySet)
			.createCommandForAdd(entity)
		);
	}
	
	//method
	public boolean hasChanges() {
		return (commandsForAdding.containsAny() || commandsForDeletion.containsAny());
	}
	
	//method
	public void reset() {
		commandsForAdding.clear();
		commandsForDeletion.clear();
	}
	
	//method
	public void saveChanges(final IContainer<EntitySet<Entity>> entitySets) {
		
		final var commands = new List<C>();		
		commands.addAtEnd(commandsForAdding); //can not be referenced
		commands.addAtEnd(createCommandsForUpdateEntities(entitySets)); 
		commands.addAtEnd(commandsForDeletion);		
		databaseConnector.run(commands);
		
		reset();
	}
	
	//method
	private IContainer<C> createCommandsForUpdateEntities(final IContainer<EntitySet<Entity>> entitySets) {
		return
		entitySets.toFromMany(
			es -> es.getRefUpdatedEntities().to(
				e ->
				getDatabaseConnector()
				.getEntitySetConnector(es)
				.createCommandForUpdate(e)
			)
		);
	}
	
	//method
	private IDatabaseConnector<C> getDatabaseConnector() {
		return databaseConnector;
	}
}
