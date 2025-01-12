package ch.nolix.system.objectdata.model;

import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;

public final class FieldFromColumnMapper {

  public AbstractField createFieldFromColumn(final IColumn column) {

    final var field = createEmptyFieldFromColumn(column);
    field.internalSetParentColumn(column);

    return field;
  }

  private AbstractField createEmptyFieldFromColumn(final IColumn column) {
    return switch (column.getContentModel().getContentType()) {
      case VALUE ->
        Value.withValueType(column.getContentModel().asAbstractValueModel().getValueType());
      case OPTIONAL_VALUE ->
        OptionalValue
          .withValueType(column.getContentModel().asAbstractValueModel().getValueType());
      case MULTI_VALUE ->
        MultiValue.withValueType(column.getContentModel().asAbstractValueModel().getValueType());
      case REFERENCE ->
        createEmptyReferenceFromReferenceColumn(column);
      case OPTIONAL_REFERENCE ->
        createEmptyOptionalReferenceFromOptionalReferenceColumn(column);
      case MULTI_REFERENCE ->
        createEmptyMultiReferenceFromMultiReferenceColumn(column);
      case BACK_REFERENCE ->
        createEmptyBackReferenceFromBackReferenceColumn(column);
      case OPTIONAL_BACK_REFERENCE ->
        createEmptyOptionalBackReferenceFromOptionalBackReferenceColumn(column);
      case MULTI_BACK_REFERENCE ->
        createEmptyMultiBackReferenceFromMultiBackReferenceColumn(column);
    };
  }

  private AbstractField createEmptyReferenceFromReferenceColumn(final IColumn referenceColumn) {

    final var referencedtableName = referenceColumn
      .getContentModel()
      .asAbstractReferenceModel()
      .getStoredencedTable()
      .getName();

    return Reference.forEntityWithTableName(referencedtableName);
  }

  private AbstractField createEmptyOptionalReferenceFromOptionalReferenceColumn(final IColumn optionalReferenceColumn) {

    final var referencedtableName = optionalReferenceColumn
      .getContentModel()
      .asAbstractReferenceModel()
      .getStoredencedTable()
      .getName();

    return OptionalReference.forEntityWithTableName(referencedtableName);
  }

  private AbstractField createEmptyMultiReferenceFromMultiReferenceColumn(final IColumn multiReferenceColumn) {

    final var referencedtableName = multiReferenceColumn
      .getContentModel()
      .asAbstractReferenceModel()
      .getStoredencedTable()
      .getName();

    return MultiReference.forReferencedTable(referencedtableName);
  }

  private AbstractField createEmptyBackReferenceFromBackReferenceColumn(final IColumn backReferenceColumn) {

    final var backReferencedColumn = backReferenceColumn
      .getContentModel()
      .asAbstractBackReferenceModel()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedFieldName = backReferencedColumn.getName();

    return BackReference.forEntityWithTableNameAndBackReferencedFieldName(backReferencedTableName,
      backReferencedFieldName);
  }

  private AbstractField createEmptyOptionalBackReferenceFromOptionalBackReferenceColumn(
    final IColumn optionalBackReferenceColumn) {

    final var backReferencedColumn = optionalBackReferenceColumn
      .getContentModel()
      .asAbstractBackReferenceModel()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedFieldName = backReferencedColumn.getName();

    return OptionalBackReference.forEntityWithTableNameAndBackReferencedFieldName(backReferencedTableName,
      backReferencedFieldName);
  }

  private AbstractField createEmptyMultiBackReferenceFromMultiBackReferenceColumn(
    final IColumn multiBackReferenceColumn) {

    final var backReferencedColumn = multiBackReferenceColumn
      .getContentModel()
      .asAbstractBackReferenceModel()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedFieldName = backReferencedColumn.getName();

    return MultiBackReference.forBackReferencedTableAndBaseReference(backReferencedTableName,
      backReferencedFieldName);
  }
}
