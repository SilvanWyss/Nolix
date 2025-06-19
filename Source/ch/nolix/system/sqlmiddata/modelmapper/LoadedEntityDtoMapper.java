package ch.nolix.system.sqlmiddata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.ObjectValueFieldDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.ColumnViewDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.TableViewDto;
import ch.nolix.systemapi.midschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.sqlmiddataapi.modelmapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.sqlmiddataapi.modelmapperapi.ILoadedEntityDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-10-10
 */
public final class LoadedEntityDtoMapper implements ILoadedEntityDtoMapper {

  private static final IContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  @Override
  public EntityLoadingDto mapSqlRecordToEntityLoadingDto(
    final ISqlRecord sqlRecord,
    final TableViewDto tableView) {
    return //
    new EntityLoadingDto(
      sqlRecord.getStoredAtOneBasedIndex(1),
      sqlRecord.getStoredAtOneBasedIndex(2),
      mapSqlRecordToContentFieldDtos(sqlRecord, tableView.columnSchemaViews()));
  }

  @Override
  public IContainer<ObjectValueFieldDto> mapSqlRecordToContentFieldDtos(
    final ISqlRecord sqlRecord,
    final IContainer<ColumnViewDto> columnViews) {

    final ILinkedList<ObjectValueFieldDto> contentFieldDtos = LinkedList.createEmpty();
    var sqlRecordValueIterator = sqlRecord.iterator();

    //Skips meta fields.
    FlowController
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
