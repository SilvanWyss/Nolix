package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;

final class StateExtractor<S extends Enum<S>> {
  public IContainer<State<S>> createStatesFromStateClass(final Class<S> stateClass) {
    return createStatesFromStateEnumValues(stateClass.getEnumConstants());
  }

  @SuppressWarnings("unchecked")
  public IContainer<State<S>> createtStatesFromState(final S state) {
    return createStatesFromStateClass((Class<S>) state.getClass());
  }

  private IContainer<State<S>> createStatesFromStateEnumValues(final S[] stateEnumValues) {
    final ILinkedList<State<S>> states = LinkedList.createEmpty();

    var i = 0;
    for (final var sev : stateEnumValues) {
      states.addAtEnd(State.withQualifyingPrefixAndIndexAndEnumValue(sev.name(), i, sev));

      i++;
    }

    return states;
  }
}
