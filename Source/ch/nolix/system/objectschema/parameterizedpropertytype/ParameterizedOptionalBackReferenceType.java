//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParameterizedOptionalBackReferenceType extends BaseParameterizedBackReferenceType {

  //constructor
  private ParameterizedOptionalBackReferenceType(final IColumn backReferencedColumn) {
    super(backReferencedColumn);
  }

  //static method
  public static ParameterizedOptionalBackReferenceType forBackReferencedColumn(final IColumn backReferencedColumn) {
    return new ParameterizedOptionalBackReferenceType(backReferencedColumn);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.OPTIONAL_BACK_REFERENCE;
  }
}
