package ch.nolix.tech.relationaldoc.dataevaluator;

import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;

public final class AbstractValueContentEvaluator {

  public boolean canSetDataType(final IAbstractValueContent abstractValueContent, final DataType dataType) {
    return canSetDataType(dataType)
    && canSetDataTypeBecauseOfCardinality(abstractValueContent, dataType);
  }

  private boolean canSetDataTypeBecauseOfCardinality(IAbstractValueContent abstractValueContent, DataType dataType) {
    return abstractValueContent != null
    && (abstractValueContent.getDataType() == dataType
    || abstractValueContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE);
  }

  private boolean canSetDataType(final DataType dataType) {
    return (dataType != null);
  }
}
