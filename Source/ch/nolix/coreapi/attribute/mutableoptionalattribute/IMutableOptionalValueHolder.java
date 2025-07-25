package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalValueHolder;

public interface IMutableOptionalValueHolder<V> extends IOptionalValueHolder<V> {

  void removeValue();

  void setValue(V value);
}
