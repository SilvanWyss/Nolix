package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableValueContent;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public final class CategorizableValueContentExaminer {

  public boolean canSetDataType(final ICategorizableValueContent categorizableValueContent, final DataType dataType) {
    return canSetDataType(dataType)
    && canSetDataTypeBecauseOfCardinality(categorizableValueContent, dataType);
  }

  private boolean canSetDataTypeBecauseOfCardinality(
    final ICategorizableValueContent categorizableValueContent,
    final DataType dataType) {
    return categorizableValueContent != null
    && (categorizableValueContent.getDataType() == dataType
    || categorizableValueContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE);
  }

  private boolean canSetDataType(final DataType dataType) {
    return (dataType != null);
  }
}
