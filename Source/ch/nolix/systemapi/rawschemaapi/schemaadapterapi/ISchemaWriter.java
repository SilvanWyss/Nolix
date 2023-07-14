//package declaration
package ch.nolix.systemapi.rawschemaapi.schemaadapterapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//interface
public interface ISchemaWriter extends IMultiTimeChangeSaver {
	
	//method declaration
	void addColumn(String tableName, IColumnDto column);
	
	//method declaration
	void addTable(ITableDto table);
	
	//method declaration
	void deleteColumn(String tableName, String columnName);
	
	//method declaration
	void deleteTable(String tableName);
	
	//method declaration
	void setColumnName(String tableName, String columnName, String newColumnName);
	
	//method declaration
	void setColumnParametrizedPropertyType(
		String columnId,
		IParametrizedPropertyTypeDto parametrizedPropertyType
	);
	
	//method declaration
	void setTableName(String tableName, String newTableName);
}
