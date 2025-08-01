package ch.nolix.system.style.tool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.style.model.IAttachingAttribute;
import ch.nolix.systemapi.style.tool.IAttachingAttributeValidator;

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
