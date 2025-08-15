package ch.nolix.system.sqlmidschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.midschema.model.BackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiValueModelDto;
import ch.nolix.systemapi.midschema.model.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalValueModelDto;
import ch.nolix.systemapi.midschema.model.ReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ValueModelDto;
import ch.nolix.systemapi.sqlmidschema.modelsqldto.ContentModelSqlDto;

public final class ContentModelSqlRecordMapper {

  private static final String NULL = "NULL";

  public ContentModelSqlDto mapContentModelDtoToContentModelSqlDto(final IContentModelDto contentModelDto) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + valueModelDto.getFieldType().name() + "'",
        "'" + valueModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalValueModelDto.getFieldType().name() + "'",
        "'" + optionalValueModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiValueModelDto.getFieldType().name() + "'",
        "'" + multiValueModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + referenceModelDto.getFieldType().name() + "'",
        "'" + referenceModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalReferenceModelDto.getFieldType().name() + "'",
        "'" + optionalReferenceModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiReferenceModelDto.getFieldType().name() + "'",
        "'" + multiReferenceModelDto.dataType().name() + "'",
        NULL);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + backReferenceModelDto.getFieldType().name() + "'",
        "'" + backReferenceModelDto.dataType().name() + "'",
        "'" + backReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + optionalBackReferenceModelDto.getFieldType().name() + "'",
        "'" + optionalBackReferenceModelDto.dataType().name() + "'",
        "'" + optionalBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      new ContentModelSqlDto(
        "'" + multiBackReferenceModelDto.getFieldType().name() + "'",
        "'" + multiBackReferenceModelDto.dataType().name() + "'",
        "'" + multiBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
