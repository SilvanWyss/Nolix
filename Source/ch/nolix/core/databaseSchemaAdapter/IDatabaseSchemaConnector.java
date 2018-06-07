//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//interface
public interface IDatabaseSchemaConnector {
	
	//abstract method
	public abstract boolean containsEntitySet();
	
	//abstract method
	public IEntitySetConnector getEntitySetConnector(EntitySet entitySet);
	
	//abstract method
	public abstract void initialize();
	
	//abstract method
	public abstract boolean isInitialized();
	
	//abstract method
	public abstract void saveChanges(Iterable<EntitySet> changedEntitySetsInOrder);
}
