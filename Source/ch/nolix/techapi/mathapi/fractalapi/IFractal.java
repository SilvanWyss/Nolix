package ch.nolix.techapi.mathapi.fractalapi;

import java.math.BigDecimal;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IClosedInterval;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequence;

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
