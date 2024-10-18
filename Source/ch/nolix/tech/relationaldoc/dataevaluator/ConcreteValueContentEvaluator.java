package ch.nolix.tech.relationaldoc.dataevaluator;

import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;

public final class ConcreteValueContentEvaluator {

  public boolean canAddValue(final IConcreteValueContent concreteValueContent, final String value) {
    return canAddValue(value)
    && canAddValueBecauseOfCardinality(concreteValueContent);
  }

  public boolean canRemoveValue(final IConcreteValueContent concreteValueContent) {
    return concreteValueContent != null
    && (concreteValueContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE
    || concreteValueContent.getStoredValues().getCount() > 1);
  }

  public boolean canRemoveValues(final IConcreteValueContent concreteValueContent) {
    return concreteValueContent != null
    && concreteValueContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE;
  }

  public boolean canSetDataType(final IConcreteValueContent concreteValueContent, final DataType dataType) {
    return concreteValueContent != null
    && !concreteValueContent.getStoredParentField().inheritsFromBaseField()
    && dataType != null;
  }

  private boolean canAddValue(final String value) {
    return (value != null);
  }

  private boolean canAddValueBecauseOfCardinality(final IConcreteValueContent concreteValueContent) {
    return concreteValueContent != null
    && (concreteValueContent.isEmpty()
    || concreteValueContent.getStoredParentField().getCardinality() == Cardinality.TO_MANY);
  }
}
