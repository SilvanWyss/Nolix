//package declaration
package ch.nolix.system.objectschema.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//class
public final class ParameterizedOptionalReferenceType extends BaseParameterizedReferenceType {

  //constructor
  private ParameterizedOptionalReferenceType(final ITable referencedTable) {
    super(referencedTable);
  }

  //static method
  public static ParameterizedOptionalReferenceType forReferencedTable(final ITable referencedTable) {
    return new ParameterizedOptionalReferenceType(referencedTable);
  }

  //method
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.OPTIONAL_REFERENCE;
  }
}
