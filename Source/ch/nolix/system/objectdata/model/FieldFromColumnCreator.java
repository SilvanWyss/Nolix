package ch.nolix.system.objectdata.model;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractBackReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractReferenceModelView;
import ch.nolix.systemapi.objectdata.schemaview.IAbstractValueModelView;
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
        ValueField.withValueType(((IAbstractValueModelView<Object, ITable<IEntity>>) contentModelView).getValueType());
      case OPTIONAL_VALUE_FIELD ->
        OptionalValueField
          .withValueType(((IAbstractValueModelView<Object, ITable<IEntity>>) contentModelView).getValueType());
      case MULTI_VALUE_FIELD ->
        MultiValueField.withValueType(((IAbstractValueModelView<Object, ITable<IEntity>>) contentModelView)
          .getValueType());
      case REFERENCE ->
        createReferenceFromAbstractReferenceModelView(
          (IAbstractReferenceModelView<ITable<IEntity>>) contentModelView);
      case OPTIONAL_REFERENCE ->
        createOptionalReferenceFromAbstractReferenceModelView(
          (IAbstractReferenceModelView<ITable<IEntity>>) contentModelView);
      case MULTI_REFERENCE ->
        createMultiReferenceFromAbstractReferenceModelView(
          (IAbstractReferenceModelView<ITable<IEntity>>) contentModelView);
      case BACK_REFERENCE ->
        createBackReferenceFromAbstractBackReferenceModelView(
          (IAbstractBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>>) contentModelView);
      case OPTIONAL_BACK_REFERENCE ->
        createOptionalBackReferenceFromAbstractBackReferenceModelView(
          (IAbstractBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>>) contentModelView);
      case MULTI_BACK_REFERENCE ->
        createMultiBackReferenceFromAbstractBackReferenceModelView(
          (IAbstractBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>>) contentModelView);
    };
  }

  private static Reference<AbstractEntity> createReferenceFromAbstractReferenceModelView(
    final IAbstractReferenceModelView<ITable<IEntity>> abstractReferenceModelView) {

    final var referencedtableName = abstractReferenceModelView.getStoredReferencedTable().getName();

    return Reference.forTable(referencedtableName);
  }

  private static OptionalReference<AbstractEntity> createOptionalReferenceFromAbstractReferenceModelView(
    final IAbstractReferenceModelView<ITable<IEntity>> abstractReferenceModelView) {

    final var referencedtableName = abstractReferenceModelView.getStoredReferencedTable().getName();

    return OptionalReference.forTable(referencedtableName);
  }

  private static MultiReference<AbstractEntity> createMultiReferenceFromAbstractReferenceModelView(
    final IAbstractReferenceModelView<ITable<IEntity>> abstractReferenceModelView) {

    final var referencedtableName = abstractReferenceModelView.getStoredReferencedTable().getName();

    return MultiReference.forTable(referencedtableName);
  }

  private static BackReference<AbstractEntity> createBackReferenceFromAbstractBackReferenceModelView(
    final IAbstractBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>> //
    abstractBackReferenceModelView) {

    final var backReferencedColumn = abstractBackReferenceModelView.getBackReferencedColumn();
    final var backReferencedTable = backReferencedColumn.getStoredParentTable();
    final var backReferencedTableName = backReferencedTable.getName();
    final var backReferencedFieldName = backReferencedColumn.getName();

    return //
    BackReference.forEntityWithTableNameAndBackReferencedFieldName(backReferencedTableName, backReferencedFieldName);
  }

  private static OptionalBackReference<AbstractEntity> createOptionalBackReferenceFromAbstractBackReferenceModelView(
    final IAbstractBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>> //
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
    final IAbstractBackReferenceModelView<IColumnView<ITable<IEntity>>, ITable<IEntity>> //
    abstractBackReferenceModelView) {

    final var backReferencedColumn = abstractBackReferenceModelView.getBackReferencedColumn();
    final var backReferencedTable = backReferencedColumn.getStoredParentTable();
    final var backReferencedTableName = backReferencedTable.getName();
    final var backReferencedFieldName = backReferencedColumn.getName();

    return //
    MultiBackReference.forBackReferencedTableAndBaseReference(backReferencedTableName, backReferencedFieldName);
  }
}
