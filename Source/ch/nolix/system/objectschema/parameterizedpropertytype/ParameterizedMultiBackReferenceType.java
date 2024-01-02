//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedMultiBackReferenceType extends BaseParameterizedBackReferenceType {

  //constructor
  private ParameterizedMultiBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static ParameterizedMultiBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedMultiBackReferenceType(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.MULTI_BACK_REFERENCE;
  }
}
