//package declaration
package ch.nolix.system.objectdata.schemamapper;

//own imports
import ch.nolix.core.container.LinkedList;
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
	public LinkedList<ITable<SchemaImplementation>> createTablesFrom(final ISchema<?> schema) {
		
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
	private LinkedList<ITable<SchemaImplementation>> createEmptyTablesFrom(final ISchema<?> schema) {
		return schema.getEntityTypesInOrder().to(et -> createEmptyTableFrom(et));
	}
	
	//method
	private <E extends IEntity<?>> ITable<SchemaImplementation> createEmptyTableFrom(final Class<E> entityType) {
		return new Table(entityType.getSimpleName());
	}
}
