/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.graphic.color;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.graphicproperty.Direction;

/**
 * A {@link ColorGradient} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class ColorGradient extends AbstractElement implements IColorGradient {
  public static final Direction DEFAULT_DIRECTION = Direction.VERTICAL;

  public static final Color DEFAULT_COLOR1 = X11ColorCatalog.BLACK;

  public static final Color DEFAULT_COLOR2 = X11ColorCatalog.WHITE;

  private final Direction direction;

  private final Color color1;

  private final Color color2;

  /**
   * Creates a new {@link ColorGradient} with the given direction, color1 and
   * color2.
   * 
   * @param direction
   * @param color1
   * @param color2
   * @throws RuntimeException if the given direction is null
   * @throws RuntimeException if the given color 1 is null
   * @throws RuntimeException if the given color 2 is null
   */
  private ColorGradient(final Direction direction, final Color color1, final Color color2) {
    Validator.assertThat(direction).thatIsNamed("direction").isNotNull();
    Validator.assertThat(color1).thatIsNamed("color1").isNotNull();
    Validator.assertThat(color2).thatIsNamed("color2").isNotNull();

    this.direction = direction;
    this.color1 = color1;
    this.color2 = color2;
  }

  /**
   * @param specification
   * @return a new {@link ColorGradient} from the given specification
   * @throws RuntimeException if the given specification is not valid
   */
  public static ColorGradient fromSpecification(final Node<?> specification) {
    final var attributes = specification.getStoredChildNodes();
    final var attributeCount = attributes.getCount();

    return switch (attributeCount) {
      case 2 ->
        from2Attributes(attributes);
      case 3 ->
        from3Attributes(attributes);
      default ->
        throw //
        InvalidArgumentException.forArgumentAndArgumentName(specification, LowerCaseVariableNameCatalog.SPECIFICATION);
    };
  }

  /**
   * @param color1
   * @param color2
   * @return a new {@link ColorGradient} with the given color1 and color2
   * @throws RuntimeException if the given color1 is null
   * @throws RuntimeException if the given color2 is null
   */
  public static ColorGradient withColors(final Color color1, final Color color2) {
    return new ColorGradient(DEFAULT_DIRECTION, color1, color2);
  }

  /**
   * @param direction
   * @param color1
   * @param color2
   * @return a new {@link ColorGradient} with the given direction, color1 and
   *         color2
   * @throws RuntimeException if the given direction is null
   * @throws RuntimeException if the given color 1 is null
   * @throws RuntimeException if the given color 2 is null
   */
  public static ColorGradient withDirectionAndColors(
    final Direction direction,
    final Color color1,
    final Color color2) {
    return new ColorGradient(direction, color1, color2);
  }

  /**
   * @param attributes
   * @return a new {@link ColorGradient} from the given attributes
   * @throws RuntimeException if the given attributes are not valid
   */
  private static ColorGradient from2Attributes(ExtendedIterable<? extends Node<?>> attributes) {
    final var color1Specification = ImmutableNode.withChildNode(attributes.getStoredAtOneBasedIndex(1));
    final var color2Specification = ImmutableNode.withChildNode(attributes.getStoredAtOneBasedIndex(2));

    return //
    withColors(
      Color.fromSpecification(color1Specification),
      Color.fromSpecification(color2Specification));
  }

  /**
   * @param attributes
   * @return a new {@link ColorGradient} from the given attributes
   * @throws RuntimeException if the given attributes are not valid
   */
  private static ColorGradient from3Attributes(ExtendedIterable<? extends Node<?>> attributes) {
    final var directionSpecification = ImmutableNode.withChildNode(attributes.getStoredAtOneBasedIndex(1));
    final var color1Specification = ImmutableNode.withChildNode(attributes.getStoredAtOneBasedIndex(2));
    final var color2Specification = ImmutableNode.withChildNode(attributes.getStoredAtOneBasedIndex(3));

    return //
    new ColorGradient(
      Direction.fromSpecification(directionSpecification),
      Color.fromSpecification(color1Specification),
      Color.fromSpecification(color2Specification));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> getAttributes() {
    return LinkedList.withElement(
      ImmutableNode.withHeader(getDirection().toString()),
      ImmutableNode.withHeader(getColor1().toHexadecimalString()),
      ImmutableNode.withHeader(getColor2().toHexadecimalString()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getColor1() {
    return color1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getColor2() {
    return color2;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Direction getDirection() {
    return direction;
  }
}
