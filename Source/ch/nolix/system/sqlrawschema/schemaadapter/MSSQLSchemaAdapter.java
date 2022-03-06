//package declaration
package ch.nolix.system.sqlrawschema.schemaadapter;

//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.DataTypeDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;

//class
public final class MSSQLSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MSSQLSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool
	) {
		return new MSSQLSchemaAdapter(databaseName, pSQLConnectionPool);
	}
	
	//constant
	private static final IColumnDTO SAVE_STAMP_COLUMN_DTO =
	new ColumnDTO(PascalCaseCatalogue.SAVE_STAMP, new DataTypeDTO("BigInt"));
	
	//constructor
	private MSSQLSchemaAdapter(final String databaseName, final SQLConnectionPool pSQLConnectionPool) {
		super(
			databaseName,
			pSQLConnectionPool,
			ch.nolix.system.sqlbasicschema.schemaadapter.MSSQLSchemaAdapter
			.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
				databaseName,
				pSQLConnectionPool
			),
			SAVE_STAMP_COLUMN_DTO
		);
	}
}
