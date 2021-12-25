//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
final class TableMapper {
	
	//method
	public Table<IEntity<DataImplementation>> createTableFromTableDTOForDatabase(
		final ITableDTO tableDTO,
		final Database database
	) {
		
		//TODO: Complete implementation.
		return new Table<>(getNameFrom(tableDTO), null, database);
	}
	
	//method
	private String getNameFrom(final ITableDTO tableDTO) {
		return tableDTO.getName();
	}
}
