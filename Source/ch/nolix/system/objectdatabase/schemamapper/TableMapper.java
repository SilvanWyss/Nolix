//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ISchema;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class TableMapper implements ITableMapper<SchemaImplementation> {
	
	//static attribute
	private static final IColumnMapper<SchemaImplementation> columnMapper = new ColumnMapper();
	
	@Override
	public IContainer<ITable<SchemaImplementation>> createTablesFrom(final ISchema<?> schema) {
		
		final var tables = createEmptyTablesFromSchema(schema);
		
		for (final var t : tables) {
			final var entityType = schema.getEntityTypesInOrder().getRefFirst(et -> t.hasName(et.getSimpleName()));						
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
	public IContainer<ITable<SchemaImplementation>> createEmptyTablesFromSchema(final ISchema<?> schema) {
		return schema.getEntityTypesInOrder().to(this::createEmptyTableFrom);
	}
	
	//method
	private <E extends IEntity<?>> ITable<SchemaImplementation> createEmptyTableFrom(final Class<E> entityType) {
		return new Table(entityType.getSimpleName());
	}
}
