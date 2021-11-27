//package declaration
package ch.nolix.techapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;

//interface
public interface IColumnHelper extends IDatabaseObjectHelper {
	
	//method declaration
	void assertBelongsToTable(IColumn<?, ?> column);
	
	//method declaration
	void assertDoesNotBelongToTable(IColumn<?, ?> column);
	
	//method declaration
	void assertIsABackReferenceColumn(IColumn<?, ?> column);
	
	//method declaration
	void assertIsAReferenceColumn(IColumn<?, ?> column);
	
	//method declaration
	boolean belongsToDatabase(IColumn<?, ?> column);
	
	//method declaration
	boolean isABackReferenceColumn(IColumn<?, ?> column);
	
	//method declaration
	boolean isAReferenceColumn(IColumn<?, ?> column);
	
	//method declaration
	boolean isAValueColumn(IColumn<?, ?> column);
	
	//method declaration
	boolean referencesBackGivenColumn(IColumn<?, ?> column, IColumn<?, ?> probableBackReferencedColumn);
	
	//method declaration
	boolean referencesGivenColumn(IColumn<?, ?> column, IColumn<?, ?> probableReferencedColumn);
}
