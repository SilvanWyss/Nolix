/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.fractal;

import java.math.BigDecimal;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IMutableImage;
import ch.nolix.systemapi.math.bigdecimalmath.IClosedInterval;
import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.systemapi.math.bigdecimalmath.ISequence;

/**
 * @author Silvan Wyss
 */
public interface IFractal {
  /**
   * @param complexNumber
   * @return a new {@link ISequence} of the current {@link IFractal} for the given
   *         complexNumber
   */
  ISequence<IComplexNumber> createSequenceFor(IComplexNumber complexNumber);

  /**
   * @param iterationCount
   * @return the color of the current {@link IFractal} for the given
   *         iterationCount where the magnitude of the value exceeds the max
   *         magnitude
   */
  IColor getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(int iterationCount);

  /**
   * @return the number of decimal places of the current {@link IFractal}
   */
  int getDecimalPlaces();

  /**
   * @return the height of the current {@link IFractal} in pixel
   */
  int getHeightInPixel();

  /**
   * @return the interval of the imaginary components of the current
   *         {@link IFractal}
   */
  IClosedInterval getImaginaryComponentInterval();

  /**
   * @return the max iteration count of the current {@link IFractal}
   */
  int getMaxIterationCount();

  /**
   * @return the min magnitude for divergence of the current {@link IFractal}
   */
  BigDecimal getMinMagnitudeForDivergence();

  /**
   * @return the interval of the real components of the current {@link IFractal}
   */
  IClosedInterval getRealComponentInterval();

  /**
   * @return the width of the current {@link IFractal} in pixel
   */
  int getWidthInPixel();

  /**
   * Starts the generation of the image of the current {@link IFractal}.
   * 
   * @return the {@link IImageGenerator} that generates the image of the current
   *         {@link IFractal}
   */
  IImageGenerator startImageGeneration();

  /**
   * @return a new image of the current {@link IFractal}
   */
  IMutableImage<?> toImage();
}
