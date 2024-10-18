package ch.nolix.systemapi.elementapi.multistateconfigurationapi;

import ch.nolix.systemapi.elementapi.mutableelementapi.IRespondingMutableElement;

public interface IMultiStateConfiguration<MSC extends IMultiStateConfiguration<MSC, S>, S extends Enum<S>>
extends IRespondingMutableElement<MSC> {

  S getBaseState();
}
