//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemamapper.ColumnMapper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class SchemaInitializer {

  //constant
  private static final ITableMapper TABLE_MAPPER = new ch.nolix.system.objectdata.schemamapper.TableMapper();

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

    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseValues = entity
      .internalGetStoredFields()
      .getStoredSelected(p -> p.getType().getBaseType() == BaseContentType.BASE_VALUE);

    for (final var bv : baseValues) {

      final var column = COLUMN_MAPPER.createColumnFromGivenPropertyUsingGivenReferencableTables(bv,
        ImmutableList.createEmpty());

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

    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseReferences = entity
      .internalGetStoredFields()
      .getStoredSelected(p -> p.getType().getBaseType() == BaseContentType.BASE_REFERENCE);

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

    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);

    final var baseBackReferences = entity
      .internalGetStoredFields()
      .getStoredSelected(p -> p.getType().getBaseType() == BaseContentType.BASE_BACK_REFERENCE);

    for (final var bbr : baseBackReferences) {

      final var column = COLUMN_MAPPER.createColumnFromGivenPropertyUsingGivenReferencableTables(bbr,
        referencableTables);

      table.addColumn(column);
    }
  }
}
