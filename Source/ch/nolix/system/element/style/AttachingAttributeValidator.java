package ch.nolix.system.element.style;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.element.style.IAttachingAttribute;
import ch.nolix.systemapi.element.style.IAttachingAttributeValidator;

public final class AttachingAttributeValidator implements IAttachingAttributeValidator {

  @Override
  public void assertHasTag(final IAttachingAttribute attachingAttribute) {
    if (!attachingAttribute.hasTag()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
        attachingAttribute,
        LowerCaseVariableCatalog.TAG);
    }
  }
}
