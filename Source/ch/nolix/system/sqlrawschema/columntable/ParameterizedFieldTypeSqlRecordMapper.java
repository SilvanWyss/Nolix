package ch.nolix.system.sqlrawschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;

public final class ParameterizedFieldTypeSqlRecordMapper {

  private static final String NULL = "NULL";

  public ParameterizedFieldTypeSqlRecord createParameterizedFieldTypeRecordFrom(
    final IContentModelDto contentModelDto) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + valueModelDto.getContentType().name() + "'",
        "'" + valueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + optionalValueModelDto.getContentType().name() + "'",
        "'" + optionalValueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + multiValueModelDto.getContentType().name() + "'",
        "'" + multiValueModelDto.dataType().name() + "'",
        NULL,
        NULL);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + referenceModelDto.getContentType().name() + "'",
        "'" + referenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + referenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + optionalReferenceModelDto.getContentType().name() + "'",
        "'" + optionalReferenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + optionalReferenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + multiReferenceModelDto.getContentType().name() + "'",
        "'" + multiReferenceModelDto.dataType().name() + "'",

        //TODO: Handle multiple referenced table ids
        "'" + multiReferenceModelDto.referencedTableIds().getStoredFirst() + "'",
        NULL);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + backReferenceModelDto.getContentType().name() + "'",
        "'" + backReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + backReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + optionalBackReferenceModelDto.getContentType().name() + "'",
        "'" + optionalBackReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + optionalBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      new ParameterizedFieldTypeSqlRecord(
        "'" + multiBackReferenceModelDto.getContentType().name() + "'",
        "'" + multiBackReferenceModelDto.dataType().name() + "'",
        NULL,
        "'" + multiBackReferenceModelDto.backReferencedColumnId() + "'");
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
