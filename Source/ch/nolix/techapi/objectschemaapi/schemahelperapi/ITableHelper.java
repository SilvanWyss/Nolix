//package declaration
package ch.nolix.techapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ITableHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertContainsGivenColumn(ITable<?, ?, ?> table, IColumn<?, ?> column);
	
	//method declaration
	void assertDoesNotBelongToDatabase(ITable<?, ?, ?> table);
	
	//method declaration
	void assertDoesNotContainGivenColumn(ITable<?, ?, ?> table, IColumn<?, ?> column);
	
	//method declaration
	void assertDoesNotContainColumnWithGivenHeader(ITable<?, ?, ?> table, String header);
	
	//method declaration
	void assertIsNotReferenced(ITable<?, ?, ?> table);
	
	//method declaration
	boolean containsGivenColumn(ITable<?, ?, ?> table, IColumn<?, ?> column);
	
	//method declaration
	boolean containsColumnBackReferencedByGivenColumn(ITable<?, ?, ?> table, IColumn<?, ?> column);
	
	//method declaration
	boolean containsColumnThatReferencesBackGivenColumn(ITable<?, ?, ?> table, IColumn<?, ?> column) ;
	
	//method declaration
	boolean containsColumnThatReferencesGivenTable(ITable<?, ?, ?> table, ITable<?, ?, ?> probableReferencedTable);
	
	//method declaration
	boolean containsColumnWithGivenHeader(ITable<?, ?, ?> table, final String header);
	
	//method declaration
	int getColumnCount(ITable<?, ?, ?> table);
	
	//method declaration
	LinkedList<IColumn<?, ?>> getRefBackReferencingColumns(ITable<?, ?, ?> table);
	
	//method declaration
	IColumn<?, ?> getRefColumnWithGivenHeader(ITable<?, ?, ?> table, String header);
	
	//method declaration
	LinkedList<IColumn<?, ?>> getRefReferencingColumns(ITable<?, ?, ?> table);
	
	//method declaration
	boolean isReferenced(ITable<?, ?, ?> table);
}
