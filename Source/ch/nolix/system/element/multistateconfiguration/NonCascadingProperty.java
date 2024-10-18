package ch.nolix.system.element.multistateconfiguration;

import java.util.function.BiConsumer;
import java.util.function.Function;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class NonCascadingProperty<S extends Enum<S>, V> extends MaterializedProperty<S, V> {

  private final V defaultValue;

  public NonCascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    super(name, stateClass, valueCreator, specificationCreator);

    defaultValue = null;
  }

  public NonCascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final V defaultValue) {

    super(name, stateClass, valueCreator, specificationCreator);

    GlobalValidator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableCatalogue.DEFAULT_VALUE).isNotNull();

    this.defaultValue = defaultValue;
  }

  public NonCascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final BiConsumer<S, V> setterMethod) {

    super(name, stateClass, valueCreator, specificationCreator, setterMethod);

    defaultValue = null;
  }

  public NonCascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final BiConsumer<S, V> setterMethod,
    final V defaultValue) {

    super(name, stateClass, valueCreator, specificationCreator, setterMethod);

    GlobalValidator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableCatalogue.DEFAULT_VALUE).isNotNull();

    this.defaultValue = defaultValue;
  }

  public static <S2 extends Enum<S2>> NonCascadingProperty<S2, Double> //
  forDoubleWithNameAndStateClassAndSetterMethodAndDefaultValue(
    final String name,
    final Class<S2> stateClass,
    final BiConsumer<S2, Double> setterMethod,
    final double defaultValue) {
    return new NonCascadingProperty<>(
      name,
      stateClass,
      INode::getSingleChildNodeAsDouble,
      Node::withChildNode,
      setterMethod,
      defaultValue);
  }

  public static <S2 extends Enum<S2>> NonCascadingProperty<S2, Integer> forIntWithNameAndStateClassAndSetterMethod(
    final String name,
    final Class<S2> stateClass,
    final BiConsumer<S2, Integer> setterMethod) {
    return new NonCascadingProperty //
    <S2, Integer>( //NOSONAR: Gradle fails on diamond operators in this case.
      name,
      stateClass,
      INode::getSingleChildNodeAsInt,
      Node::withChildNode,
      setterMethod);
  }

  public static <S2 extends Enum<S2>> NonCascadingProperty<S2, Integer>//
  forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
    final String name,
    final Class<S2> stateClass,
    final BiConsumer<S2, Integer> setterMethod,
    final int defaultValue) {
    return new NonCascadingProperty<>(
      name,
      stateClass,
      INode::getSingleChildNodeAsInt,
      Node::withChildNode,
      setterMethod,
      defaultValue);
  }

  public boolean hasDefaultValue() {
    return (defaultValue != null);
  }

  public void setEmptyForState(final S state) {
    stateProperties[(getStateOf(state).getIndex())].setEmpty();
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

    if (hasDefaultValue()) {
      return defaultValue;
    }

    throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
      this,
      "value for the" + state.getQualifyingPrefix() + " state");
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

    return false;
  }
}
