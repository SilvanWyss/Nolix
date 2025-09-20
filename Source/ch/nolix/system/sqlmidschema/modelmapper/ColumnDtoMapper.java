package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlmidschema.modelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschema.modelmapper.IContentModelDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-09-02
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {
  private static final IContentModelDtoMapper CONTENT_MODEL_DTO_MAPPER = new ContentModelDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapJoinedColumnSqlRecordToColumnDto(final ISqlRecord joinedColumnSqlRecord) {
    final var id = joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.ID_INDEX);
    final var name = joinedColumnSqlRecord.getStoredAtOneBasedIndex(ColumnTableFieldIndexCatalog.NAME_INDEX);
    final var contentModel = CONTENT_MODEL_DTO_MAPPER.mapJoinedColumnSqlRecordToColumnDto(joinedColumnSqlRecord);
    final var fieldType = contentModel.fieldType();

    return new ColumnDto(id, name, fieldType, contentModel);
  }
}
