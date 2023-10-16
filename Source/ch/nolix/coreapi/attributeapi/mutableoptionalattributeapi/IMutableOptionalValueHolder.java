//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalValueHolder;

//interface
public interface IMutableOptionalValueHolder<V> extends IOptionalValueHolder<V> {

  //method declaration
  void removeValue();

  //method declaration
  void setValue(V value);
}
