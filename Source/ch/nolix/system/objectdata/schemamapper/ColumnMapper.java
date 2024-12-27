package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public final class ColumnMapper implements IColumnMapper {

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IContentModelMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ContentModelMapper();

  @Override
  public IColumn createColumnFromGivenPropertyUsingGivenReferencableTables(
    final IField field,
    final IContainer<ITable> referencableTables) {
    return new Column(
      field.getName(),
      PARAMETERIZED_FIELD_TYPE_MAPPER.mapFieldToContentModel(
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
