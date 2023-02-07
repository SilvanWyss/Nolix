//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class DatabaseTableLoader {
	
	//static attribute
	private static final TableMapper tableMapper = new TableMapper();
	
	//static attribute
	private static final ColumnMapper columnMapper = new ColumnMapper();
	
	//method
	public LinkedList<Table<IEntity<DataImplementation>>> loadTablesForDatabase(final Database database) {
		
		final var rawTables = database.internalGetRefDataAndSchemaAdapter().loadTables();
		
		final var tables = rawTables.to(rt -> tableMapper.createEmptyTableFromTableDTOForDatabase(rt, database));
		
		addBaseValueColumnsToTablesFromRawTables(tables, rawTables);
		addBaseReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);
		addBaseBackReferenceColumnsToTablesFromRawTables(tables, rawTables, tables);
		
		return LinkedList.fromIterable(tables);
	}
	
	//method
	private void addBaseValueColumnsToTablesFromRawTables(
		final IContainer<Table<IEntity<DataImplementation>>> tables,
		final IContainer<ITableDTO> rawTables
	) {
		for (final var t : tables) {
			final var tableName = t.getName();
			final var rawTable = rawTables.getRefFirst(rt -> rt.getName().equals(tableName));
			addBaseValueColumnsToTableFromRawTable(t, rawTable);
		}
	}
	
	//method
	private void addBaseValueColumnsToTableFromRawTable(
		final Table<IEntity<DataImplementation>> table,
		final ITableDTO rawTable
	) {
		
		final var rawBaseValueColumns =
		rawTable
		.getColumns()
		.getRefSelected(
			c -> c.getParametrizedPropertyType().getPropertyType().getBaseType() == BasePropertyType.BASE_VALUE
		);
		
		for (final var c : rawBaseValueColumns) {
			
			final var column =
			columnMapper.createColumnFromDTOForParentTableUsingGivenReferencableTables(
				c,
				table,
				new ImmutableList<>()
			);
			
			table.internalAddColumn(column);
		}
	}
	
	//method
	private void addBaseReferenceColumnsToTablesFromRawTables(
		final IContainer<Table<IEntity<DataImplementation>>> tables,
		final IContainer<ITableDTO> rawTables,
		final IContainer<? extends ITable<DataImplementation, IEntity<DataImplementation>>> referencableTables
	) {
		for (final var t : tables) {
			final var tableName = t.getName();
			final var rawTable = rawTables.getRefFirst(rt -> rt.getName().equals(tableName));
			addBaseReferenceColumnsToTableFromRawTable(t, rawTable, referencableTables);
		}
	}
	
	//method
	private void addBaseReferenceColumnsToTableFromRawTable(
		final Table<IEntity<DataImplementation>> table,
		final ITableDTO rawTable,
		final IContainer<? extends ITable<DataImplementation, IEntity<DataImplementation>>> referencableTables
	) {
		
		final var rawBaseReferenceColumns =
		rawTable
		.getColumns()
		.getRefSelected(
			c -> c.getParametrizedPropertyType().getPropertyType().getBaseType() == BasePropertyType.BASE_REFERENCE
		);
		
		for (final var c : rawBaseReferenceColumns) {
			
			final var column =
			columnMapper.createColumnFromDTOForParentTableUsingGivenReferencableTables(
				c,
				table,
				referencableTables
			);
			
			table.internalAddColumn(column);
		}
	}
	
	//method
	private void addBaseBackReferenceColumnsToTablesFromRawTables(
		final IContainer<Table<IEntity<DataImplementation>>> tables,
		final IContainer<ITableDTO> rawTables,
		final IContainer<? extends ITable<DataImplementation, IEntity<DataImplementation>>> referencableTables
	) {
		for (final var t : tables) {
			final var tableName = t.getName();
			final var rawTable = rawTables.getRefFirst(rt -> rt.getName().equals(tableName));
			addBaseBackReferenceColumnsToTableFromRawTable(t, rawTable, referencableTables);
		}
	}
	
	//method
	private void addBaseBackReferenceColumnsToTableFromRawTable(
		final Table<IEntity<DataImplementation>> table,
		final ITableDTO rawTable,
		final IContainer<? extends ITable<DataImplementation, IEntity<DataImplementation>>> referencableTables
	) {
		
		final var rawBaseValueColumns =
		rawTable
		.getColumns()
		.getRefSelected(
			c -> c.getParametrizedPropertyType().getPropertyType().getBaseType() == BasePropertyType.BASE_BACK_REFERENCE
		);
		
		for (final var c : rawBaseValueColumns) {
			
			final var column =
			columnMapper.createColumnFromDTOForParentTableUsingGivenReferencableTables(
				c,
				table,
				referencableTables
			);
			
			table.internalAddColumn(column);
		}
	}
}
