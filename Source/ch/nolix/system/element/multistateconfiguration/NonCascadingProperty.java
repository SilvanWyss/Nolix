/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.multistateconfiguration;

import java.util.function.BiConsumer;
import java.util.function.Function;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the states of a {@link NonCascadingProperty}.
 * @param <V> the type of the values of a {@link NonCascadingProperty}.
 */
public final class NonCascadingProperty<S extends Enum<S>, V> extends AbstractMaterializedProperty<S, V> {
  private final V defaultValue;

  private NonCascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<Node<?>, V> valueCreator,
    final Function<V, Node<?>> specificationCreator,
    final V defaultValue) {
    super(name, stateClass, valueCreator, specificationCreator);

    Validator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableNameCatalog.DEFAULT_VALUE).isNotNull();

    this.defaultValue = defaultValue;
  }

  private NonCascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<Node<?>, V> valueCreator,
    final Function<V, Node<?>> specificationCreator,
    final BiConsumer<S, V> setterMethod,
    final V defaultValue) {
    super(name, stateClass, valueCreator, specificationCreator, setterMethod);

    Validator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableNameCatalog.DEFAULT_VALUE).isNotNull();

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
      Node::getSingleChildNodeAsDouble,
      ImmutableNode::withChildNode,
      setterMethod,
      defaultValue);
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
      Node::getSingleChildNodeAsInt,
      ImmutableNode::withChildNode,
      setterMethod,
      defaultValue);
  }

  public static <S2 extends Enum<S2>, V2> NonCascadingProperty<S2, V2> //
  withNameAndStateClassAndValueMapperAndSpecificationMapperAndDefaultValue(
    final String name,
    final Class<S2> stateClass,
    final Function<Node<?>, V2> valueCreator,
    final Function<V2, Node<?>> specificationCreator,
    final V2 defaultValue) {
    return new NonCascadingProperty<>(name, stateClass, valueCreator, specificationCreator, defaultValue);
  }

  public static <S2 extends Enum<S2>, V2> NonCascadingProperty<S2, V2> //
  withNameAndStateClassAndValueMapperAndSpecificationMapperAndSetterAndDefaultValue(
    final String name,
    final Class<S2> stateClass,
    final Function<Node<?>, V2> valueCreator,
    final Function<V2, Node<?>> specificationCreator,
    final BiConsumer<S2, V2> setterMethod,
    final V2 defaultValue) {
    return new NonCascadingProperty<>(name, stateClass, valueCreator, specificationCreator, setterMethod, defaultValue);
  }

  public void setEmptyForState(final S state) {
    stateProperties[(getStateOf(state).getIndex())].setEmpty();
  }

  /**
   * {@inheritDoc}
   */
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

    return defaultValue;
  }

  /**
   * {@inheritDoc}
   */
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
