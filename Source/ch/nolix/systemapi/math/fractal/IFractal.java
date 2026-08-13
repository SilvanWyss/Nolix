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
  ISequence<IComplexNumber> createSequenceFor(IComplexNumber complexNumber);

  IColor getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(int iterationCount);

  int getDecimalPlaces();

  int getHeightInPixel();

  IClosedInterval getImaginaryComponentInterval();

  int getMaxIterationCount();

  BigDecimal getMinMagnitudeForDivergence();

  IClosedInterval getRealComponentInterval();

  int getWidthInPixel();

  IImageGenerator startImageGeneration();

  IMutableImage<?> toImage();
}
