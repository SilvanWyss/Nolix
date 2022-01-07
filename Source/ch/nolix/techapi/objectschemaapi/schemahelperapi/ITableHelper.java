//package declaration
package ch.nolix.techapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

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
	<IMPL> LinkedList<IColumn<IMPL>> getRefBackReferencingColumns(ITable<IMPL> table);
	
	//method declaration
	<IMPL> IColumn<IMPL> getRefColumnWithGivenHeader(ITable<IMPL> table, String header);
	
	//method declaration
	<IMPL> LinkedList<IColumn<IMPL>> getRefReferencingColumns(ITable<IMPL> table);
	
	//method declaration
	boolean isReferenced(ITable<?> table);
}
