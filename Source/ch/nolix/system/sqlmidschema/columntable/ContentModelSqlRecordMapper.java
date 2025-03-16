package ch.nolix.system.sqlmidschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.midschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ValueModelDto;
import ch.nolix.systemapi.sqlmidschemaapi.modelsqldto.ContentModelSqlDto;

public final class ContentModelSqlRecordMapper {

  private static final String NULL = "NULL";

  public ContentModelSqlDto mapContentModelDtoToContentModelSqlDto(final IContentModelDto contentModelDto) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + valueModelDto.getContentType().name() + "'",
        "'" + valueModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalValueModelDto.getContentType().name() + "'",
        "'" + optionalValueModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiValueModelDto.getContentType().name() + "'",
        "'" + multiValueModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + referenceModelDto.getContentType().name() + "'",
        "'" + referenceModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalReferenceModelDto.getContentType().name() + "'",
        "'" + optionalReferenceModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiReferenceModelDto.getContentType().name() + "'",
        "'" + multiReferenceModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + backReferenceModelDto.getContentType().name() + "'",
        "'" + backReferenceModelDto.dataType().name() + "'",
        "'" + backReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalBackReferenceModelDto.getContentType().name() + "'",
        "'" + optionalBackReferenceModelDto.dataType().name() + "'",
        "'" + optionalBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiBackReferenceModelDto.getContentType().name() + "'",
        "'" + multiBackReferenceModelDto.dataType().name() + "'",
        "'" + multiBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
