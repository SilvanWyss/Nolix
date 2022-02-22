//package declaration
package ch.nolix.system.sqlrawobjectschema.schemaadapter;

//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.DataTypeDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//constant
	private static final IColumnDTO SAVE_STAMP_COLUMN_DTO =
	new ColumnDTO(PascalCaseCatalogue.SAVE_STAMP, new DataTypeDTO("BigInt"));
	
	//constructor
	public MSSQLSchemaAdapter(final String databaseName, final SQLConnection pSQLConnection) {
		super(
			databaseName,
			pSQLConnection,
			new ch.nolix.system.sqlschema.schemaadapter.MSSQLSchemaAdapter(pSQLConnection),
			SAVE_STAMP_COLUMN_DTO
		);
	}
}
