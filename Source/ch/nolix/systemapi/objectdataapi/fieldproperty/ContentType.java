//package declaration
package ch.nolix.systemapi.objectdataapi.fieldproperty;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum ContentType {
  VALUE(BaseContentType.BASE_VALUE, Cardinality.TO_ONE),
  OPTIONAL_VALUE(BaseContentType.BASE_VALUE, Cardinality.TO_ONE_OR_NONE),
  MULTI_VALUE(BaseContentType.BASE_VALUE, Cardinality.TO_MANY),
  REFERENCE(BaseContentType.BASE_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_REFERENCE(BaseContentType.BASE_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_REFERENCE(BaseContentType.BASE_REFERENCE, Cardinality.TO_MANY),
  BACK_REFERENCE(BaseContentType.BASE_BACK_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_BACK_REFERENCE(BaseContentType.BASE_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_BACK_REFERENCE(BaseContentType.BASE_BACK_REFERENCE, Cardinality.TO_MANY);

  //attribute
  private final BaseContentType baseType;

  //attribute
  private final Cardinality cardinality;

  //constructor
  ContentType(final BaseContentType baseType, final Cardinality cardinality) {
    this.baseType = baseType;
    this.cardinality = cardinality;
  }

  //static method
  public static ContentType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }

  //method
  public BaseContentType getBaseType() {
    return baseType;
  }

  //method
  public Cardinality getCardinality() {
    return cardinality;
  }
}
