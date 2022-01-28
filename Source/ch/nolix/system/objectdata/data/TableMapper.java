//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectdata.datahelper.SchemaHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ISchemaHelper;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
final class TableMapper {
	
	//static attribute
	private static final ISchemaHelper schemaHelper = new SchemaHelper();
	
	//method
	public ITable<DataImplementation, IEntity<DataImplementation>> createTableFromTableDTOForDatabase(
		final ITableDTO tableDTO,
		final Database database
	) {
		
		final var name = getNameFrom(tableDTO);
		
		return
		new Table<>(getNameFrom(tableDTO), schemaHelper.getEntityTypeByName(database.internalGetSchema(), name), database);
	}
	
	//method
	private String getNameFrom(final ITableDTO tableDTO) {
		return tableDTO.getName();
	}
}
