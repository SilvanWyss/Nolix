package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.application.relationaldoc.backend.datamodel.AbstractReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;

public final class AbstractReferenceContentExaminer {

  public boolean canSetReferenceType(
    final AbstractReferenceContent abstractReferenceContent,
    final IAbstractableObject referenceType) {
    return abstractReferenceContent != null
    && referenceType != null;
  }
}
