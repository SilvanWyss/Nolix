/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.gui.cssmapper;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.gui.cssmapper.ICssValueMapper;

/**
 * Of the {@link CornerShadowToCssMapperHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class CornerShadowToCssMapperHelper {
  private static final ICssValueMapper CSS_VALUE_MAPPER = new CssValueMapper();

  /**
   * Prevents that an instance of the {@link CornerShadowToCssMapperHelper} can be
   * created.
   */
  private CornerShadowToCssMapperHelper() {
  }

  /**
   * @param cornerShadow
   * @return a new {@link ICssProperty} from the given cornerShadow when the
   *         shadow is inside the box.
   * @throws RuntimeException if the given cornerShadow is null.
   */
  public static ICssProperty mapCornerShadowToCssPropertyWhenShadowIsInsideTheBox(final ICornerShadow cornerShadow) {
    final var side1Thickness = cornerShadow.getSide1Thickness();
    final var side2Thickness = cornerShadow.getSide2Thickness();
    final var blurRadius = cornerShadow.getBlurRadius();
    final var color = cornerShadow.getColor();
    final var colorCssValue = CSS_VALUE_MAPPER.mapColorToCssValue(color);

    return //
    switch (cornerShadow.getCorner()) {
      case BOTTOM_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", side1Thickness, -side2Thickness, blurRadius, colorCssValue));
      case BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", -side1Thickness, -side2Thickness, blurRadius, colorCssValue));
      case TOP_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", side1Thickness, side2Thickness, blurRadius, colorCssValue));
      case TOP_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s inset", -side1Thickness, side2Thickness, blurRadius, colorCssValue));
    };
  }

  /**
   * @param cornerShadow
   * @return a new {@link ICssProperty} from the given cornerShadow when the
   *         shadow is outside the box.
   * @throws RuntimeException if the given cornerShadow is null.
   */
  public static ICssProperty mapCornerShadowToCssPropertyWhenShadowIsOutsideTheBox(final ICornerShadow cornerShadow) {
    final var side1Thickness = cornerShadow.getSide1Thickness();
    final var side2Thickness = cornerShadow.getSide2Thickness();
    final var blurRadius = cornerShadow.getBlurRadius();
    final var color = cornerShadow.getColor();
    final var colorCssValue = CSS_VALUE_MAPPER.mapColorToCssValue(color);

    return //
    switch (cornerShadow.getCorner()) {
      case BOTTOM_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", -side1Thickness, side2Thickness, blurRadius, colorCssValue));
      case BOTTOM_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", side1Thickness, side2Thickness, blurRadius, colorCssValue));
      case TOP_LEFT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", -side1Thickness, -side2Thickness, blurRadius, colorCssValue));
      case TOP_RIGHT ->
        CssProperty.withNameAndValue(
          CssPropertyNameCatalog.BOX_SHADOW,
          String.format("%dpx %dpx %dpx %s", side1Thickness, -side2Thickness, blurRadius, colorCssValue));
    };
  }
}
