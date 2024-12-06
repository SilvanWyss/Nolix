package ch.nolix.system.element.style;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttributeValidator;

public final class AttachingAttributeValidator implements IAttachingAttributeValidator {

  @Override
  public void assertHasTag(final IAttachingAttribute attachingAttribute) {
    if (!attachingAttribute.hasTag()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
        attachingAttribute,
        LowerCaseVariableCatalogue.TAG);
    }
  }
}
