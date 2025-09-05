package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectschema.model.Column;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.schemamapper.IColumnMapper;
import ch.nolix.systemapi.objectdata.schemamapper.IContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ColumnMapper implements IColumnMapper {
  private static final IContentModelMapper CONTENT_MODEL_MAPPER = new ContentModelMapper();

  @Override
  public IColumn mapFieldToColumn(final IField field, final IContainer<ITable> referencedTables) {
    return new Column(field.getName(), CONTENT_MODEL_MAPPER.mapFieldToContentModel(field, referencedTables));
  }
}
