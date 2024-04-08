//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;

//class
public final class FieldFromColumnMapper {

  //method
  public Field createFieldFromColumn(final IColumn column) {

    final var field = createEmptyFieldFromColumn(column);
    field.internalSetParentColumn(column);

    return field;
  }

  //method
  private Field createEmptyFieldFromColumn(final IColumn column) {
    return switch (column.getParameterizedFieldType().getFieldType()) {
      case VALUE ->
        Value.withValueType(column.getParameterizedFieldType().asBaseParameterizedValueType().getValueType());
      case OPTIONAL_VALUE ->
        OptionalValue
          .withValueType(column.getParameterizedFieldType().asBaseParameterizedValueType().getValueType());
      case MULTI_VALUE ->
        MultiValue.withValueType(column.getParameterizedFieldType().asBaseParameterizedValueType().getValueType());
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

  //method
  private Field createEmptyReferenceFromReferenceColumn(final IColumn referenceColumn) {

    final var referencedtableName = referenceColumn
      .getParameterizedFieldType()
      .asBaseParameterizedReferenceType()
      .getStoredencedTable()
      .getName();

    return Reference.forEntityWithTableName(referencedtableName);
  }

  //method
  private Field createEmptyOptionalReferenceFromOptionalReferenceColumn(final IColumn optionalReferenceColumn) {

    final var referencedtableName = optionalReferenceColumn
      .getParameterizedFieldType()
      .asBaseParameterizedReferenceType()
      .getStoredencedTable()
      .getName();

    return OptionalReference.forEntityWithTableName(referencedtableName);
  }

  //method
  private Field createEmptyMultiReferenceFromMultiReferenceColumn(final IColumn multiReferenceColumn) {

    final var referencedtableName = multiReferenceColumn
      .getParameterizedFieldType()
      .asBaseParameterizedReferenceType()
      .getStoredencedTable()
      .getName();

    return MultiReference.forReferencedTable(referencedtableName);
  }

  //method
  private Field createEmptyBackReferenceFromBackReferenceColumn(final IColumn backReferenceColumn) {

    final var backReferencedColumn = backReferenceColumn
      .getParameterizedFieldType()
      .asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedFieldName = backReferencedColumn.getName();

    return BackReference.forEntityWithTableNameAndBackReferencedFieldName(backReferencedTableName,
      backReferencedFieldName);
  }

  //method
  private Field createEmptyOptionalBackReferenceFromOptionalBackReferenceColumn(
    final IColumn optionalBackReferenceColumn) {

    final var backReferencedColumn = optionalBackReferenceColumn
      .getParameterizedFieldType()
      .asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedFieldName = backReferencedColumn.getName();

    return OptionalBackReference.forEntityWithTableNameAndBackReferencedFieldName(backReferencedTableName,
      backReferencedFieldName);
  }

  //method
  private Field createEmptyMultiBackReferenceFromMultiBackReferenceColumn(
    final IColumn multiBackReferenceColumn) {

    final var backReferencedColumn = multiBackReferenceColumn
      .getParameterizedFieldType()
      .asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedFieldName = backReferencedColumn.getName();

    return MultiBackReference.forBackReferencedTableAndBaseReference(backReferencedTableName,
      backReferencedFieldName);
  }
}
