package ch.nolix.tech.math.fractal;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.IntFunction;

import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.tech.math.bigdecimalmath.ClosedInterval;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IClosedInterval;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequence;
import ch.nolix.techapi.mathapi.fractalapi.IFractalBuilder;

public final class FractalBuilder implements IFractalBuilder {

  public static final IClosedInterval DEFAULT_REAL_COMPONENT_INTERVAL = new ClosedInterval(-2.5, 1.0);

  public static final IClosedInterval DEFAULT_IMAGINARY_COMPONENT_INTERVAL = new ClosedInterval(-1.5, 1.5);

  public static final int DEFAULT_WIDHT_IN_PIXEL = 500;

  public static final int DEFAULT_HEIGHT_IN_PIXEL = DEFAULT_WIDHT_IN_PIXEL;

  public static final Function<IComplexNumber, ISequence<IComplexNumber>> DEFAULT_SEQUENCE_CREATOR = //
  z -> ComplexSequenceDefinedBy1Predecessor.withFirstValueAndNextValueFunction(z, p -> p.getPower2().getSum(z));

  public static final double DEFAULT_SEQUENCES_MIN_DIVERGENCE_MAGNITUDE = 10.0;

  public static final int DEFAULT_SEQUENCE_MAX_ITERATION_COUNT = 50;

  public static final IntFunction<IColor> DEFAULT_COLOR_FUNCTION = i -> Color
    .withRedValueAndGreenValueAndBlueValue(0, 0, (10 * i) % Color.MAX_COLOR_COMPONENT);

  public static final int DEFAULT_BIG_DECIMAL_SCALE = 10;

  private IClosedInterval realComponentInterval = DEFAULT_REAL_COMPONENT_INTERVAL;

  private IClosedInterval imaginaryComponentInterval = DEFAULT_IMAGINARY_COMPONENT_INTERVAL;

  private int widthInPixel = DEFAULT_WIDHT_IN_PIXEL;

  private int heightInPixel = DEFAULT_HEIGHT_IN_PIXEL;

  private Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator = //
  DEFAULT_SEQUENCE_CREATOR;

  private BigDecimal sequencesMinDivergenceMagnitude = BigDecimal.valueOf(DEFAULT_SEQUENCES_MIN_DIVERGENCE_MAGNITUDE);

  private int sequencesMaxIterationCount = DEFAULT_SEQUENCE_MAX_ITERATION_COUNT;

  private IntFunction<IColor> colorFunction = DEFAULT_COLOR_FUNCTION;

  private int decimalPlaces = DEFAULT_BIG_DECIMAL_SCALE;

  @Override
  public Fractal build() {
    return new Fractal(
      realComponentInterval,
      imaginaryComponentInterval,
      widthInPixel,
      heightInPixel,
      sequenceCreator,
      sequencesMinDivergenceMagnitude,
      sequencesMaxIterationCount,
      colorFunction,
      decimalPlaces);
  }

  @Override
  public int getMaxIterationCount() {
    return sequencesMaxIterationCount;
  }

  @Override
  public IFractalBuilder setDecimalPlaces(final int decimalPlaces) {

    this.decimalPlaces = decimalPlaces;

    return this;
  }

  @Override
  public IFractalBuilder setColorFunction(final IntFunction<IColor> colorFunction) {

    this.colorFunction = colorFunction;

    return this;
  }

  @Override
  public IFractalBuilder setHeightInPixel(final int heightInPixel) {

    this.heightInPixel = heightInPixel;

    return this;
  }

  @Override
  public IFractalBuilder setImaginaryComponentInterval(final double min, final double max) {
    return setImaginaryComponentInterval(new ClosedInterval(min, max));
  }

  @Override
  public IFractalBuilder setImaginaryComponentInterval(final IClosedInterval imaginaryComponentInterval) {

    this.imaginaryComponentInterval = imaginaryComponentInterval;

    return this;
  }

  @Override
  public IFractalBuilder setRealComponentInterval(final double min, final double max) {
    return setRealComponentInterval(new ClosedInterval(min, max));
  }

  @Override
  public IFractalBuilder setRealComponentInterval(final IClosedInterval realComponentInterval) {

    this.realComponentInterval = realComponentInterval;

    return this;
  }

  @Override
  public IFractalBuilder setMaxIterationCount(final int sequencesMaxIterationCount) {

    this.sequencesMaxIterationCount = sequencesMaxIterationCount;

    return this;
  }

  @Override
  public IFractalBuilder setMinMagnitudeForDivergence(final BigDecimal sequencesMinDivergenceMagnitude) {

    this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude;

    return this;
  }

  @Override
  public IFractalBuilder setMinMagnitudeForDivergence(final double minMagnitudeForDivergence) {
    return setMinMagnitudeForDivergence(BigDecimal.valueOf(minMagnitudeForDivergence));
  }

  @Override
  public IFractalBuilder setSequenceCreator(
    final Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator) {

    this.sequenceCreator = sequenceCreator;

    return this;
  }

  @Override
  public IFractalBuilder setWidthInPixel(final int widthInPixel) {

    this.widthInPixel = widthInPixel;

    return this;
  }
}
