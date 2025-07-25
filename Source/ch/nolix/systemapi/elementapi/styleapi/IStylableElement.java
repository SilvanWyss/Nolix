package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.attribute.fluentmutablemultiattribute.IFluentMutableMultiTokenHolder;
import ch.nolix.coreapi.attribute.fluentmutableoptionalattribute.IFluentMutableOptionalIdHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.structureapi.typerequestapi.TypeRequestable;
import ch.nolix.systemapi.elementapi.baseapi.IStructureElement;
import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;

/**
 * A {@link IStylableElement} is configurable and can contain other
 * {@link IStylableElement}s.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of a {@link IStylableElement}.
 */
public interface IStylableElement<E extends IStylableElement<E>>
extends
IFluentMutableMultiTokenHolder<E>,
IFluentMutableOptionalIdHolder<E>,
IMutableElement,
IStructureElement,
TypeRequestable {

  /**
   * @return the child {@link IStylableElement}s of the current
   *         {@link IStylableElement}.
   */
  IContainer<? extends IStylableElement<?>> getStoredChildStylableElements();

  /**
   * @param role
   * @return true if the current {@link IStylableElement} has the given role.
   */
  boolean hasRole(String role);

  /**
   * Resets the style of the current {@link IStylableElement} and the style of the
   * child {@link IStylableElement}s of the current {@link IStylableElement}.
   */
  void resetStyleRecursively();
}
