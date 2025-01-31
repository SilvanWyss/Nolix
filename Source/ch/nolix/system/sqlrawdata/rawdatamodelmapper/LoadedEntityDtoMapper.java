package ch.nolix.system.sqlrawdata.rawdatamodelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawdataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.rawschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.sqlrawdataapi.rawdatamodelmapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.sqlrawdataapi.rawdatamodelmapperapi.ILoadedEntityDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-10-10
 */
public final class LoadedEntityDtoMapper implements ILoadedEntityDtoMapper {

  private static final IContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  @Override
  public EntityLoadingDto mapSqlRecordToEntityLoadingDto(final ISqlRecord sqlRecord, final TableViewDto tableView) {
    return //
    new EntityLoadingDto(
      sqlRecord.getStoredAt1BasedIndex(1),
      sqlRecord.getStoredAt1BasedIndex(2),
      mapSqlRecordToContentFieldDtos(sqlRecord, tableView.columnViews()));
  }

  @Override
  public IContainer<ContentFieldDto<Object>> mapSqlRecordToContentFieldDtos(
    final ISqlRecord sqlRecord,
    final IContainer<ColumnSchemaViewDto> columnViews) {

    final ILinkedList<ContentFieldDto<Object>> contentFieldDtos = LinkedList.createEmpty();
    var sqlRecordValueIterator = sqlRecord.iterator();

    //Skips meta fields.
    GlobalSequencer
      .forCount(FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS)
      .run(sqlRecordValueIterator::next);

    for (final var c : columnViews) {

      final var string = sqlRecordValueIterator.next();
      final var contentFieldDto = CONTENT_FIELD_DTO_MAPPER.mapStringToContentFieldDtoUsingColumnView(string, c);

      contentFieldDtos.addAtEnd(contentFieldDto);
    }

    return contentFieldDtos;
  }
}
