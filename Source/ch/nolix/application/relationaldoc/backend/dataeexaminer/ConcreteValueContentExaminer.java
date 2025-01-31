package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteValueContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.DataType;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public final class ConcreteValueContentExaminer {

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
