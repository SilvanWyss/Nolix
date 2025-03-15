package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.application.relationaldoc.backend.datamodel.CategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;

public final class CategorizableReferenceContentExaminer {

  public boolean canSetReferenceType(
    final CategorizableReferenceContent categorizableReferenceContent,
    final ISmartObject referenceType) {
    return categorizableReferenceContent != null
    && referenceType != null;
  }
}
