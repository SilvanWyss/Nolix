package ch.nolix.techapi.math.fractal;

import java.math.BigDecimal;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IMutableImage;
import ch.nolix.techapi.math.bigdecimalmath.IClosedInterval;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.techapi.math.bigdecimalmath.ISequence;

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
