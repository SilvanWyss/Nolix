//package declaration
package ch.nolix.techapi.mathapi.fractalapi;

//Java imports
import java.math.BigDecimal;
import java.util.function.Function;

import ch.nolix.coreapi.functionapi.genericfunctionapi.IIntTakerElementGetter;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IClosedInterval;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequence;

//interface
public interface IFractalBuilder {

  // method declaration
  IFractal build();

  // method declaration
  int getMaxIterationCount();

  // method declaration
  IFractalBuilder setBigDecimalScale(int bigDecumalScale);

  // method declaration
  IFractalBuilder setColorFunction(IIntTakerElementGetter<IColor> colorFunction);

  // method declaration
  IFractalBuilder setHeightInPixel(final int heightInPixel);

  // method declaration
  IFractalBuilder setImaginaryComponentInterval(double min, double max);

  // method declaration
  IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);

  // method declaration
  IFractalBuilder setMaxIterationCount(int maxIterationCount);

  // method declaration
  IFractalBuilder setMinMagnitudeForDivergence(BigDecimal minMagnitudeForDivergence);

  // method declaration
  IFractalBuilder setMinMagnitudeForDivergence(double minMagnitudeForDivergence);

  // method declaration
  IFractalBuilder setRealComponentInterval(double min, double max);

  // method declaration
  IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);

  // method declaration
  IFractalBuilder setSequenceCreator(
      Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator);

  // method declaration
  IFractalBuilder setWidthInPixel(int widthInPixel);
}
