//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.DataTypeDTO;
import ch.nolix.techapi.sqlschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemalanguageapi.ISchemaQueryCreator;

//class
public abstract class SchemaReader implements ISchemaReader {
	
	//attributes
	private final SQLConnection mSQLConnection;
	private final ISchemaQueryCreator schemaQueryCreator;
	
	//constructor
	public SchemaReader(final SQLConnection pSQLConnection, final ISchemaQueryCreator schemaQueryCreator) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(schemaQueryCreator).thatIsNamed(ISchemaQueryCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaQueryCreator = schemaQueryCreator;
	}
	
	//method
	@Override
	public final LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return
		mSQLConnection
		.getRecords(schemaQueryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName))
		.to(r -> new ColumnDTO(r.get(0), new DataTypeDTO(r.get(1))));
	}
	
	//method
	@Override
	public final LinkedList<IFlatTableDTO> loadFlatTables() {
		return
		mSQLConnection
		.getRecordsAsStrings(schemaQueryCreator.createQueryToLoadNameOfTables())
		.to(FlatTableDTO::new);
	}
}
