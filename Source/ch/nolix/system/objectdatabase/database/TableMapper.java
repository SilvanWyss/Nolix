//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
final class TableMapper {
	
	//static attribute
	private static final ColumnMapper columnMapper = new ColumnMapper();
	
	//method
	@SuppressWarnings("unchecked")
	public Table<IEntity> createEmptyTableFromTableDtoForDatabase(
		final ITableDto tableDto,
		final Database database
	) {
		return
		Table.withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
			database,
			tableDto.getName(),
			tableDto.getId(),
			(Class<IEntity>)database.internalGetSchema().getEntityTypeByName(tableDto.getName())
		);
	}
	
	//method
	public ITable<IEntity>
	createTableFromTableDtoForDatabaseUsingGivenReferencableTables(
		final ITableDto tableDto,
		final Database database,
		final IContainer<ITable<IEntity>> referencableTables
	) {
		
		final var table = createEmptyTableFromTableDtoForDatabase(tableDto, database);
		
		final var columns =
		tableDto.getColumns()
		.to(
			c ->
			columnMapper.createColumnFromDtoForParentTableUsingGivenReferencableTables(c, table, referencableTables)
		);
		
		table.internalSetColumns(columns);
		
		return table;
	}
}
