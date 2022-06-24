//package declaration
package ch.nolix.systemapi.objectschemaapi.schemahelperapi;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ITableHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertContainsGivenColumn(ITable<?> table, IColumn<?> column);
	
	//method declaration
	void assertDoesNotBelongToDatabase(ITable<?> table);
	
	//method declaration
	void assertDoesNotContainGivenColumn(ITable<?> table, IColumn<?> column);
	
	//method declaration
	void assertDoesNotContainColumnWithGivenName(ITable<?> table, String name);
	
	//method declaration
	void assertIsNotReferenced(ITable<?> table);
	
	//method declaration
	boolean containsGivenColumn(ITable<?> table, IColumn<?> column);
	
	//method declaration
	boolean containsColumnBackReferencedByGivenColumn(ITable<?> table, IColumn<?> column);
	
	//method declaration
	boolean containsColumnThatReferencesBackGivenColumn(ITable<?> table, IColumn<?> column) ;
	
	//method declaration
	boolean containsColumnThatReferencesGivenTable(ITable<?> table, ITable<?> probableReferencedTable);
	
	//method declaration
	boolean containsColumnWithGivenName(ITable<?> table, final String name);
	
	//method declaration
	int getColumnCount(ITable<?> table);
	
	//method declaration
	<IMPL> IContainer<IColumn<IMPL>> getRefBackReferenceColumns(ITable<IMPL> table);
	
	//method declaration
	<IMPL> IContainer<IColumn<IMPL>> getRefBackReferencingColumns(ITable<IMPL> table);
	
	//method declaration
	<IMPL> IContainer<IColumn<IMPL>> getRefReferencingColumns(ITable<IMPL> table);
	
	//method declaration
	boolean isReferenced(ITable<?> table);
}
