//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;

//class
public final class ConcreteValueContentEvaluator {

  //method
  public boolean canAddValue(final IConcreteValueContent concreteValueContent, final String value) {
    return canAddValue(value)
    && canAddValueBecauseOfCardinality(concreteValueContent);
  }

  //method
  public boolean canRemoveValue(final IConcreteValueContent concreteValueContent) {
    return concreteValueContent != null
    && (concreteValueContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE
    || concreteValueContent.getStoredValues().getElementCount() > 1);
  }

  //method
  public boolean canRemoveValues(final IConcreteValueContent concreteValueContent) {
    return concreteValueContent != null
    && concreteValueContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE;
  }

  //method
  public boolean canSetDataType(final IConcreteValueContent concreteValueContent, final DataType dataType) {
    return concreteValueContent != null
    && !concreteValueContent.getStoredParentField().inheritsFromBaseField()
    && dataType != null;
  }

  //method
  private boolean canAddValue(final String value) {
    return (value != null);
  }

  //method
  private boolean canAddValueBecauseOfCardinality(final IConcreteValueContent concreteValueContent) {
    return concreteValueContent != null
    && (concreteValueContent.isEmpty()
    || concreteValueContent.getStoredParentField().getCardinality() == Cardinality.TO_MANY);
  }
}
