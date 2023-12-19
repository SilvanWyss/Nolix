//package declaration
package ch.nolix.tech.math.fractal;

//Java imports
import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.IntFunction;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IClosedInterval;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.ISequence;
import ch.nolix.techapi.mathapi.fractalapi.IFractal;

//class
public final class Fractal implements IFractal {

  //constant
  public static final Color CONVERGENCE_COLOR = Color.BLACK;

  //attribute
  private final IClosedInterval realComponentInterval;

  //attribute
  private final IClosedInterval imaginaryComponentInterval;

  //attribute
  private final int widthInPixel;

  //attribute
  private final int heightInPixel;

  //attribute
  private final Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator;

  //attribute
  private final BigDecimal sequencesMinDivergenceMagnitude;

  //attribute
  private final int sequencesMaxIterationCount;

  //attribute
  private final IntFunction<IColor> colorFunction;

  //attribute
  private final int bigDecimalScale;

  //constructor
  public Fractal( //NOSONAR: A Fractal has many parameters and therefore a FractalBuilder fills
    //them up.
    final IClosedInterval realComponentInterval,
    final IClosedInterval imaginaryComponentInterval,
    final int widthInPixel,
    final int heightInPixel,
    final Function<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator,
    final BigDecimal sequencesMinDivergenceMagnitude,
    final int sequencesMaxIterationCount,
    final IntFunction<IColor> colorFunction,
    final int bigDecimalScale) {

    GlobalValidator
      .assertThat(realComponentInterval)
      .thatIsNamed("real component interval")
      .isNotNull();

    GlobalValidator
      .assertThat(imaginaryComponentInterval)
      .thatIsNamed("imaginary component interval")
      .isNotNull();

    GlobalValidator
      .assertThat(widthInPixel)
      .thatIsNamed("width in pixel")
      .isPositive();

    GlobalValidator
      .assertThat(heightInPixel)
      .thatIsNamed("height in pixel")
      .isPositive();

    GlobalValidator
      .assertThat(sequenceCreator)
      .thatIsNamed("sequence creator")
      .isNotNull();

    GlobalValidator
      .assertThat(sequencesMinDivergenceMagnitude)
      .thatIsNamed("sequences min divergence magnitude")
      .isPositive();

    GlobalValidator
      .assertThat(sequencesMaxIterationCount)
      .thatIsNamed("sequences max iteration count")
      .isPositive();

    GlobalValidator
      .assertThat(colorFunction)
      .thatIsNamed("color function")
      .isNotNull();

    GlobalValidator
      .assertThat(bigDecimalScale)
      .thatIsNamed("big decimal scale")
      .isPositive();

    this.imaginaryComponentInterval = imaginaryComponentInterval.inBigDecimalScale(bigDecimalScale);
    this.realComponentInterval = realComponentInterval.inBigDecimalScale(bigDecimalScale);
    this.widthInPixel = widthInPixel;
    this.heightInPixel = heightInPixel;
    this.sequenceCreator = sequenceCreator;
    this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude.setScale(bigDecimalScale);
    this.sequencesMaxIterationCount = sequencesMaxIterationCount;
    this.colorFunction = colorFunction;
    this.bigDecimalScale = bigDecimalScale;
  }

  //method
  @Override
  public ISequence<IComplexNumber> createSequenceFor(final IComplexNumber complexNumber) {
    return sequenceCreator.apply(complexNumber);
  }

  //method
  @Override
  public int getBigDecimalScale() {
    return bigDecimalScale;
  }

  //method
  @Override
  public IColor getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(final int iterationCount) {

    if (iterationCount == -1) {
      return CONVERGENCE_COLOR;
    }

    return colorFunction.apply(iterationCount);
  }

  //method
  @Override
  public int getHeightInPixel() {
    return heightInPixel;
  }

  //method
  @Override
  public IClosedInterval getImaginaryComponentInterval() {
    return imaginaryComponentInterval;
  }

  //method
  @Override
  public IClosedInterval getRealComponentInterval() {
    return realComponentInterval;
  }

  //method
  @Override
  public int getMaxIterationCount() {
    return sequencesMaxIterationCount;
  }

  //method
  @Override
  public BigDecimal getMinMagnitudeForDivergence() {
    return sequencesMinDivergenceMagnitude;
  }

  //method
  @Override
  public int getWidthInPixel() {
    return widthInPixel;
  }

  //method
  @Override
  public ImageGenerator startImageGeneration() {
    return new ImageGenerator(this);
  }

  //method
  @Override
  public MutableImage toImage() {

    final var imageBuilder = startImageGeneration();
    imageBuilder.waitUntilIsFinishedSuccessfully();

    return imageBuilder.getStoredImage();
  }
}
