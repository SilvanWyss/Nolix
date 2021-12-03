//package declaration
package ch.nolix.techapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IDatabaseHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertContainsGivenTable(IDatabase<?> database, ITable<?> table);
	
	//method declaration
	void assertContainsTableReferencedByGivenColumn(IDatabase<?> database, IColumn<?> column);
	
	//method declaration
	void assertContainsTableWithColumnBackReferencedByGivenColumn(IDatabase<?> database, IColumn<?> column);
	
	//method declaration
	void assertContainsTableWithGivenColumn(IDatabase<?> database, IColumn<?> column);
	
	//method declaration
	void assertDoesNotContainTableWithGivenName(IDatabase<?> database, String name);
	
	//method declaration
	boolean containsGivenTable(IDatabase<?> database, ITable<?> table);
	
	//method declaration
	boolean containsTableReferencedByGivenColumn(IDatabase<?> database, IColumn<?> column);
	
	//method declaration
	boolean containsTableWithColumnBackReferencedByGivenColumn(IDatabase<?> database, IColumn<?> column);
	
	//method declaration
	boolean containsTableWithGivenColumn(IDatabase<?> database, IColumn<?> column);
	
	//method declaration
	boolean containsTableWithGivenName(IDatabase<?> database, String name);
	
	//method declaration
	void deleteTableWithGivenName(IDatabase<?> database, String name);
	
	//method declaration
	<IMPL> ITable<IMPL> getRefTableWithGivenName(IDatabase<IMPL> database, String name);
	
	//method declaration
	int getTableCount(IDatabase<?> database);
}
