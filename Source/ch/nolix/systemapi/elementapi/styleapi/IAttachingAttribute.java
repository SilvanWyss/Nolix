package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IValueHolder;

public interface IAttachingAttribute extends IValueHolder<String> {

  Enum<?> getTag();

  boolean hasTag();

  boolean hasTag(Enum<?> tag);
}
