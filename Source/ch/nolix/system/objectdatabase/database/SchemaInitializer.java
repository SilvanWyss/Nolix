//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdatabase.schemamapper.ColumnMapper;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class SchemaInitializer {
	
	//static attribute
	private static final ITableMapper<SchemaImplementation> tableMapper =
	new ch.nolix.system.objectdatabase.schemamapper.TableMapper();
	
	//static attribute
	private static final EntityCreator entityCreator = new EntityCreator();
	
	//static attribute
	private static final IColumnMapper<SchemaImplementation> columnMapper = new ColumnMapper();
	
	//method
	public void initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(
		final ISchema<DataImplementation> schema,
		final ISchemaAdapter<SchemaImplementation> schemaAdapter
	) {
		if (databaseIsEmpty(schemaAdapter)) {
			initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(schema, schemaAdapter);
		}
	}
	
	//method
	private boolean databaseIsEmpty(final ISchemaAdapter<SchemaImplementation> schemaAdapter) {
		return (schemaAdapter.getTableCount() == 0);
	}
	
	//method
	private void initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(
		final ISchema<DataImplementation> schema,
		final ISchemaAdapter<SchemaImplementation> schemaAdapter
	) {
		
		final var tables = tableMapper.createEmptyTablesFromSchema(schema);
		
		tables.forEach(schemaAdapter::addTable);
		
		addBaseValueColumnsToTablesFromSchema(tables, schema);
		addBaseReferenceColumnsToTablesFromSchema(tables, schema, tables);
		addBaseBackReferenceColumnsToTablesFromSchema(tables, schema, tables);
		
		schemaAdapter.saveChangesAndReset();
	}
	
	//method
	private void addBaseValueColumnsToTablesFromSchema(
		final IContainer<ITable<SchemaImplementation>> tables,
		final ISchema<DataImplementation> schema
	) {
		for (final var t : tables) {
			final var entityType = schema.getEntityTypeByName(t.getName());
			addBaseValueColumnsToTableFromEntityType(t, entityType);
		}
	}
	
	//method
	private void addBaseValueColumnsToTableFromEntityType(
		final ITable<SchemaImplementation> table,
		final Class<? extends IEntity<DataImplementation>> entityType
	) {
		
		final var entity = entityCreator.createEmptyEntityOf(entityType);
		
		final var baseValues =
		entity
		.technicalGetRefProperties()
		.getRefSelected(p -> p.getType().getBaseType() == BasePropertyType.BASE_VALUE);
		
		for (final var bv : baseValues) {
			
			final var column =
			columnMapper.createColumnFromGivenPropertyUsingGivenReferencableTables(bv, new ImmutableList<>());
			
			table.addColumn(column);
		}
	}
	
	//method
	private void addBaseReferenceColumnsToTablesFromSchema(
		final IContainer<ITable<SchemaImplementation>> tables,
		final ISchema<DataImplementation> schema,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		for (final var t : tables) {
			final var entityType = schema.getEntityTypeByName(t.getName());
			addBaseReferenceColumnsToTableFromEntityType(t, entityType, referencableTables);
		}
	}
	
	//method
	private void addBaseReferenceColumnsToTableFromEntityType(
		final ITable<SchemaImplementation> table,
		final Class<? extends IEntity<DataImplementation>> entityType,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		
		final var entity = entityCreator.createEmptyEntityOf(entityType);
		
		final var baseReferences =
		entity
		.technicalGetRefProperties()
		.getRefSelected(p -> p.getType().getBaseType() == BasePropertyType.BASE_REFERENCE);
		
		for (final var br : baseReferences) {
			
			final var column =
			columnMapper.createColumnFromGivenPropertyUsingGivenReferencableTables(br, referencableTables);
			
			table.addColumn(column);
		}
	}
	
	//method
	private void addBaseBackReferenceColumnsToTablesFromSchema(
		final IContainer<ITable<SchemaImplementation>> tables,
		final ISchema<DataImplementation> schema,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		for (final var t : tables) {
			final var entityType = schema.getEntityTypeByName(t.getName());
			addBaseBackReferenceColumnsToTableFromEntityType(t, entityType, referencableTables);
		}
	}
	
	//method
	private void addBaseBackReferenceColumnsToTableFromEntityType(
		final ITable<SchemaImplementation> table,
		final Class<? extends IEntity<DataImplementation>> entityType,
		final IContainer<ITable<SchemaImplementation>> referencableTables
	) {
		
		final var entity = entityCreator.createEmptyEntityOf(entityType);
		
		final var baseBackReferences =
		entity
		.technicalGetRefProperties()
		.getRefSelected(p -> p.getType().getBaseType() == BasePropertyType.BASE_BACK_REFERENCE);
		
		for (final var bbr : baseBackReferences) {
			
			final var column =
			columnMapper.createColumnFromGivenPropertyUsingGivenReferencableTables(bbr, referencableTables);
			
			table.addColumn(column);
		}
	}
}
