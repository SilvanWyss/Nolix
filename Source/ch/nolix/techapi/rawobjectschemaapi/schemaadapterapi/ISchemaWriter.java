//package declaration
package ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaWriter extends IChangeSaver {
	
	//method declaration
	void addColumn(String tableName, IColumnDTO column);
	
	//method declaration
	void addTable(ITableDTO table);
	
	//method declaration
	void deleteColumn(String tableName, String columnHeader);
	
	//method declaration
	void deleteTable(String tableName);
	
	//method declaration
	void setColumnHeader(String tableName, String columnHeader, String newColumnHeader);
	
	//method declaration
	void setColumnParametrizedPropertyType(
		String tableName,
		String columnHeader,
		IParametrizedPropertyTypeDTO parametrizedPropertyType
	);
	
	//method declaration
	void setSchemaTimestamp(Time schemaTimestamp);
	
	//method declaration
	void setTableName(String tableName, String newTableName);
}
