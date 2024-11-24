package ch.nolix.system.graphic.color;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.element.base.Element;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.canvasapi.DirectionInCanvas;

/**
 * A {@link ColorGradient} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-08-01
 */
public final class ColorGradient extends Element implements IColorGradient {

  public static final ColorGradient VERTICAL_BLACK_WHITE_COLOR_GRADIENT = new ColorGradient(
    DirectionInCanvas.VERTICAL, Color.BLACK, Color.WHITE);

  public static final ColorGradient VERTICAL_RED_WHITE_COLOR_GRADIENT = new ColorGradient(DirectionInCanvas.VERTICAL,
    Color.RED, Color.WHITE);

  public static final DirectionInCanvas DEFAULT_DIRECTION = DirectionInCanvas.VERTICAL;

  public static final Color DEFAULT_COLOR1 = Color.BLACK;

  public static final Color DEFAULT_COLOR2 = Color.WHITE;

  private final DirectionInCanvas direction;

  private final Color color1;

  private final Color color2;

  /**
   * Creates a new {@link ColorGradient} with the given direction.
   * 
   * @param direction
   * @throws ArgumentIsNullException if the given direction is null.
   */
  public ColorGradient(final DirectionInCanvas direction) {

    //Calls other constructor.
    this(direction, DEFAULT_COLOR1, DEFAULT_COLOR2);
  }

  /**
   * Creates a new {@link ColorGradient} with the given direction, color 1 and
   * color 2.
   * 
   * @param direction
   * @param color1
   * @param color2
   * @throws ArgumentIsNullException if the given direction is null.
   * @throws ArgumentIsNullException if the given color 1 is null.
   * @throws ArgumentIsNullException if the given color 2 is null.
   */
  public ColorGradient(final DirectionInCanvas direction, final Color color1, final Color color2) {

    //Asserts that the given direction is not null.
    GlobalValidator.assertThat(direction).thatIsNamed("direction").isNotNull();

    //Asserts that the given color1 is not null.
    GlobalValidator.assertThat(color1).thatIsNamed("color1").isNotNull();

    //Asserts that the given color2 is not null.
    GlobalValidator.assertThat(color2).thatIsNamed("color2").isNotNull();

    this.direction = direction;
    this.color1 = color1;
    this.color2 = color2;
  }

  /**
   * @param specification
   * @return a new {@link ColorGradient} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static ColorGradient fromSpecification(final INode<?> specification) {

    final var attributes = specification.getStoredChildNodes();

    return switch (attributes.getCount()) {
      case 2 ->
        from2Attributes(attributes);
      case 3 ->
        from3Attributes(attributes);
      default ->
        throw //
        InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.SPECIFICATION, specification);
    };
  }

  /**
   * @param color1
   * @param color2
   * @return a new {@link ColorGradient} with the given color1 and color2.
   * @throws ArgumentIsNullException if the given color1 is null.
   * @throws ArgumentIsNullException if the given color2 is null.
   */
  public static ColorGradient withColors(final Color color1, final Color color2) {
    return new ColorGradient(DEFAULT_DIRECTION, color1, color2);
  }

  /**
   * @param attributes
   * @return a new {@link ColorGradient} from the given attributes.
   * @throws InvalidArgumentException if the given attributes are not valid.
   */
  private static ColorGradient from2Attributes(IContainer<? extends INode<?>> attributes) {

    final var color1Specification = Node.withChildNode(attributes.getStoredAt1BasedIndex(1));
    final var color2Specification = Node.withChildNode(attributes.getStoredAt1BasedIndex(2));

    return //
    withColors(
      Color.fromSpecification(color1Specification),
      Color.fromSpecification(color2Specification));
  }

  /**
   * @param attributes
   * @return a new {@link ColorGradient} from the given attributes.
   * @throws InvalidArgumentException if the given attributes are not valid.
   */
  private static ColorGradient from3Attributes(IContainer<? extends INode<?>> attributes) {

    final var directionSpecification = Node.withChildNode(attributes.getStoredAt1BasedIndex(1));
    final var color1Specification = Node.withChildNode(attributes.getStoredAt1BasedIndex(2));
    final var color2Specification = Node.withChildNode(attributes.getStoredAt1BasedIndex(3));

    return new ColorGradient(
      DirectionInCanvas.fromSpecification(directionSpecification),
      Color.fromSpecification(color1Specification),
      Color.fromSpecification(color2Specification));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return LinkedList.withElement(
      Node.withHeader(getDirection().toString()),
      Node.withHeader(getColor1().toHexadecimalString()),
      Node.withHeader(getColor2().toHexadecimalString()));
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
  public DirectionInCanvas getDirection() {
    return direction;
  }
}
