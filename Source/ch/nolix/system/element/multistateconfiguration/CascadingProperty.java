//package declaration
package ch.nolix.system.element.multistateconfiguration;

//Java imports
import java.util.function.BiConsumer;
import java.util.function.Function;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class CascadingProperty<S extends Enum<S>, V> extends MaterializedProperty<S, V> {

  //attribute
  private final V defaultValue;

  //optional attribute
  private CascadingProperty<S, V> parentProperty;

  //constructor
  public CascadingProperty(
    final String name,
    final Class<S> stateClass,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator,
    final V defaultValue) {

    super(name, stateClass, valueCreator, specificationCreator);

    GlobalValidator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableCatalogue.DEFAULT_VALUE).isNotNull();

    this.defaultValue = defaultValue;
  }

  //constructor
  public CascadingProperty(
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

  //static method
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

  //static method
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

  //method
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

  //method
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

  //method
  @SuppressWarnings("unchecked")
  void setParentProperty(final CascadingProperty<S, ?> parentProperty) {

    GlobalValidator.assertThat(parentProperty).thatIsNamed("parent property").isNotNull();

    this.parentProperty = (CascadingProperty<S, V>) parentProperty;
  }

  //method
  private boolean hasParentProperty() {
    return (parentProperty != null);
  }
}
