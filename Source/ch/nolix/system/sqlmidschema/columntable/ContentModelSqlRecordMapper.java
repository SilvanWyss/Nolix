package ch.nolix.system.sqlmidschema.columntable;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.sql.syntax.SqlKeywordCatalog;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.sqlmidschema.modelsqldto.ContentModelSqlDto;

public final class ContentModelSqlRecordMapper {

  public ContentModelSqlDto mapContentModelDtoToContentModelSqlDto(final ContentModelDto contentModelDto) {
    final var fieldType = contentModelDto.fieldType();
    final var fieldTypeName = fieldType.name();
    final var dataType = contentModelDto.dataType();
    final var dataTypeName = dataType.name();

    return //
    switch (fieldType) {
      case VALUE_FIELD ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",
          SqlKeywordCatalog.NULL);
      case OPTIONAL_VALUE_FIELD ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",
          SqlKeywordCatalog.NULL);
      case MULTI_VALUE_FIELD ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",
          SqlKeywordCatalog.NULL);
      case REFERENCE ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",
          SqlKeywordCatalog.NULL);

      case OPTIONAL_REFERENCE ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",
          SqlKeywordCatalog.NULL);
      case MULTI_REFERENCE ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",
          SqlKeywordCatalog.NULL);
      case BACK_REFERENCE ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",

          //TODO: Re-engineer
          "'" + contentModelDto.backReferenceableColumnIds().getStoredFirst() + "'");
      case OPTIONAL_BACK_REFERENCE ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",

          //TODO: Re-engineer
          "'" + contentModelDto.backReferenceableColumnIds().getStoredFirst() + "'");

      case MULTI_BACK_REFERENCE ->
        new ContentModelSqlDto(
          "'" + fieldTypeName + "'",
          "'" + dataTypeName + "'",

          //TODO: Re-engineer
          "'" + contentModelDto.backReferenceableColumnIds().getStoredFirst() + "'");
      default ->
        throw InvalidArgumentException.forArgument(fieldType);
    };
  }
}
