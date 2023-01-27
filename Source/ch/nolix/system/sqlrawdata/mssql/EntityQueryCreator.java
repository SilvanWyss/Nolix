//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IEntityQueryCreator;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class EntityQueryCreator implements IEntityQueryCreator {
	
	//method
	@Override
	public String createQueryToCountEntitiesWithGivenId(final String tableName, final String id) {
		return
		"SELECT COUNT(Id) FROM "
		+ TableType.ENTITY_TABLE.getNamePrefix()
		+ tableName
		+ " WHERE Id = '"
		+ id
		+ "';";
	}
	
	//method
	@Override
	public String createQueryToCountEntitiesWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return "SELECT COUNT(" + columnName + ") FROM " + tableName + " WHERE "+ columnName + " = '" + value + "'";
	}
	
	//method
	@Override
	public String createQueryToLoadEntitiesOfTable(final ITableInfo tableInfo) {
		return
		"SELECT Id, SaveStamp, "
		+ tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toString(", ")
		+ " FROM "
		+ tableInfo.getTableName();
	}
	
	//method
	@Override
	public String createQueryToLoadEntity(String id, ITableInfo tableInfo) {
		return
		"SELECT Id, SaveStamp, "
		+ tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toString(", ")
		+ " FROM "
		+ TableType.ENTITY_TABLE.getNamePrefix() + tableInfo.getTableName()
		+ " WHERE Id = '"
		+ id
		+ "'";
	}
	
	//method
	@Override
	public String createQueryToLoadSchemaTimestamp() {
		return
		"SELECT "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " FROM "
		+ SystemDataTable.DATABASE_PROPERTY.getFullName()
		+ " WHERE "
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ " = '"
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
		+ "'";
	}
}
