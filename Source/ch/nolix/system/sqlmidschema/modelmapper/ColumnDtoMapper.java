package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi.IContentModelDtoMapper;

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

    return new ColumnDto(id, name, contentModel);
  }
}
