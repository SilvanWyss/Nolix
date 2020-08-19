//package declaration
package ch.nolix.system.databaseAdapter;

//interface
public interface IDatabaseAdapterCreator {
	
	//method declaration
	public abstract DatabaseAdapter createDatabaseAdapter(Schema schema);
}
