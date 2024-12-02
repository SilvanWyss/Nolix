package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractValueContent;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public final class AbstractValueContentExaminer {

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
