//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
final class PropertyFromTableExtractor {

  // method
  public IContainer<Property> createPropertiesFromTable(
      final ITable<IEntity> table) {
    return table.getStoredColumns().to(this::createPropertyFromColumn);
  }

  // method
  private Property createEmptyPropertyFromColumn(final IColumn column) {
    switch (column.getParameterizedPropertyType().getPropertyType()) {
      case VALUE:
        return new Value<>();
      case OPTIONAL_VALUE:
        return new OptionalValue<>();
      case MULTI_VALUE:
        return new MultiValue<>();
      case REFERENCE:

        final var referencedtableName = column
            .getParameterizedPropertyType()
            .asBaseParameterizedReferenceType()
            .getStoredencedTable()
            .getName();

        return Reference.forEntityWithTableName(referencedtableName);
      case OPTIONAL_REFERENCE:

        final var referencedtableName2 = column
            .getParameterizedPropertyType()
            .asBaseParameterizedReferenceType()
            .getStoredencedTable()
            .getName();

        return OptionalReference.forEntityWithTableName(referencedtableName2);
      case MULTI_REFERENCE:

        final var referencedtableName3 = column
            .getParameterizedPropertyType()
            .asBaseParameterizedReferenceType()
            .getStoredencedTable()
            .getName();

        return MultiReference.forEntityWithTableName(referencedtableName3);
      case BACK_REFERENCE, OPTIONAL_BACK_REFERENCE, MULTI_BACK_REFERENCE:
      default:
        throw InvalidArgumentException.forArgument(column);
    }
  }

  // method
  private Property createPropertyFromColumn(final IColumn column) {

    final var property = createEmptyPropertyFromColumn(column);
    property.internalSetParentColumn(column);

    return property;
  }
}
