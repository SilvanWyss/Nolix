/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.gui.box;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.box.Corner;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.gui.location.Location;

/**
 * A {@link CornerShadow} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class CornerShadow extends AbstractElement implements ICornerShadow {
  private static final Corner DEFAULT_CORNER = Corner.BOTTOM_RIGHT;

  public static final Location DEFAULT_LOCATION = Location.OUTSIDE;

  private static final int DEFAULT_SIDE_THICKNESS = 10;

  private static final int DEFAULT_BLUR_RADIUS = 0;

  private static final Color DEFAULT_COLOR = X11ColorCatalog.BLACK;

  private static final String CORNER_HEADER = "Corner";

  private static final String LOCATION_HEADER = "Location";

  private static final String SIDE1_THICKNESS_HEADER = "Side1Thickness";

  private static final String SIDT_THICKNESS_HEADER = "Side2Thickness";

  private static final String BLUR_RADIUS_HEADER = "BlurRadius";

  private static final String COLOR_HEADER = PascalCaseVariableCatalog.COLOR;

  private final Corner corner;

  private final Location location;

  private final int side1Thickness;

  private final int side2Thickness;

  private final int blurRadius;

  private final Color color;

  /**
   * Creates a new {@link CornerShadow} with the given corner, location,
   * side1Thickness, side2Thickness, blurRadius and color.
   * 
   * @param corner
   * @param location
   * @param side1Thickness
   * @param side2Thickness
   * @param blurRadius
   * @param color
   * @throws RuntimeException if the given corner is null.
   * @throws RuntimeException if the given location is null.
   * @throws RuntimeException if the given side1Thickness is negative.
   * @throws RuntimeException if the given side2Thickness is negative.
   * @throws RuntimeException if the given blurRadius is negative.
   * @throws RuntimeException if the given color is null.
   */
  private CornerShadow(
    final Corner corner,
    final Location location,
    final int side1Thickness,
    final int side2Thickness,
    final int blurRadius,
    final IColor color) {
    Validator.assertThat(corner).thatIsNamed(Corner.class).isNotNull();
    Validator.assertThat(location).thatIsNamed(Location.class).isNotNull();
    Validator.assertThat(side1Thickness).thatIsNamed("side 1 thickness").isNotNegative();
    Validator.assertThat(side2Thickness).thatIsNamed("side 2 thickness").isNotNegative();
    Validator.assertThat(blurRadius).thatIsNamed("blur radius").isNotNegative();

    this.corner = corner;
    this.location = location;
    this.side1Thickness = side1Thickness;
    this.side2Thickness = side2Thickness;
    this.blurRadius = blurRadius;
    this.color = Color.fromColor(color);
  }

  /**
   * @param cornerShadow
   * @return a {@link CornerShadow} from the given cornerShadow.
   * @throws RuntimeException if the given cornerShadow is null.
   */
  public static CornerShadow fromCornerShadow(final ICornerShadow cornerShadow) {
    if (cornerShadow instanceof CornerShadow localCornerShadow) {
      return localCornerShadow;
    }

    final var corner = cornerShadow.getCorner();
    final var location = cornerShadow.getLocation();
    final var side1Thickness = cornerShadow.getSide1Thickness();
    final var side2Thickness = cornerShadow.getSide2Thickness();
    final var blurRadius = cornerShadow.getBlurRadius();
    final var color = cornerShadow.getColor();

    return //
    withCornerAndLocationAndSide1ThicknessAnsSide2ThicknessAndBlurRadiusAndColor(
      corner,
      location,
      side1Thickness,
      side2Thickness,
      blurRadius,
      color);
  }

  /**
   * @param specification
   * @return a {@link CornerShadow} from the given specification.
   * @throws RuntimeException if the given specification is not valid.
   */
  public static CornerShadow fromSpecification(final INode<?> specification) {
    var corner = DEFAULT_CORNER;
    var location = DEFAULT_LOCATION;
    var side1Thickness = DEFAULT_SIDE_THICKNESS;
    var side2Thickness = DEFAULT_SIDE_THICKNESS;
    var blurRadius = DEFAULT_BLUR_RADIUS;
    var color = DEFAULT_COLOR;
    final var attributes = specification.getStoredChildNodes();

    for (final var a : attributes) {
      final var header = a.getHeader();

      switch (header) {
        case CORNER_HEADER:
          corner = Corner.fromSpecification(a);
          break;
        case LOCATION_HEADER:
          location = Location.fromSpecification(a);
          break;
        case SIDE1_THICKNESS_HEADER:
          side1Thickness = a.getSingleChildNodeAsInt();
          break;
        case SIDT_THICKNESS_HEADER:
          side2Thickness = a.getSingleChildNodeAsInt();
          break;
        case BLUR_RADIUS_HEADER:
          blurRadius = a.getSingleChildNodeAsInt();
          break;
        case COLOR_HEADER:
          color = Color.fromSpecification(a);
          break;
        default:
          throw InvalidArgumentException.forArgumentAndArgumentName(a, LowerCaseVariableCatalog.ATTRIBUTE);
      }
    }

    return //
    withCornerAndLocationAndSide1ThicknessAnsSide2ThicknessAndBlurRadiusAndColor(
      corner,
      location,
      side1Thickness,
      side2Thickness,
      blurRadius,
      color);
  }

  /**
   * @param corner
   * @param location
   * @param side1Thickness
   * @param side2Thickness
   * @param blurRadius
   * @param color
   * @return a new {@link CornerShadow} with the given corner, location,
   *         side1Thickness, side2Thickness, blurRadius and color.
   * @throws RuntimeException if the given corner is null.
   * @throws RuntimeException if the given location is null.
   * @throws RuntimeException if the given side1Thickness is negative.
   * @throws RuntimeException if the given side2Thickness is negative.
   * @throws RuntimeException if the given blurRadius is negative.
   * @throws RuntimeException if the given color is null.
   */
  public static CornerShadow withCornerAndLocationAndSide1ThicknessAnsSide2ThicknessAndBlurRadiusAndColor(
    final Corner corner,
    final Location location,
    final int side1Thickness,
    final int side2Thickness,
    final int blurRadius,
    final IColor color) {
    return new CornerShadow(corner, location, side1Thickness, side2Thickness, blurRadius, color);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return //
    ArrayList.withElements(
      Node.fromEnum(getCorner()),
      Node.fromEnum(getLocation()),
      Node.withHeaderAndChildNode(SIDE1_THICKNESS_HEADER, getSide1Thickness()),
      Node.withHeaderAndChildNode(SIDT_THICKNESS_HEADER, getSide2Thickness()),
      Node.withHeaderAndChildNode(BLUR_RADIUS_HEADER, getBlurRadius()),
      getColor().getSpecification());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getBlurRadius() {
    return blurRadius;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getColor() {
    return color;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Corner getCorner() {
    return corner;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Location getLocation() {
    return location;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSide1Thickness() {
    return side1Thickness;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSide2Thickness() {
    return side2Thickness;
  }
}
