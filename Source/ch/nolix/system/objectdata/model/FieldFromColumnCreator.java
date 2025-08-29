package ch.nolix.system.objectdata.model;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IBaseBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IBaseValueModelView;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;
import ch.nolix.systemapi.objectdata.schemaview.IContentModelView;

public final class FieldFromColumnCreator {

  private FieldFromColumnCreator() {
  }

  public static AbstractField createFieldFromAndWithColumnView(final IColumnView<ITable<IEntity>> columnView) {

    final var field = createFieldFromColumnView(columnView);

    field.setParentColumn(columnView);

    return field;
  }

  private static AbstractField createFieldFromColumnView(final IColumnView<ITable<IEntity>> columnView) {

    final var contentModelView = columnView.getContentModel();

    return createFieldFromContentModelView(contentModelView);
  }

  @SuppressWarnings("unchecked")
  private static AbstractField createFieldFromContentModelView(
    final IContentModelView<ITable<IEntity>> contentModelView) {

    final var fieldType = contentModelView.getFieldType();

    return //
    switch (fieldType) {
      case VALUE_FIELD ->
        ValueField.withValueType(((IBaseValueModelView<Object, ITable<IEntity>>) contentModelView).getValueType());
      case OPTIONAL_VALUE_FIELD ->
        OptionalValueField
          .withValueType(((IBaseValueModelView<Object, ITable<IEntity>>) contentModelView).getValueType());
      case MULTI_VALUE_FIELD ->
        MultiValueField.withValueType(((IBaseValueModelView<Object, ITable<IEntity>>) contentModelView)
          .getValueType());
      case REFERENCE ->
        createReferenceFromAbstractReferenceModelView(
          (IBaseReferenceModelView<ITable<IEntity>>) contentModelView);
      case OPTIONAL_REFERENCE ->
        createOptionalReferenceFromAbstractReferenceModelView(
          (IBaseReferenceModelView<ITable<IEntity>>) contentModelView);
      case MULTI_REFERENCE ->
        createMultiReferenceFromAbstractReferenceModelView(
          (IBaseReferenceModelView<ITable<IEntity>>) contentModelView);
      case BACK_REFERENCE ->
        createBackReferenceFromAbstractBackReferenceModelView(
          (IBaseBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>>) contentModelView);
      case OPTIONAL_BACK_REFERENCE ->
        createOptionalBackReferenceFromAbstractBackReferenceModelView(
          (IBaseBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>>) contentModelView);
      case MULTI_BACK_REFERENCE ->
        createMultiBackReferenceFromAbstractBackReferenceModelView(
          (IBaseBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>>) contentModelView);
    };
  }

  private static Reference<AbstractEntity> createReferenceFromAbstractReferenceModelView(
    final IBaseReferenceModelView<ITable<IEntity>> abstractReferenceModelView) {

    final var referenceableTableNames = abstractReferenceModelView.getStoredReferenceableTables().to(ITable::getName);

    return Reference.forReferenceableTableNames(referenceableTableNames);
  }

  private static OptionalReference<AbstractEntity> createOptionalReferenceFromAbstractReferenceModelView(
    final IBaseReferenceModelView<ITable<IEntity>> abstractReferenceModelView) {

    final var referenceableTableNames = abstractReferenceModelView.getStoredReferenceableTables().to(ITable::getName);

    //TODO: Update OptionalReference
    return OptionalReference.forReferenceableTableName(referenceableTableNames.getStoredFirst());
  }

  private static MultiReference<AbstractEntity> createMultiReferenceFromAbstractReferenceModelView(
    final IBaseReferenceModelView<ITable<IEntity>> abstractReferenceModelView) {

    final var referenceableTableNames = abstractReferenceModelView.getStoredReferenceableTables().to(ITable::getName);

    //TODO: Update MultiReference
    return MultiReference.forReferenceableTableName(referenceableTableNames.getStoredFirst());
  }

  private static BackReference<AbstractEntity> createBackReferenceFromAbstractBackReferenceModelView(
    final IBaseBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>> //
    abstractBackReferenceModelView) {

    final var backReferencedColumn = abstractBackReferenceModelView.getBackReferencedColumn();
    final var backReferencedTable = backReferencedColumn.getStoredParentTable();
    final var backReferencedTableName = backReferencedTable.getName();
    final var backReferencedFieldName = backReferencedColumn.getName();

    return //
    BackReference.forEntityWithTableNameAndBackReferencedFieldName(backReferencedTableName, backReferencedFieldName);
  }

  private static OptionalBackReference<AbstractEntity> createOptionalBackReferenceFromAbstractBackReferenceModelView(
    final IBaseBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>> //
    abstractBackReferenceModelView) {

    final var backReferencedColumn = abstractBackReferenceModelView.getBackReferencedColumn();
    final var backReferencedTable = backReferencedColumn.getStoredParentTable();
    final var backReferencedTableName = backReferencedTable.getName();
    final var backReferencedFieldName = backReferencedColumn.getName();

    return //
    OptionalBackReference.forEntityWithTableNameAndBackReferencedFieldName(
      backReferencedTableName,
      backReferencedFieldName);
  }

  private static MultiBackReference<AbstractEntity> createMultiBackReferenceFromAbstractBackReferenceModelView(
    final IBaseBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>> //
    abstractBackReferenceModelView) {

    final var backReferencedColumn = abstractBackReferenceModelView.getBackReferencedColumn();
    final var backReferencedTable = backReferencedColumn.getStoredParentTable();
    final var backReferencedTableName = backReferencedTable.getName();
    final var backReferencedFieldName = backReferencedColumn.getName();

    return //
    MultiBackReference.forBackReferencedTableAndBaseReference(backReferencedTableName, backReferencedFieldName);
  }
}
