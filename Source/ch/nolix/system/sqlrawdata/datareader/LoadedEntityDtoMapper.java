//package declaration
package ch.nolix.system.sqlrawdata.datareader;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.sqlrawdata.datadto.LoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//class
final class LoadedEntityDtoMapper {

  //constant
  private static final ContentFieldMapper CONTENT_FIELD_MAPPER = new ContentFieldMapper();

  //method
  public ILoadedEntityDto createLoadedEntityDtoFrosqlRecord(
    final IContainer<String> sqlRecordValues,
    final ITableInfo tableInfo) {
    return new LoadedEntityDto(
      sqlRecordValues.getStoredAt1BasedIndex(1),
      sqlRecordValues.getStoredAt1BasedIndex(2),
      getContentFieldsFrosqlRecord(sqlRecordValues, tableInfo));
  }

  //method
  private IContainer<ILoadedContentFieldDto> getContentFieldsFrosqlRecord(
    final IContainer<String> sqlRecordValues,
    final ITableInfo tableInfo) {
    return getContentFieldsFrosqlRecord(sqlRecordValues, tableInfo.getColumnInfos());
  }

  //method
  private IContainer<ILoadedContentFieldDto> getContentFieldsFrosqlRecord(
    final IContainer<String> sqlRecordValues,
    final IContainer<IColumnInfo> contentColumnDefinitions) {

    final ILinkedList<ILoadedContentFieldDto> contentFields = LinkedList.createEmpty();
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
