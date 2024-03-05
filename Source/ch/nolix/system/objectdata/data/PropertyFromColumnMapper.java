//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;

//class
public final class PropertyFromColumnMapper {

  //method
  public Property createPropertyFromColumn(final IColumn column) {

    final var property = createEmptyPropertyFromColumn(column);
    property.internalSetParentColumn(column);

    return property;
  }

  //method
  private Property createEmptyPropertyFromColumn(final IColumn column) {
    return switch (column.getParameterizedPropertyType().getPropertyType()) {
      case VALUE ->
        Value.withValueType(column.getParameterizedPropertyType().asBaseParameterizedValueType().getValueType());
      case OPTIONAL_VALUE ->
        OptionalValue
          .withValueType(column.getParameterizedPropertyType().asBaseParameterizedValueType().getValueType());
      case MULTI_VALUE ->
        MultiValue.withValueType(column.getParameterizedPropertyType().asBaseParameterizedValueType().getValueType());
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
  private Property createEmptyReferenceFromReferenceColumn(final IColumn referenceColumn) {

    final var referencedtableName = referenceColumn
      .getParameterizedPropertyType()
      .asBaseParameterizedReferenceType()
      .getStoredencedTable()
      .getName();

    return Reference.forEntityWithTableName(referencedtableName);
  }

  //method
  private Property createEmptyOptionalReferenceFromOptionalReferenceColumn(final IColumn optionalReferenceColumn) {

    final var referencedtableName = optionalReferenceColumn
      .getParameterizedPropertyType()
      .asBaseParameterizedReferenceType()
      .getStoredencedTable()
      .getName();

    return OptionalReference.forEntityWithTableName(referencedtableName);
  }

  //method
  private Property createEmptyMultiReferenceFromMultiReferenceColumn(final IColumn multiReferenceColumn) {

    final var referencedtableName = multiReferenceColumn
      .getParameterizedPropertyType()
      .asBaseParameterizedReferenceType()
      .getStoredencedTable()
      .getName();

    return MultiReference.forReferencedTable(referencedtableName);
  }

  //method
  private Property createEmptyBackReferenceFromBackReferenceColumn(final IColumn backReferenceColumn) {

    final var backReferencedColumn = backReferenceColumn
      .getParameterizedPropertyType()
      .asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedPropertyName = backReferencedColumn.getName();

    return BackReference.forEntityWithTableNameAndBackReferencedPropertyName(backReferencedTableName,
      backReferencedPropertyName);
  }

  //method
  private Property createEmptyOptionalBackReferenceFromOptionalBackReferenceColumn(
    final IColumn optionalBackReferenceColumn) {

    final var backReferencedColumn = optionalBackReferenceColumn
      .getParameterizedPropertyType()
      .asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedPropertyName = backReferencedColumn.getName();

    return OptionalBackReference.forEntityWithTableNameAndBackReferencedPropertyName(backReferencedTableName,
      backReferencedPropertyName);
  }

  //method
  private Property createEmptyMultiBackReferenceFromMultiBackReferenceColumn(
    final IColumn multiBackReferenceColumn) {

    final var backReferencedColumn = multiBackReferenceColumn
      .getParameterizedPropertyType()
      .asBaseParameterizedBackReferenceType()
      .getBackReferencedColumn();

    final var backReferencedTableName = backReferencedColumn.getStoredParentTable().getName();

    final var backReferencedPropertyName = backReferencedColumn.getName();

    return MultiBackReference.forBackReferencedTableAndBaseReference(backReferencedTableName,
      backReferencedPropertyName);
  }
}
