package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.application.relationaldoc.backend.datamodel.CategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;

public final class CategorizableReferenceContentExaminer {

  public boolean canSetReferenceType(
    final CategorizableReferenceContent categorizableReferenceContent,
    final ICategorizableObject referenceType) {
    return categorizableReferenceContent != null
    && referenceType != null;
  }
}
