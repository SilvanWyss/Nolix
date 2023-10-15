//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.tech.relationaldoc.datamodel.AbstractReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class AbstractReferenceContentEvaluator {

  // method
  public boolean canSetReferenceType(
      final AbstractReferenceContent abstractReferenceContent,
      final IAbstractableObject referenceType) {
    return abstractReferenceContent != null
        && referenceType != null;
  }
}
