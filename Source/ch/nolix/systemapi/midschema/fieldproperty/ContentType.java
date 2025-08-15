package ch.nolix.systemapi.midschema.fieldproperty;

import ch.nolix.coreapi.datamodel.cardinality.Cardinality;
import ch.nolix.coreapi.document.node.INode;

public enum ContentType {
  VALUE_FIELD(BaseFieldType.BASE_VALUE_FIELD, Cardinality.TO_ONE),
  OPTIONAL_VALUE_FIELD(BaseFieldType.BASE_VALUE_FIELD, Cardinality.TO_ONE_OR_NONE),
  MULTI_VALUE_FIELD(BaseFieldType.BASE_VALUE_FIELD, Cardinality.TO_MANY),
  REFERENCE(BaseFieldType.BASE_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_REFERENCE(BaseFieldType.BASE_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_REFERENCE(BaseFieldType.BASE_REFERENCE, Cardinality.TO_MANY),
  BACK_REFERENCE(BaseFieldType.BASE_BACK_REFERENCE, Cardinality.TO_ONE),
  OPTIONAL_BACK_REFERENCE(BaseFieldType.BASE_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
  MULTI_BACK_REFERENCE(BaseFieldType.BASE_BACK_REFERENCE, Cardinality.TO_MANY);

  private final BaseFieldType baseType;

  private final Cardinality cardinality;

  ContentType(final BaseFieldType baseType, final Cardinality cardinality) {
    this.baseType = baseType;
    this.cardinality = cardinality;
  }

  public static ContentType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }

  public BaseFieldType getBaseType() {
    return baseType;
  }

  public Cardinality getCardinality() {
    return cardinality;
  }
}
