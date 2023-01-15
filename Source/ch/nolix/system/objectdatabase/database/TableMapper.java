//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class TableMapper {
	
	//static attribute
	private static final ColumnMapper columnMapper = new ColumnMapper();
	
	//method
	@SuppressWarnings("unchecked")
	public Table<IEntity<DataImplementation>> createEmptyTableFromTableDTOForDatabase(
		final ITableDTO tableDTO,
		final Database database
	) {
		return
		Table.withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
			database,
			tableDTO.getName(),
			tableDTO.getId(),
			(Class<IEntity<DataImplementation>>)database.internalGetSchema().getEntityTypeByName(tableDTO.getName())
		);
	}
	
	//method
	public ITable<DataImplementation, IEntity<DataImplementation>>
	createTableFromTableDTOForDatabaseUsingGivenReferencableTables(
		final ITableDTO tableDTO,
		final Database database,
		final IContainer<ITable<DataImplementation, IEntity<DataImplementation>>> referencableTables
	) {
		
		final var table = createEmptyTableFromTableDTOForDatabase(tableDTO, database);
		
		final var columns =
		tableDTO.getColumns()
		.to(
			c ->
			columnMapper.createColumnFromDTOForParentTableUsingGivenReferencableTables(c, table, referencableTables)
		);
		
		table.internalSetColumns(columns);
		
		return table;
	}
}
