//package declaration
package ch.nolix.system.sqldatabaserawdata.databasereader;

//Java imports
import java.util.List;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqldatabaserawdata.databasedto.LoadedEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
final class LoadedEntityDtoMapper {

  // constant
  private static final ContentFieldMapper CONTENT_FIELD_MAPPER = new ContentFieldMapper();

  // method
  public ILoadedEntityDto createLoadedEntityDtoFrosqlRecord(
      final List<String> sqlRecordValues,
      final ITableInfo tableInfo) {
    return new LoadedEntityDto(
        sqlRecordValues.get(0),
        sqlRecordValues.get(1),
        getContentFieldsFrosqlRecord(sqlRecordValues, tableInfo));
  }

  // method
  private IContainer<ILoadedContentFieldDto> getContentFieldsFrosqlRecord(
      final List<String> sqlRecordValues,
      final ITableInfo tableInfo) {
    return getContentFieldsFrosqlRecord(sqlRecordValues, tableInfo.getColumnInfos());
  }

  // method
  private IContainer<ILoadedContentFieldDto> getContentFieldsFrosqlRecord(
      final List<String> sqlRecordValues,
      final IContainer<IColumnInfo> contentColumnDefinitions) {

    final var contentFields = new LinkedList<ILoadedContentFieldDto>();
    var sqlRecordValueIterator = sqlRecordValues.iterator();

    // Skips id and save stamp.
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
