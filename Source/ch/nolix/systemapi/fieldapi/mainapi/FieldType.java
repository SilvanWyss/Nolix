//package declaration
package ch.nolix.systemapi.fieldapi.mainapi;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum FieldType {
  VALUE(BaseFieldType.BASE_VALUE, Cardinality.TO_ONE),
  OPTIONAL_VALUE(BaseFieldType.BASE_VALUE, Cardinality.TO_ONE_OR_NONE),
  MULTI_VALUE(BaseFieldType.BASE_VALUE, Cardinality.TO_MANY),
  REFERENCE(BaseFieldType.BASE_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_REFERENCE(BaseFieldType.BASE_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_REFERENCE(BaseFieldType.BASE_REFERENCE, Cardinality.TO_MANY),
  BACK_REFERENCE(BaseFieldType.BASE_BACK_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_BACK_REFERENCE(BaseFieldType.BASE_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_BACK_REFERENCE(BaseFieldType.BASE_BACK_REFERENCE, Cardinality.TO_MANY);

  //attribute
  private final BaseFieldType baseType;

  //attribute
  private final Cardinality cardinality;

  //constructor
  FieldType(final BaseFieldType baseType, final Cardinality cardinality) {
    this.baseType = baseType;
    this.cardinality = cardinality;
  }

  //static method
  public static FieldType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }

  //method
  public BaseFieldType getBaseType() {
    return baseType;
  }

  //method
  public Cardinality getCardinality() {
    return cardinality;
  }
}
