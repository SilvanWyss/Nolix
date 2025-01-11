package ch.nolix.system.element.multistateconfiguration;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.ValueStoringState;

public abstract class MaterializedProperty<S extends Enum<S>, V> extends AbstractProperty<S> {

  private static final String NONE_HEADER = "None";

  private static final IStringTool STRING_TOOL = new StringTool();

  private final Function<INode<?>, V> valueCreator;

  private final Function<V, INode<?>> specificationCreator;

  private final BiConsumer<S, V> setterMethod;

  protected final StateProperty<V>[] stateProperties;

  @SuppressWarnings("unchecked")
  protected MaterializedProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    super(name);

    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();

    stateProperties = new StateProperty[stateClass.getEnumConstants().length];
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
    setterMethod = null;

    extractStateProperties();
  }

  @SuppressWarnings("unchecked")
  protected MaterializedProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final BiConsumer<S, V> setterMethod) {

    super(name);

    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
    GlobalValidator.assertThat(setterMethod).thatIsNamed("setter method").isNotNull();

    stateProperties = new StateProperty[stateClass.getEnumConstants().length];
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
    this.setterMethod = setterMethod;

    extractStateProperties();
  }

  public final ValueStoringState getAssignmentTypeForState(final S state) {
    return stateProperties[(getStateOf(state).getIndex())].getAssignmentType();
  }

  public final Optional<V> getOptionalValueOfState(final S state) {

    final var stateProperty = stateProperties[getStateOf(state).getIndex()];

    if (!stateProperty.hasValue()) {
      return Optional.empty();
    }

    return Optional.of(stateProperty.getValue());
  }

  public final V getValueOfState(final S state) {
    return stateProperties[getStateOf(state).getIndex()].getValue();
  }

  public final V getValueWhenHasState(final S state) {
    return getValueWhenHasState(getStateOf(state));
  }

  public final boolean hasSetterMethod() {
    return (setterMethod != null);
  }

  public final boolean hasValueForState(final S state) {
    return stateProperties[getStateOf(state).getIndex()].hasValue();
  }

  public final boolean hasValueOrIsEmptyForState(final S state) {
    return stateProperties[getStateOf(state).getIndex()].hasValueOrDefinesEmpty();
  }

  @Override
  public void setUndefined() {
    for (final var sp : stateProperties) {
      sp.setForwarding();
    }
  }

  public void setUndefinedForState(final S state) {
    stateProperties[getStateOf(state).getIndex()].setForwarding();
  }

  public final void setValueForState(final S state, final V value) {
    stateProperties[getStateOf(state).getIndex()].setValue(value);
  }

  @Override
  protected final void fillUpValuesSpecificationInto(final ILinkedList<INode<?>> list) {
    for (final var s : parent.getAvailableStates()) {

      final var stateProperty = stateProperties[s.getIndex()];

      switch (stateProperty.getAssignmentType()) {
        case STORING_VALUE:

          final var valueSpecification = Node.withHeaderAndChildNode(
            s.getQualifyingPrefix() + getName(),
            specificationCreator.apply(stateProperty.getValue()).getStoredSingleChildNode());

          list.addAtEnd(valueSpecification);

          break;
        case DEFINING_EMPTY:

          list.addAtEnd(Node.withHeaderAndChildNode(s.getQualifyingPrefix() + getName(), NONE_HEADER));

          break;
        case FORWARDING:
          break;

      }
    }
  }

  protected final StateProperty<V> getStoredBaseStateProperty() {
    return stateProperties[parent.getBaseStateObject().getIndex()];
  }

  protected final State<S> getStateOf(final S state) {
    return parent.getStateObjectFor(state);
  }

  protected abstract V getValueWhenHasState(State<S> state);

  //mehod declaration
  protected abstract boolean hasValueWhenHasState(State<S> currentStateObject);

  @Override
  @SuppressWarnings("unchecked")
  protected void setFrom(AbstractProperty<S> property) {
    setFrom((MaterializedProperty<S, V>) property);
  }

  @Override
  protected final void setValueFromSpecification(final INode<?> specification) {

    for (final var s : parent.getAvailableStates()) {
      if (STRING_TOOL.startsWithIgnoringCase(specification.getHeader(), s.getQualifyingPrefix())) {
        setValueFromSpecificationToState(s, specification);
        return;
      }
    }

    throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalog.SPECIFICATION, specification);
  }

  private void extractStateProperties() {
    for (var i = 0; i < stateProperties.length; i++) {
      stateProperties[i] = new StateProperty<>();
    }
  }

  private void setFrom(final MaterializedProperty<S, V> materializedProperty) {
    for (var i = 0; i < stateProperties.length; i++) {
      switch (materializedProperty.stateProperties[i].getAssignmentType()) {
        case STORING_VALUE:
          stateProperties[i].setValue(materializedProperty.stateProperties[i].getValue());
          break;
        case DEFINING_EMPTY:
          stateProperties[i].setEmpty();
          break;
        case FORWARDING:
          stateProperties[i].setForwarding();
          break;
      }
    }
  }

  //For a better performance, this implementation does not use all comfortable methods.
  private void setValueForStateUsingSetterMethod(final S state, final V value) {
    if (setterMethod == null) {
      setValueForState(state, value);
    } else {
      setterMethod.accept(state, value);
    }
  }

  private void setValueFromSpecificationToState(final State<S> state, final INode<?> specification) {
    if (specification.getSingleChildNodeHeader().equals(NONE_HEADER)) {
      stateProperties[state.getIndex()].setEmpty();
    } else {
      setValueForStateUsingSetterMethod(state.getEnumValue(), valueCreator.apply(specification));
    }
  }
}
