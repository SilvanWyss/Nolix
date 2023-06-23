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
		return ("ALTER TABLE " + tabbleName + " ADD " + getColumnAsSQL(column));
	}
	
	//method
	@Override
	public String createStatementToAddTable(ITableDTO table) {
		return 
		"CREATE TABLE "
		+ table.getName()
		+ " (" + table.getColumns().to(this::getColumnAsSQL).toStringWithSeparator(",")
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
	private String getColumnAsSQL(final IColumnDTO column) {
		
		var lSQL = column.getName() + " " + getDataTypeAsSQL(column.getDataType());
		
		if (column.getConstraints().containsAny()) {
			lSQL += getConstraintsAsSQL(column);
		}
		
		return lSQL;
	}
	
	//method
	private String getConstraintAsSQL(final IConstraintDTO constraint) {
		
		var lSQL = constraint.getType().toString().replace(StringCatalogue.UNDERSCORE, StringCatalogue.SPACE);
		
		if (constraint.getParameters().containsAny()) {
			getConstraintParametersAsSQL(constraint);
		}
		
		return lSQL;
	}
	
	//method
	private String getConstraintsAsSQL(final IColumnDTO column) {
		return column.getConstraints().to(this::getConstraintAsSQL).toStringWithSeparator(",");
	}
	
	//method
	private String getConstraintParametersAsSQL(final IConstraintDTO constraint) {
		return ("(" + constraint.getParameters().toStringWithSeparator(",") + ")");
	}
	
	//method
	private String getDataTypeAsSQL(final IDataTypeDTO dataType) {
		
		if (!dataType.hasParameter()) {
			return dataType.getName();
		}
		
		return (dataType.getName() + "(" + dataType.getParameter() + ")");
	}
}
