package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.systemapi.rawdataapi.dto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

final class LoadedEntityDtoMapper {

  private static final ContentFieldMapper CONTENT_FIELD_MAPPER = new ContentFieldMapper();

  public EntityLoadingDto createLoadedEntityDtoFrosqlRecord(
    final IContainer<String> sqlRecordValues,
    final ITableInfo tableInfo) {
    return //
    new EntityLoadingDto(
      sqlRecordValues.getStoredAt1BasedIndex(1),
      sqlRecordValues.getStoredAt1BasedIndex(2),
      getContentFieldsFromSqlRecord(sqlRecordValues, tableInfo));
  }

  private IContainer<ContentFieldDto<Object>> getContentFieldsFromSqlRecord(
    final IContainer<String> sqlRecordValues,
    final ITableInfo tableInfo) {
    return getContentFieldsFromSqlRecord(sqlRecordValues, tableInfo.getColumnInfos());
  }

  private IContainer<ContentFieldDto<Object>> getContentFieldsFromSqlRecord(
    final IContainer<String> sqlRecordValues,
    final IContainer<IColumnInfo> contentColumnDefinitions) {

    final ILinkedList<ContentFieldDto<Object>> contentFields = LinkedList.createEmpty();
    var sqlRecordValueIterator = sqlRecordValues.iterator();

    //Skips id and save stamp.
    sqlRecordValueIterator.next();
    sqlRecordValueIterator.next();

    for (final var ccd : contentColumnDefinitions) {
      contentFields.addAtEnd(
        CONTENT_FIELD_MAPPER.createContentFieldFromString(
          sqlRecordValueIterator.next(),
          ccd));
    }

    return contentFields;
  }
}
