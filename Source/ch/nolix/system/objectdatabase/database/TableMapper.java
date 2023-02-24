//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class TableMapper {
	
	//static attribute
	private static final ColumnMapper columnMapper = new ColumnMapper();
	
	//method
	@SuppressWarnings("unchecked")
	public Table<IEntity> createEmptyTableFromTableDTOForDatabase(
		final ITableDTO tableDTO,
		final Database database
	) {
		return
		Table.withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
			database,
			tableDTO.getName(),
			tableDTO.getId(),
			(Class<IEntity>)database.internalGetSchema().getEntityTypeByName(tableDTO.getName())
		);
	}
	
	//method
	public ITable<IEntity>
	createTableFromTableDTOForDatabaseUsingGivenReferencableTables(
		final ITableDTO tableDTO,
		final Database database,
		final IContainer<ITable<IEntity>> referencableTables
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
