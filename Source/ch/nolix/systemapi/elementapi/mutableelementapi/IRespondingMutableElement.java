package ch.nolix.systemapi.elementapi.mutableelementapi;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 * @version 2021-04-01
 * @param <E> is the type of a {@link IRespondingMutableElement}.
 */
public interface IRespondingMutableElement<E extends IRespondingMutableElement<E>> extends IMutableElement {

  /**
   * Adds or changes the given attribute to the current
   * {@link IRespondingMutableElement} if the given attributes matches.
   * 
   * @param attribute
   * @return true if the given attribute was added or changed to the current
   *         {@link IRespondingMutableElement}.
   */
  boolean addedOrChangedAttribute(INode<?> attribute);
}
