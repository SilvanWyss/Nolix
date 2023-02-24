//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class TableMapper implements ITableMapper {
	
	//static attribute
	private static final IColumnMapper columnMapper = new ColumnMapper();
	
	@Override
	public IContainer<ITable> createTablesFrom(final ISchema schema) {
		
		final var tables = createEmptyTablesFromSchema(schema);
		
		for (final var t : tables) {
			final var entityType = schema.getEntityTypes().getRefFirst(et -> t.hasName(et.getSimpleName()));						
			for (
				final var c :
				columnMapper.createColumnsFromGivenEntityTypeUsingGivenReferencableTables(entityType, tables)
			) {
				t.addColumn(c);
			}
		}
		
		return tables;
	}
	
	//method
	@Override
	public IContainer<ITable> createEmptyTablesFromSchema(final ISchema schema) {
		return schema.getEntityTypes().to(this::createEmptyTableFrom);
	}
	
	//method
	private <E extends IEntity> ITable createEmptyTableFrom(final Class<E> entityType) {
		return new Table(entityType.getSimpleName());
	}
}
