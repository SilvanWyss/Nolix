//package declaration
package ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.core.skillapi.IMultiTimeChangeSaver;
import ch.nolix.element.time.base.Time;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaWriter extends GroupCloseable, IMultiTimeChangeSaver {
	
	//method declaration
	void addColumn(String tableName, IColumnDTO column);
	
	//method declaration
	void addTable(ITableDTO table);
	
	//method declaration
	void deleteColumn(String tableName, String columnName);
	
	//method declaration
	void deleteTable(String tableName);
	
	//method declaration
	void setColumnName(String tableName, String columnName, String newColumnName);
	
	//method declaration
	void setColumnParametrizedPropertyType(
		String columnId,
		IParametrizedPropertyTypeDTO parametrizedPropertyType
	);
	
	//method declaration
	void setSchemaTimestamp(Time schemaTimestamp);
	
	//method declaration
	void setTableName(String tableName, String newTableName);
}
