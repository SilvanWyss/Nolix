package ch.nolix.techapi.mathapi.fractalapi;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.IntFunction;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IClosedInterval;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequence;

public interface IFractalBuilder {

  IFractal build();

  int getMaxIterationCount();

  IFractalBuilder setDecimalPlaces(int decimalPlaces);

  IFractalBuilder setColorFunction(IntFunction<IColor> colorFunction);

  IFractalBuilder setHeightInPixel(int heightInPixel);

  IFractalBuilder setImaginaryComponentInterval(double min, double max);

  IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);

  IFractalBuilder setMaxIterationCount(int maxIterationCount);

  IFractalBuilder setMinMagnitudeForDivergence(BigDecimal minMagnitudeForDivergence);

  IFractalBuilder setMinMagnitudeForDivergence(double minMagnitudeForDivergence);

  IFractalBuilder setRealComponentInterval(double min, double max);

  IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);

  IFractalBuilder setSequenceCreator(
    Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator);

  IFractalBuilder setWidthInPixel(int widthInPixel);
}
