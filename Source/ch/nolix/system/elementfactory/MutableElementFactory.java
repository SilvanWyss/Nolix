//package declaration
package ch.nolix.system.elementfactory;

import java.util.function.Function;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainapi.IMutableElement;

//class
public final class MutableElementFactory<E> extends ElementFactory<E> {

  //method
  public <ME extends IMutableElement> MutableElementFactory<E> registerElementClass(
      final Class<ME> elementClass) {

    registerElementClass_(elementClass);

    return this;
  }

  //method
  public MutableElementFactory<E> registerElementClass(
      final Class<E> elementClass,
      final Function<INode<?>, E> creator) {

    registerElementClass_(elementClass, creator);

    return this;
  }
}
