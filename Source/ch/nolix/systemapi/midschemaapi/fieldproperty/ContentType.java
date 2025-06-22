package ch.nolix.systemapi.midschemaapi.fieldproperty;

import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

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

  private final BaseContentType baseType;

  private final Cardinality cardinality;

  ContentType(final BaseContentType baseType, final Cardinality cardinality) {
    this.baseType = baseType;
    this.cardinality = cardinality;
  }

  public static ContentType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }

  public BaseContentType getBaseType() {
    return baseType;
  }

  public Cardinality getCardinality() {
    return cardinality;
  }
}
