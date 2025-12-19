package ch.nolix.system.sqlmiddata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.sqlmiddata.modelmapper.IContentFieldDtoMapper;
import ch.nolix.systemapi.sqlmiddata.modelmapper.ILoadedEntityDtoMapper;

/**
 * @author Silvan Wyss
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
      mapSqlRecordToContentFieldDtos(sqlRecord, tableView.columnViews()));
  }

  @Override
  public IContainer<FieldDto> mapSqlRecordToContentFieldDtos(
    final ISqlRecord sqlRecord,
    final IContainer<ColumnViewDto> columnViews) {
    final ILinkedList<FieldDto> contentFieldDtos = LinkedList.createEmpty();
    var sqlRecordValueIterator = sqlRecord.iterator();

    //Skips meta fields.
    FlowController
      .forCount(FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS)
      .run(sqlRecordValueIterator::next);

    String previousString = null;

    for (final var c : columnViews) {
      if (previousString == null) {
        final var fieldType = c.fieldType();

        if (fieldType == FieldType.REFERENCE
        || fieldType == FieldType.OPTIONAL_REFERENCE
        || fieldType == FieldType.BACK_REFERENCE
        || fieldType == FieldType.OPTIONAL_BACK_REFERENCE) {
          previousString = sqlRecordValueIterator.next();
        } else {
          final var string = sqlRecordValueIterator.next();

          final var contentFieldDto = //
          CONTENT_FIELD_DTO_MAPPER.mapNullableStringRepresentedValueToContentFieldDto(string, null, c);

          contentFieldDtos.addAtEnd(contentFieldDto);
        }
      } else {
        final var string = sqlRecordValueIterator.next();

        final var contentFieldDto = //
        CONTENT_FIELD_DTO_MAPPER.mapNullableStringRepresentedValueToContentFieldDto(string, previousString, c);

        contentFieldDtos.addAtEnd(contentFieldDto);
        previousString = null;
      }
    }

    return contentFieldDtos;
  }
}
