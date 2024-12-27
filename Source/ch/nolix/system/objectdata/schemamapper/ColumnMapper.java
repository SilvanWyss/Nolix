package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.model.Column;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class ColumnMapper implements IColumnMapper {

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IContentModelMapper PARAMETERIZED_FIELD_TYPE_MAPPER = //
  new ContentModelMapper();

  @Override
  public IColumn mapFieldToColumn(final IField field, final IContainer<ITable> referencedTables) {
    return new Column(field.getName(), PARAMETERIZED_FIELD_TYPE_MAPPER.mapFieldToContentModel(field, referencedTables));
  }

  @Override
  public IContainer<IColumn> mapEntityToColumns(final IEntity entity, final IContainer<ITable> referencedTables) {
    return entity.internalGetStoredFields().to(p -> mapFieldToColumn(p, referencedTables));
  }

  @Override
  public IContainer<IColumn> mapEntityTypeToColumns(
    final Class<? extends IEntity> entityType,
    final IContainer<ITable> referencedTables) {
    return mapEntityToColumns(ENTITY_CREATOR.createEmptyEntityOf(entityType), referencedTables);
  }
}
