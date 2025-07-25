package ch.nolix.system.element.multistateconfiguration;

import java.util.function.BiConsumer;
import java.util.function.Function;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;

public final class CascadingProperty<S extends Enum<S>, V> extends AbstractMaterializedProperty<S, V> {

  private final V defaultValue;

  private CascadingProperty<S, V> parentProperty;

  public CascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final V defaultValue) {

    super(name, stateClass, valueCreator, specificationCreator);

    Validator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableCatalog.DEFAULT_VALUE).isNotNull();

    this.defaultValue = defaultValue;
  }

  public CascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final BiConsumer<S, V> setterMethod,
    final V defaultValue) {

    super(name, stateClass, valueCreator, specificationCreator, setterMethod);

    Validator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableCatalog.DEFAULT_VALUE).isNotNull();

    this.defaultValue = defaultValue;
  }

  public static <S2 extends Enum<S2>> CascadingProperty<S2, Boolean> forBooleanWithNameAndStateClassAndDefaultValue(
    final String name,
    final Class<S2> stateClass,
    final boolean defaultValue) {
    return new CascadingProperty<>(
      name,
      stateClass,
      INode::getSingleChildNodeAsBoolean,
      Node::withChildNode,
      defaultValue);
  }

  public static <S2 extends Enum<S2>> CascadingProperty<S2, Integer>//
  forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
    final String name,
    final Class<S2> stateClass,
    final BiConsumer<S2, Integer> setterMethod,
    final int defaultValue) {
    return new CascadingProperty<>(
      name,
      stateClass,
      INode::getSingleChildNodeAsInt,
      Node::withChildNode,
      setterMethod,
      defaultValue);
  }

  @Override
  protected V getValueWhenHasState(final State<S> state) {

    final var stateProperty = stateProperties[state.getIndex()];
    if (stateProperty.hasValueOrDefinesEmpty()) {
      return stateProperty.getValue();
    }

    final var baseStateProperty = getStoredBaseStateProperty();
    if (baseStateProperty.hasValueOrDefinesEmpty()) {
      return baseStateProperty.getValue();
    }

    if (hasParentProperty()) {
      return parentProperty.getValueWhenHasState(state);
    }

    return defaultValue;
  }

  @Override
  protected boolean hasValueWhenHasState(final State<S> state) {

    final var stateProperty = stateProperties[state.getIndex()];
    if (stateProperty.hasValueOrDefinesEmpty()) {
      return stateProperty.hasValue();
    }

    final var baseStateProperty = getStoredBaseStateProperty();
    if (baseStateProperty.hasValueOrDefinesEmpty()) {
      return baseStateProperty.hasValue();
    }

    return hasParentProperty() && parentProperty.hasValueWhenHasState(state);
  }

  @SuppressWarnings("unchecked")
  void setParentProperty(final CascadingProperty<S, ?> parentProperty) {

    Validator.assertThat(parentProperty).thatIsNamed("parent property").isNotNull();

    this.parentProperty = (CascadingProperty<S, V>) parentProperty;
  }

  private boolean hasParentProperty() {
    return (parentProperty != null);
  }
}
