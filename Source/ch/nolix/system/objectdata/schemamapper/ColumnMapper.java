package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IParameterizedFieldTypeMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ColumnMapper implements IColumnMapper {

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IParameterizedFieldTypeMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ParameterizedFieldTypeMapper();

  @Override
  public IColumn createColumnFromGivenPropertyUsingGivenReferencableTables(
    final IField field,
    final IContainer<ITable> referencableTables) {
    return new Column(
      field.getName(),
      PARAMETERIZED_FIELD_TYPE_MAPPER.createParameterizedFieldTypeFromField(
        field,
        referencableTables));
  }

  @Override
  public <E extends IEntity> IContainer<IColumn> createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
    final Class<E> entityType,
    final IContainer<ITable> referencableTables) {
    return createColumnsFromGivenEntityUsingGivenReferencableTables(
      ENTITY_CREATOR.createEmptyEntityOf(entityType),
      referencableTables);
  }

  @Override
  public IContainer<IColumn> createColumnsFromGivenEntityUsingGivenReferencableTables(
    final IEntity entity,
    final IContainer<ITable> referencableTables) {
    return entity
      .internalGetStoredFields()
      .to(p -> createColumnFromGivenPropertyUsingGivenReferencableTables(p, referencableTables));
  }
}
