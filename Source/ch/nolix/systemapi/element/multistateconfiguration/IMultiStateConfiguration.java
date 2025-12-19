package ch.nolix.systemapi.element.multistateconfiguration;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;

/**
 * @author Silvan Wyss
 */
public interface IMultiStateConfiguration<C extends IMultiStateConfiguration<C, S>, S extends Enum<S>>
extends IRespondingMutableElement<C> {
  S getBaseState();
}
