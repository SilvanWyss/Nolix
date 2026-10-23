/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.fractal;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.IntFunction;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.math.bigdecimalmath.IClosedInterval;
import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.systemapi.math.bigdecimalmath.ISequence;

/**
 * @author Silvan Wyss
 */
public interface IFractalBuilder {
  /**
   * @return a new {@link IFractal} from the current {@link IFractalBuilder}
   */
  IFractal build();

  /**
   * @return the max iteration count of the current {@link IFractalBuilder}
   */
  int getMaxIterationCount();

  /**
   * Sets the given colorFunction to the current {@link IFractalBuilder}.
   * 
   * @param colorFunction
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setColorFunction(IntFunction<IColor> colorFunction);

  /**
   * Sets the given decimalPlaces to the current {@link IFractalBuilder}.
   * 
   * @param decimalPlaces
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setDecimalPlaces(int decimalPlaces);

  /**
   * Sets the given heightInPixel to the current {@link IFractalBuilder}.
   * 
   * @param heightInPixel
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setHeightInPixel(int heightInPixel);

  /**
   * Sets the interval with the given min and max as interval of the imaginary
   * components to the current {@link IFractalBuilder}.
   * 
   * @param min
   * @param max
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setImaginaryComponentInterval(double min, double max);

  /**
   * Sets the given imaginaryComponentInterval to the current
   * {@link IFractalBuilder}.
   * 
   * @param imaginaryComponentInterval
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);

  /**
   * Sets the given maxIterationCount to the current {@link IFractalBuilder}.
   * 
   * @param maxIterationCount
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setMaxIterationCount(int maxIterationCount);

  /**
   * Sets the given minMagnitudeForDivergence to the current
   * {@link IFractalBuilder}.
   * 
   * @param minMagnitudeForDivergence
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setMinMagnitudeForDivergence(BigDecimal minMagnitudeForDivergence);

  /**
   * Sets the given minMagnitudeForDivergence to the current
   * {@link IFractalBuilder}.
   * 
   * @param minMagnitudeForDivergence
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setMinMagnitudeForDivergence(double minMagnitudeForDivergence);

  /**
   * Sets the interval with the given min and max as interval of the real
   * components to the current {@link IFractalBuilder}.
   * 
   * @param min
   * @param max
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setRealComponentInterval(double min, double max);

  /**
   * Sets the given realComponentInterval to the current {@link IFractalBuilder}.
   * 
   * @param realComponentInterval
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);

  /**
   * Sets the given sequenceCreator to the current {@link IFractalBuilder}.
   * 
   * @param sequenceCreator
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setSequenceCreator(
    Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator);

  /**
   * Sets the given widthInPixel to the current {@link IFractalBuilder}.
   * 
   * @param widthInPixel
   * @return the current {@link IFractalBuilder}
   */
  IFractalBuilder setWidthInPixel(int widthInPixel);
}
