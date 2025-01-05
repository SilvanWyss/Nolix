package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.rawschemaapi.dto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.ValueModelDto;
import ch.nolix.systemapi.sqlrawschemaapi.modelsqlrecord.ContentModelSqlRecord;

public final class ParameterizedFieldTypeSqlRecordMapper {

  private static final String NULL = "NULL";

  public ContentModelSqlRecord createParameterizedFieldTypeRecordFrom(
    final IContentModelDto contentModelDto) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + valueModelDto.getContentType().name() + "'",
        "'" + valueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + optionalValueModelDto.getContentType().name() + "'",
        "'" + optionalValueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + multiValueModelDto.getContentType().name() + "'",
        "'" + multiValueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + referenceModelDto.getContentType().name() + "'",
        "'" + referenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + referenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + optionalReferenceModelDto.getContentType().name() + "'",
        "'" + optionalReferenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + optionalReferenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + multiReferenceModelDto.getContentType().name() + "'",
        "'" + multiReferenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + multiReferenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + backReferenceModelDto.getContentType().name() + "'",
        "'" + backReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + backReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + optionalBackReferenceModelDto.getContentType().name() + "'",
        "'" + optionalBackReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + optionalBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      new ContentModelSqlRecord(
        "'" + multiBackReferenceModelDto.getContentType().name() + "'",
        "'" + multiBackReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + multiBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
