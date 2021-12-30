//package declaration
package ch.nolix.techapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IColumnHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertBelongsToTable(IColumn<?> column);
	
	//method declaration
	void assertDoesNotBelongToTable(IColumn<?> column);
	
	//method declaration
	void assertIsABackReferenceColumn(IColumn<?> column);
	
	//method declaration
	void assertIsAReferenceColumn(IColumn<?> column);
	
	//method declaration
	boolean belongsToDatabase(IColumn<?> column);
	
	//method declaration
	BasePropertyType getBasePropertyType(IColumn<?> column);
	
	//method declaration
	<IMPL> IDatabase<IMPL> getParentDatabase(IColumn<IMPL> column);
	
	//method declaration
	PropertyType getPropertyType(IColumn<?> column);
	
	//method declaration
	boolean isABackReferenceColumn(IColumn<?> column);
	
	//method declaration
	boolean isAReferenceColumn(IColumn<?> column);
	
	//method declaration
	boolean isAValidBackReferenceColumn(IColumn<?> column);
	
	//method declaration
	boolean isAValueColumn(IColumn<?> column);
	
	//method declaration
	boolean referencesBackGivenColumn(IColumn<?> column, IColumn<?> probableBackReferencedColumn);
	
	//method declaration
	boolean referencesGivenTable(IColumn<?> column, ITable<?> table);
}
