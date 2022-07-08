//package declaration
package ch.nolix.system.objectdata.schemamapper;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schema.Table;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class TableMapper implements ITableMapper<SchemaImplementation> {
	
	//static attribute
	private static final IColumnMapper<SchemaImplementation> columnMapper = new ColumnMapper();
	
	@Override
	public IContainer<ITable<SchemaImplementation>> createTablesFrom(final ISchema<?> schema) {
		
		final var tables = createEmptyTablesFrom(schema);
		
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
	private IContainer<ITable<SchemaImplementation>> createEmptyTablesFrom(final ISchema<?> schema) {
		return schema.getEntityTypesInOrder().to(this::createEmptyTableFrom);
	}
	
	//method
	private <E extends IEntity<?>> ITable<SchemaImplementation> createEmptyTableFrom(final Class<E> entityType) {
		return new Table(entityType.getSimpleName());
	}
}
