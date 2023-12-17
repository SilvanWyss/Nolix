//package declaration
package ch.nolix.systemapi.entitypropertyapi.mainapi;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum PropertyType {
  VALUE(BasePropertyType.BASE_VALUE, Cardinality.TO_ONE),
  OPTIONAL_VALUE(BasePropertyType.BASE_VALUE, Cardinality.TO_ONE_OR_NONE),
  MULTI_VALUE(BasePropertyType.BASE_VALUE, Cardinality.TO_MANY),
  REFERENCE(BasePropertyType.BASE_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_REFERENCE(BasePropertyType.BASE_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_REFERENCE(BasePropertyType.BASE_REFERENCE, Cardinality.TO_MANY),
  BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_BACK_REFERENCE(BasePropertyType.BASE_BACK_REFERENCE, Cardinality.TO_MANY);

  //attribute
  private final BasePropertyType baseType;

  //attribute
  private final Cardinality cardinality;

  //constructor
  PropertyType(final BasePropertyType baseType, final Cardinality cardinality) {
    this.baseType = baseType;
    this.cardinality = cardinality;
  }

  //static method
  public static PropertyType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }

  //method
  public BasePropertyType getBaseType() {
    return baseType;
  }

  //method
  public Cardinality getCardinality() {
    return cardinality;
  }
}