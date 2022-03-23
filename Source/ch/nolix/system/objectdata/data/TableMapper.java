//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectdata.datahelper.SchemaHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ISchemaHelper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class TableMapper {
	
	//static attribute
	private static final ISchemaHelper schemaHelper = new SchemaHelper();
	
	//method
	public ITable<DataImplementation, IEntity<DataImplementation>> createTableFromTableDTOForDatabase(
		final ITableDTO tableDTO,
		final Database database
	) {
		return 
		Table.withParentDatabaseAndNameAndIdAndEntityClass(
			database,
			tableDTO.getName(),
			tableDTO.getId(),
			schemaHelper.getEntityTypeByName(database.internalGetSchema(), tableDTO.getName())
		);
	}
}
