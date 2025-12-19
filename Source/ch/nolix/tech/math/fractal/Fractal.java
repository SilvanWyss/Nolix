package ch.nolix.tech.math.fractal;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.IntFunction;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.techapi.math.bigdecimalmath.IClosedInterval;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.techapi.math.bigdecimalmath.ISequence;
import ch.nolix.techapi.math.fractal.IFractal;

/**
 * @author Silvan Wyss
 */
public final class Fractal implements IFractal {
  public static final Color CONVERGENCE_COLOR = X11ColorCatalog.BLACK;

  private final IClosedInterval realComponentInterval;

  private final IClosedInterval imaginaryComponentInterval;

  private final int widthInPixel;

  private final int heightInPixel;

  private final Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator;

  private final BigDecimal sequencesMinDivergenceMagnitude;

  private final int sequencesMaxIterationCount;

  private final IntFunction<IColor> colorFunction;

  private final int decimalPlaces;

  public Fractal( //NOSONAR: A Fractal has many parameters.
    final IClosedInterval realComponentInterval,
    final IClosedInterval imaginaryComponentInterval,
    final int widthInPixel,
    final int heightInPixel,
    final Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator,
    final BigDecimal sequencesMinDivergenceMagnitude,
    final int sequencesMaxIterationCount,
    final IntFunction<IColor> colorFunction,
    final int decimalPlaces) {
    Validator
      .assertThat(realComponentInterval)
      .thatIsNamed("real component interval")
      .isNotNull();

    Validator
      .assertThat(imaginaryComponentInterval)
      .thatIsNamed("imaginary component interval")
      .isNotNull();

    Validator
      .assertThat(widthInPixel)
      .thatIsNamed("width in pixel")
      .isPositive();

    Validator
      .assertThat(heightInPixel)
      .thatIsNamed("height in pixel")
      .isPositive();

    Validator
      .assertThat(sequenceCreator)
      .thatIsNamed("sequence creator")
      .isNotNull();

    Validator
      .assertThat(sequencesMinDivergenceMagnitude)
      .thatIsNamed("sequences min divergence magnitude")
      .isPositive();

    Validator
      .assertThat(sequencesMaxIterationCount)
      .thatIsNamed("sequences max iteration count")
      .isPositive();

    Validator
      .assertThat(colorFunction)
      .thatIsNamed("color function")
      .isNotNull();

    Validator
      .assertThat(decimalPlaces)
      .thatIsNamed("big decimal scale")
      .isPositive();

    this.imaginaryComponentInterval = imaginaryComponentInterval.inDecimalPlaces(decimalPlaces);
    this.realComponentInterval = realComponentInterval.inDecimalPlaces(decimalPlaces);
    this.widthInPixel = widthInPixel;
    this.heightInPixel = heightInPixel;
    this.sequenceCreator = sequenceCreator;
    this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude.setScale(decimalPlaces);
    this.sequencesMaxIterationCount = sequencesMaxIterationCount;
    this.colorFunction = colorFunction;
    this.decimalPlaces = decimalPlaces;
  }

  @Override
  public ISequence<IComplexNumber> createSequenceFor(final IComplexNumber complexNumber) {
    return sequenceCreator.apply(complexNumber);
  }

  @Override
  public int getDecimalPlaces() {
    return decimalPlaces;
  }

  @Override
  public IColor getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(final int iterationCount) {
    if (iterationCount == -1) {
      return CONVERGENCE_COLOR;
    }

    return colorFunction.apply(iterationCount);
  }

  @Override
  public int getHeightInPixel() {
    return heightInPixel;
  }

  @Override
  public IClosedInterval getImaginaryComponentInterval() {
    return imaginaryComponentInterval;
  }

  @Override
  public IClosedInterval getRealComponentInterval() {
    return realComponentInterval;
  }

  @Override
  public int getMaxIterationCount() {
    return sequencesMaxIterationCount;
  }

  @Override
  public BigDecimal getMinMagnitudeForDivergence() {
    return sequencesMinDivergenceMagnitude;
  }

  @Override
  public int getWidthInPixel() {
    return widthInPixel;
  }

  @Override
  public ImageGenerator startImageGeneration() {
    return ImageGenerator.forFractal(this);
  }

  @Override
  public MutableImage toImage() {
    final var imageBuilder = startImageGeneration();
    imageBuilder.waitUntilIsFinishedSuccessfully();

    return imageBuilder.getStoredImage();
  }
}
