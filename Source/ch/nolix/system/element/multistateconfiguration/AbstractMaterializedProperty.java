package ch.nolix.system.element.multistateconfiguration;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import ch.nolix.core.commontypetool.stringtool.StringExaminer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.IStringExaminer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.element.multistateconfiguration.ValueStoringState;

public abstract class AbstractMaterializedProperty<S extends Enum<S>, V> extends AbstractProperty<S> {
  private static final String NONE_HEADER = "None";

  private static final IStringExaminer STRING_EXAMINER = new StringExaminer();

  private final Function<INode<?>, V> valueCreator;

  private final Function<V, INode<?>> specificationCreator;

  private final BiConsumer<S, V> setterMethod;

  protected final StateProperty<V>[] stateProperties;

  @SuppressWarnings("unchecked")
  protected AbstractMaterializedProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {
    super(name);

    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();

    stateProperties = new StateProperty[stateClass.getEnumConstants().length];
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
    setterMethod = null;

    extractStateProperties();
  }

  @SuppressWarnings("unchecked")
  protected AbstractMaterializedProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final BiConsumer<S, V> setterMethod) {
    super(name);

    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
    Validator.assertThat(setterMethod).thatIsNamed("setter method").isNotNull();

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
    for (final var p : stateProperties) {
      p.setForwarding();
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
      final var assignmentType = stateProperty.getAssignmentType();

      switch (assignmentType) {
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
        default:
          throw InvalidArgumentException.forArgument(assignmentType);
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
    setFrom((AbstractMaterializedProperty<S, V>) property);
  }

  @Override
  protected final void setValueFromSpecification(final INode<?> specification) {
    for (final var s : parent.getAvailableStates()) {
      if (STRING_EXAMINER.startsWithIgnoringCase(specification.getHeader(), s.getQualifyingPrefix())) {
        setValueFromSpecificationToState(s, specification);
        return;
      }
    }

    throw InvalidArgumentException.forArgumentAndArgumentName(specification, LowerCaseVariableCatalog.SPECIFICATION);
  }

  private void extractStateProperties() {
    for (var i = 0; i < stateProperties.length; i++) {
      stateProperties[i] = StateProperty.createUndefinedStateProperty();
    }
  }

  private void setFrom(final AbstractMaterializedProperty<S, V> materializedProperty) {
    for (var i = 0; i < stateProperties.length; i++) {
      final var assignemntType = materializedProperty.stateProperties[i].getAssignmentType();

      switch (assignemntType) {
        case STORING_VALUE:
          stateProperties[i].setValue(materializedProperty.stateProperties[i].getValue());
          break;
        case DEFINING_EMPTY:
          stateProperties[i].setEmpty();
          break;
        case FORWARDING:
          stateProperties[i].setForwarding();
          break;
        default:
          throw InvalidArgumentException.forArgument(assignemntType);
      }
    }
  }

  //For a better performance, this implementation does not use all available comfort methods.
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
