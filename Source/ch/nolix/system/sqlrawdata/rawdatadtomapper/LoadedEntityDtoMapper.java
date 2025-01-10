package ch.nolix.system.sqlrawdata.rawdatadtomapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.system.sqlrawdata.datareader.ContentFieldMapper;
import ch.nolix.systemapi.rawdataapi.model.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.model.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.sqlrawdataapi.rawdatadtomapperapi.ILoadedEntityDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-10-10
 */
public final class LoadedEntityDtoMapper implements ILoadedEntityDtoMapper {

  private static final ContentFieldMapper CONTENT_FIELD_MAPPER = new ContentFieldMapper();

  @Override
  public EntityLoadingDto mapRecordToEntityLoadingDto(final ISqlRecord paramRecord, final ITableView tableView) {
    return //
    new EntityLoadingDto(
      paramRecord.getStoredAt1BasedIndex(1),
      paramRecord.getStoredAt1BasedIndex(2),
      mapRecordToContentFieldDtos(paramRecord, tableView.getColumnInfos()));
  }

  @Override
  public IContainer<ContentFieldDto<Object>> mapRecordToContentFieldDtos(
    final ISqlRecord paramRecord,
    final IContainer<ColumnViewDto> columnViews) {

    final ILinkedList<ContentFieldDto<Object>> contentFieldDtos = LinkedList.createEmpty();
    var sqlRecordValueIterator = paramRecord.iterator();

    //Skips id.
    sqlRecordValueIterator.next();

    //Skips save stamp.
    sqlRecordValueIterator.next();

    for (final var c : columnViews) {

      final var contentFieldDto = CONTENT_FIELD_MAPPER.createContentFieldFromString(sqlRecordValueIterator.next(), c);

      contentFieldDtos.addAtEnd(contentFieldDto);
    }

    return contentFieldDtos;
  }
}
