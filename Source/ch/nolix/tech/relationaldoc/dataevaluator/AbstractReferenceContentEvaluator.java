package ch.nolix.tech.relationaldoc.dataevaluator;

import ch.nolix.tech.relationaldoc.datamodel.AbstractReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

public final class AbstractReferenceContentEvaluator {

  public boolean canSetReferenceType(
    final AbstractReferenceContent abstractReferenceContent,
    final IAbstractableObject referenceType) {
    return abstractReferenceContent != null
    && referenceType != null;
  }
}
