package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.rawschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ValueModelDto;
import ch.nolix.systemapi.sqlrawschemaapi.modelsqldto.ContentModelSqlDto;

public final class ContentModelSqlRecordMapper {

  private static final String NULL = "NULL";

  public ContentModelSqlDto mapContentModelDtoToContentModelSqlDto(final IContentModelDto contentModelDto) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + valueModelDto.getContentType().name() + "'",
        "'" + valueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalValueModelDto.getContentType().name() + "'",
        "'" + optionalValueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiValueModelDto.getContentType().name() + "'",
        "'" + multiValueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + referenceModelDto.getContentType().name() + "'",
        "'" + referenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + referenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalReferenceModelDto.getContentType().name() + "'",
        "'" + optionalReferenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + optionalReferenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiReferenceModelDto.getContentType().name() + "'",
        "'" + multiReferenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + multiReferenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + backReferenceModelDto.getContentType().name() + "'",
        "'" + backReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + backReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalBackReferenceModelDto.getContentType().name() + "'",
        "'" + optionalBackReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + optionalBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiBackReferenceModelDto.getContentType().name() + "'",
        "'" + multiBackReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + multiBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
