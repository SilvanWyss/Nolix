package ch.nolix.systemapi.elementapi.multistateconfigurationapi;

import ch.nolix.systemapi.elementapi.mutableelementapi.IRespondingMutableElement;

public interface IMultiStateConfiguration<C extends IMultiStateConfiguration<C, S>, S extends Enum<S>>
extends IRespondingMutableElement<C> {

  S getBaseState();
}
