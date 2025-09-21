package ch.nolix.system.objectdata.model;

import ch.nolix.systemapi.objectdata.model.IColumn;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class FieldMapper {
  private FieldMapper() {
  }

  public static AbstractField mapColumnToField(final IColumn column) {
    final var field = mapColumnToUnlinkedFeild(column);

    field.setParentColumn(column);

    return field;
  }

  private static AbstractField mapColumnToUnlinkedFeild(final IColumn column) {
    final var fieldType = column.getFieldType();

    return //
    switch (fieldType) {
      case VALUE_FIELD ->
        ValueField.withValueType(column.getDataTypeClass());
      case OPTIONAL_VALUE_FIELD ->
        OptionalValueField.withValueType(column.getDataTypeClass());
      case MULTI_VALUE_FIELD ->
        MultiValueField.withValueType(column.getDataTypeClass());
      case REFERENCE ->
        createReferenceFromAbstractReferenceModelView(column);
      case OPTIONAL_REFERENCE ->
        createOptionalReferenceFromAbstractReferenceModelView(column);
      case MULTI_REFERENCE ->
        createMultiReferenceFromAbstractReferenceModelView(column);
      case BACK_REFERENCE ->
        createBackReferenceFromAbstractBackReferenceModelView(column);
      case OPTIONAL_BACK_REFERENCE ->
        createOptionalBackReferenceFromAbstractBackReferenceModelView(column);
      case MULTI_BACK_REFERENCE ->
        createMultiBackReferenceFromAbstractBackReferenceModelView(column);
    };
  }

  private static Reference<AbstractEntity> createReferenceFromAbstractReferenceModelView(
    final IColumn column) {
    final var referenceableTableNames = column.getStoredReferenceableTables().to(ITable::getName);

    return Reference.forReferenceableTableNames(referenceableTableNames);
  }

  private static OptionalReference<AbstractEntity> createOptionalReferenceFromAbstractReferenceModelView(
    final IColumn column) {
    final var referenceableTableNames = column.getStoredReferenceableTables().to(ITable::getName);

    return OptionalReference.forReferenceableTableNames(referenceableTableNames);
  }

  private static MultiReference<AbstractEntity> createMultiReferenceFromAbstractReferenceModelView(
    final IColumn column) {
    final var referenceableTableNames = column.getStoredReferenceableTables().to(ITable::getName);

    return MultiReference.forReferenceableTableNames(referenceableTableNames);
  }

  private static BackReference<AbstractEntity> createBackReferenceFromAbstractBackReferenceModelView(
    final IColumn column) {

    final var backReferenceableColumns = column.getStoredBackReferenceableColumns();
    final var backReferenceableTables = backReferenceableColumns.getViewOf(IColumn::getStoredParentTable);
    final var backReferencedFieldName = backReferenceableColumns.getStoredFirst().getName();

    return //
    BackReference.forBackReferenceableTablesAndBackReferencedFieldName(
      backReferenceableTables,
      backReferencedFieldName);
  }

  private static OptionalBackReference<AbstractEntity> createOptionalBackReferenceFromAbstractBackReferenceModelView(
    final IColumn column) {

    final var backReferenceableColumns = column.getStoredBackReferenceableColumns();
    final var backReferenceableTables = backReferenceableColumns.getViewOf(IColumn::getStoredParentTable);
    final var backReferencedFieldName = backReferenceableColumns.getStoredFirst().getName();

    return //
    OptionalBackReference.forBackReferenceableTablesAndBackReferencedFieldName(
      backReferenceableTables,
      backReferencedFieldName);
  }

  private static MultiBackReference<AbstractEntity> createMultiBackReferenceFromAbstractBackReferenceModelView(
    final IColumn column) {

    final var backReferenceableColumns = column.getStoredBackReferenceableColumns();
    final var backReferenceableTables = backReferenceableColumns.getViewOf(IColumn::getStoredParentTable);
    final var backReferencedFieldName = backReferenceableColumns.getStoredFirst().getName();

    return //
    MultiBackReference.forBackReferenceableTablesAndBackReferencedFieldName(
      backReferenceableTables,
      backReferencedFieldName);
  }
}
