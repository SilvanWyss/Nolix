package ch.nolix.systemapi.style.tool;

import ch.nolix.systemapi.style.model.IAttachingAttribute;

public interface IAttachingAttributeValidator {
  void assertHasTag(IAttachingAttribute attachingAttribute);
}
