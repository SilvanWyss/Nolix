//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.schemamapper.ColumnMapper;
import ch.nolix.systemapi.entitypropertyapi.mainapi.BasePropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdatabaseapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class SchemaInitializer {

  //constant
  private static final ITableMapper TABLE_MAPPER = new ch.nolix.system.objectdatabase.schemamapper.TableMapper();

  //constant
  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  //constant
  private static final IColumnMapper COLUMN_MAPPER = new ColumnMapper();

  //method
  public void initializeDatabaseFromSchemaUsingSchemaAdapterIfDatabaseIsEmpty(
    final ISchema schema,
    final ISchemaAdapter schemaAdapter) {
    if (databaseIsEmpty(schemaAdapter)) {
      initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(schema, schemaAdapter);
    }
  }

  //method
  private boolean databaseIsEmpty(final ISchemaAdapter schemaAdapter) {
    return (schemaAdapter.getTableCount() == 0);
  }

  //method
  private void initializeDatabaseToGivenSchemaUsingGivenSchemaAdapter(
    final ISchema schema,
    final ISchemaAdapter schemaAdapter) {

    final var tables = TABLE_MAPPER.createEmptyTablesFromSchema(schema);

    tables.forEach(schemaAdapter::addTable);

    addBaseValueColumnsToTablesFromSchema(tables, schema);
    addBaseReferenceColumnsToTablesFromSchema(tables, schema, tables);
    addBaseBackReferenceColumnsToTablesFromSchema(tables, schema, tables);

    schemaAdapter.saveChanges();
  }

  //method
  private void addBaseValueColumnsToTablesFromSchema(
    final IContainer<ITable> tables,
    final ISchema schema) {
    for (final var t : tables) {
      final var entityType = schema.getEntityTypeByName(t.getName());
      addBaseValueColumnsToTableFromEntityType(t, entityType);
    }
  }

  //method
  private void addBaseValueColumnsToTableFromEntityType(
    final ITable table,
    final Class<? extends IEntity> entityType) {

    final var entity = ENTITY_CREATOR.createEmptyEntityOf(entityType);

    final var baseValues = entity
      .technicalGetStoredProperties()
      .getStoredSelected(p -> p.getType().getBaseType() == BasePropertyType.BASE_VALUE);

    for (final var bv : baseValues) {

      final var column = COLUMN_MAPPER.createColumnFromGivenPropertyUsingGivenReferencableTables(bv,
        new ImmutableList<>());

      table.addColumn(column);
    }
  }

  //method
  private void addBaseReferenceColumnsToTablesFromSchema(
    final IContainer<ITable> tables,
    final ISchema schema,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = schema.getEntityTypeByName(t.getName());
      addBaseReferenceColumnsToTableFromEntityType(t, entityType, referencableTables);
    }
  }

  //method
  private void addBaseReferenceColumnsToTableFromEntityType(
    final ITable table,
    final Class<? extends IEntity> entityType,
    final IContainer<ITable> referencableTables) {

    final var entity = ENTITY_CREATOR.createEmptyEntityOf(entityType);

    final var baseReferences = entity
      .technicalGetStoredProperties()
      .getStoredSelected(p -> p.getType().getBaseType() == BasePropertyType.BASE_REFERENCE);

    for (final var br : baseReferences) {

      final var column = COLUMN_MAPPER.createColumnFromGivenPropertyUsingGivenReferencableTables(br,
        referencableTables);

      table.addColumn(column);
    }
  }

  //method
  private void addBaseBackReferenceColumnsToTablesFromSchema(
    final IContainer<ITable> tables,
    final ISchema schema,
    final IContainer<ITable> referencableTables) {
    for (final var t : tables) {
      final var entityType = schema.getEntityTypeByName(t.getName());
      addBaseBackReferenceColumnsToTableFromEntityType(t, entityType, referencableTables);
    }
  }

  //method
  private void addBaseBackReferenceColumnsToTableFromEntityType(
    final ITable table,
    final Class<? extends IEntity> entityType,
    final IContainer<ITable> referencableTables) {

    final var entity = ENTITY_CREATOR.createEmptyEntityOf(entityType);

    final var baseBackReferences = entity
      .technicalGetStoredProperties()
      .getStoredSelected(p -> p.getType().getBaseType() == BasePropertyType.BASE_BACK_REFERENCE);

    for (final var bbr : baseBackReferences) {

      final var column = COLUMN_MAPPER.createColumnFromGivenPropertyUsingGivenReferencableTables(bbr,
        referencableTables);

      table.addColumn(column);
    }
  }
}
