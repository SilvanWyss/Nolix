//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedBackReferenceType extends BaseParameterizedBackReferenceType {

  //constructor
  private ParameterizedBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static ParameterizedBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedBackReferenceType(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.BACK_REFERENCE;
  }
}
