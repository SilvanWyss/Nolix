//package declaration
package ch.nolix.system.sqlrawobjectschema.schemaadapter;

import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.DataTypeDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//constant
	private static final IColumnDTO SAVE_STAMP_COLUMN_DTO = new ColumnDTO("RowVersion",	new DataTypeDTO("rowversion"));
	
	//constructor
	public MSSQLSchemaAdapter(final SQLConnection pSQLConnection) {
		super(
			pSQLConnection,
			new ch.nolix.system.sqlschema.schemaadapter.MSSQLSchemaAdapter(pSQLConnection),
			SAVE_STAMP_COLUMN_DTO
		);
	}
}
