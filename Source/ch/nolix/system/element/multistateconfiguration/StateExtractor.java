//package declaration
package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
final class StateExtractor<S extends Enum<S>> {

  // method
  public IContainer<State<S>> createStatesFromStateClass(final Class<S> stateClass) {
    return createStatesFromStateEnumValues(stateClass.getEnumConstants());
  }

  // method
  @SuppressWarnings("unchecked")
  public IContainer<State<S>> createtStatesFromState(final S state) {
    return createStatesFromStateClass((Class<S>) state.getClass());
  }

  // method
  private IContainer<State<S>> createStatesFromStateEnumValues(final S[] stateEnumValues) {

    final var states = new LinkedList<State<S>>();

    var i = 0;
    for (final var sev : stateEnumValues) {

      states.addAtEnd(State.withQualifyingPrefixAndIndexAndEnumValue(sev.name(), i, sev));

      i++;
    }

    return states;
  }
}
