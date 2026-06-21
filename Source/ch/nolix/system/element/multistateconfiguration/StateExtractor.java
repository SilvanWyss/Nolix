/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

final class StateExtractor<S extends Enum<S>> {
  public ExtendedIterable<State<S>> createStatesFromStateClass(final Class<S> stateClass) {
    return createStatesFromStateEnumValues(stateClass.getEnumConstants());
  }

  @SuppressWarnings("unchecked")
  public ExtendedIterable<State<S>> createtStatesFromState(final S state) {
    return createStatesFromStateClass((Class<S>) state.getClass());
  }

  private ExtendedIterable<State<S>> createStatesFromStateEnumValues(final S[] stateEnumValues) {
    final ILinkedList<State<S>> states = LinkedList.createEmpty();

    var i = 0;
    for (final var v : stateEnumValues) {
      states.addAtEnd(State.withQualifyingPrefixAndIndexAndEnumValue(v.name(), i, v));

      i++;
    }

    return states;
  }
}
