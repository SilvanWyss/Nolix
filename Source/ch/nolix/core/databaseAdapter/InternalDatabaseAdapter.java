//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class InternalDatabaseAdapter<C> {

	//attribute
	private final IDatabaseConnector<C> databaseConnector;
	
	//multi-attributes
	private final List<C> commandsForAdding = new List<C>();
	private final List<C> commandsForDeletion = new List<C>();
	
	//constructor
	public InternalDatabaseAdapter(final IDatabaseConnector<C> databaseConnector) {
		
		Validator
		.suppose(databaseConnector)
		.thatIsNamed("database connector")
		.isNotNull();
		
		this.databaseConnector = databaseConnector;
	}
	
	//method
	public <E extends Entity> List<E> getRefEntities(final EntitySet<E> entitySet) {
		return databaseConnector.getRefEntities(entitySet);
	}
	
	//method
	public <E extends Entity> E getRefEntity(final EntitySet<E> entitySet, final int id) {
		return databaseConnector.getRefEntity(entitySet, id);
	}
	
	//method
	public <E extends Entity> void noteAddEntity(final EntitySet<E> entitySet, final E entity) {
		commandsForAdding.addAtEnd(databaseConnector.createCommandForAddEntity(entitySet, entity));
	}
	
	//method
	public <E extends Entity> void noteDeleteEntity(final EntitySet<E> entitySet, final E entity) {
		commandsForAdding.addAtEnd(databaseConnector.createCommandForDeleteEntity(entitySet, entity));
	}
	
	//method
	public <E extends Entity> void noteEditEntity(final EntitySet<E> entitySet, final E entity) {
		commandsForAdding.addAtEnd(databaseConnector.createCommandForEditEntity(entitySet, entity));
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
		commands.addAtEnd(commandsForAdding);
		commands.addAtEnd(createCommandsForEditEntities(entitySets));
		commands.addAtEnd(commandsForDeletion);
		
		databaseConnector.run(commands);
		reset();
	}
	
	//method
	private IContainer<C> createCommandsForEditEntities(final IContainer<EntitySet<Entity>> entitySets) {
		return
		entitySets.toFromMany(
			es -> es.getRefEditedEntities().to(e -> databaseConnector.createCommandForEditEntity(es, e))
		);
	}
}
