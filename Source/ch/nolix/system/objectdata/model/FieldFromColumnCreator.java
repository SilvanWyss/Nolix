package ch.nolix.system.objectdata.model;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public final class FieldFromColumnCreator {

  public AbstractField createFieldForColumn(final IColumnView<ITable<IEntity>> columnView) {

    final var field = createEmptyFieldFromColumn(columnView);
    field.internalSetParentColumn(columnView);

    return field;
  }

  private AbstractField createEmptyFieldFromColumn(final IColumnView<ITable<IEntity>> columnView) {
    return switch (columnView.getContentModel().getContentType()) {
      case VALUE ->
        Value.withValueType(columnView.getContentModel().asAbstractValueModel().getValueType());
      case OPTIONAL_VALUE ->
        OptionalValue
          .withValueType(columnView.getContentModel().asAbstractValueModel().getValueType());
      case MULTI_VALUE ->
        MultiValue.withValueType(columnView.getContentModel().asAbstractValueModel().getValueType());
      case REFERENCE ->
        createEmptyReferenceFromReferenceColumn(columnView);
      case OPTIONAL_REFERENCE ->
        createEmptyOptionalReferenceFromOptionalReferenceColumn(columnView);
      case MULTI_REFERENCE ->
        createEmptyMultiReferenceFromMultiReferenceColumn(columnView);
      case BACK_REFERENCE ->
        createEmptyBackReferenceFromBackReferenceColumn(columnView);
      case OPTIONAL_BACK_REFERENCE ->
        createEmptyOptionalBackReferenceFromOptionalBackReferenceColumn(columnView);
      case MULTI_BACK_REFERENCE ->
        createEmptyMultiBackReferenceFromMultiBackReferenceColumn(columnView);
    };
  }

  private AbstractField createEmptyReferenceFromReferenceColumn(final IColumnView<ITable<IEntity>> referenceColumn) {

    final var referencedtableName = referenceColumn
      .getContentModel()
      .asAbstractReferenceModel()
      .getStoredReferencedTable()
      .getName();

    return Reference.forEntityWithTableName(referencedtableName);
  }

  private AbstractField createEmptyOptionalReferenceFromOptionalReferenceColumn(
    final IColumnView<ITable<IEntity>> optionalReferenceColumn) {

    final var referencedtableName = optionalReferenceColumn
      .getContentModel()
      .asAbstractReferenceModel()
      .getStoredReferencedTable()
      .getName();

    return OptionalReference.forEntityWithTableName(referencedtableName);
  }

  private AbstractField createEmptyMultiReferenceFromMultiReferenceColumn(
    final IColumnView<ITable<IEntity>> multiReferenceColumn) {

    final var referencedtableName = multiReferenceColumn
      .getContentModel()
      .asAbstractReferenceModel()
      .getStoredReferencedTable()
      .getName();

    return MultiReference.forReferencedTable(referencedtableName);
  }

  private AbstractField createEmptyBackReferenceFromBackReferenceColumn(
    final IColumnView<ITable<IEntity>> backReferenceColumn) {

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
    final IColumnView<ITable<IEntity>> optionalBackReferenceColumn) {

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
    final IColumnView<ITable<IEntity>> multiBackReferenceColumn) {

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
