//package declaration
package ch.nolix.system.sqldatabasebasicschema.mssqllanguage;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IConstraintDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IDataTypeDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemalanguageapi.ISchemaStatementCreator;

//class
public final class MsSqlSchemaStatementCreator implements ISchemaStatementCreator {
	
	//method
	@Override
	public String createStatementToAddColumn(final String tabbleName, final IColumnDTO column) {
		return ("ALTER TABLE " + tabbleName + " ADD " + getColumnAsSql(column));
	}
	
	//method
	@Override
	public String createStatementToAddTable(ITableDTO table) {
		return 
		"CREATE TABLE "
		+ table.getName()
		+ " (" + table.getColumns().to(this::getColumnAsSql).toStringWithSeparator(",")
		+ ")";
	}
	
	//method
	@Override
	public String createStatementToDeleteColumn(final String tableName, final String columnName) {
		return ("ALTER TABLE " + tableName + " DROP COLUMN " + columnName);
	}
	
	//method
	@Override
	public String createStatementToDeleteTable(final String tableName) {
		return ("DROP TABLE " + tableName);
	}
	
	//method
	@Override
	public String createStatementToRenameColumn(
		final String tableName,
		final String columnName,
		final String newColumnName
	) {
		return ("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName);
	}
	
	//method
	@Override
	public String createStatementToRenameTable(final String tableName, final String newTableName) {
		return ("ALTER TABLE " + tableName + " RENAME TO " + newTableName);
	}
	
	//method
	private String getColumnAsSql(final IColumnDTO column) {
		
		var sql = column.getName() + " " + getDataTypeAsSql(column.getDataType());
		
		if (column.getConstraints().containsAny()) {
			sql += getConstraintsAsSql(column);
		}
		
		return sql;
	}
	
	//method
	private String getConstraintAsSql(final IConstraintDTO constraint) {
		
		var sql = constraint.getType().toString().replace(StringCatalogue.UNDERSCORE, StringCatalogue.SPACE);
		
		if (constraint.getParameters().containsAny()) {
			getConstraintParametersAsSql(constraint);
		}
		
		return sql;
	}
	
	//method
	private String getConstraintsAsSql(final IColumnDTO column) {
		return column.getConstraints().to(this::getConstraintAsSql).toStringWithSeparator(",");
	}
	
	//method
	private String getConstraintParametersAsSql(final IConstraintDTO constraint) {
		return ("(" + constraint.getParameters().toStringWithSeparator(",") + ")");
	}
	
	//method
	private String getDataTypeAsSql(final IDataTypeDTO dataType) {
		
		if (!dataType.hasParameter()) {
			return dataType.getName();
		}
		
		return (dataType.getName() + "(" + dataType.getParameter() + ")");
	}
}
