package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalValueHolder;

public interface IMutableOptionalValueHolder<V> extends IOptionalValueHolder<V> {

  void removeValue();

  void setValue(V value);
}
