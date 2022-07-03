//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdata.datahelper.SchemaHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ISchemaHelper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class TableMapper {
	
	//static attribute
	private static final ISchemaHelper schemaHelper = new SchemaHelper();
	
	//static attribute
	private static final ColumnMapper columnMapper = new ColumnMapper();
	
	//method
	public ITable<DataImplementation, IEntity<DataImplementation>>
	createTableFromTableDTOForDatabaseUsingGivenReferencableTables(
		final ITableDTO tableDTO,
		final Database database,
		final IContainer<ITable<DataImplementation, IEntity<DataImplementation>>> referencableTables
	) {
		
		final var table =
		Table.withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
			database,
			tableDTO.getName(),
			tableDTO.getId(),
			schemaHelper.getEntityTypeByName(database.internalGetSchema(), tableDTO.getName())
		);
		
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
