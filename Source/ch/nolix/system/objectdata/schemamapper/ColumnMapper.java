package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.model.Column;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IColumnMapper;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.IContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class ColumnMapper implements IColumnMapper {

  private static final IContentModelMapper CONTENT_MODEL_MAPPER = new ContentModelMapper();

  @Override
  public IColumn mapFieldToColumn(final IField field, final IContainer<ITable> referencedTables) {
    return new Column(field.getName(), CONTENT_MODEL_MAPPER.mapFieldToContentModels(field, referencedTables));
  }
}
