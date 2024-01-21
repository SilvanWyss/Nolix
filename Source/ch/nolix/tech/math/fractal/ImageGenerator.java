//package declaration
package ch.nolix.tech.math.fractal;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.core.programcontrol.future.BaseFuture;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.fractalapi.IFractal;
import ch.nolix.techapi.mathapi.fractalapi.IFractalTool;
import ch.nolix.techapi.mathapi.fractalapi.IImageGenerator;

//class
public final class ImageGenerator extends BaseFuture implements IImageGenerator {

  //constant
  private static final int IMAGE_ROWS_PER_THREAD = 10;

  //constant
  private static final IFractalTool FRACTAL_HELPER = new FractalTool();

  //attribute
  private final IFractal fractal;

  //attribute
  private final BigDecimal squaredMinMagnitudeForDivergence;

  //attribute
  private final MutableImage image;

  //multi-attribute
  private final IContainer<IFuture> futures;

  //constructor
  private ImageGenerator(final IFractal fractal) {

    GlobalValidator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();

    this.fractal = fractal;

    squaredMinMagnitudeForDivergence = FRACTAL_HELPER.getSquaredMinMagnitudeForDivergence(fractal);

    image = MutableImage.withWidthAndHeightAndColor(fractal.getWidthInPixel(), fractal.getHeightInPixel(), Color.WHITE);

    futures = startFillImageAndGetFutures();
  }

  //static method
  public static ImageGenerator forFractal(final IFractal fractal) {
    return new ImageGenerator(fractal);
  }

  //method
  @Override
  public boolean caughtError() {
    return futures.containsAny(IFuture::caughtError);
  }

  //method
  @Override
  public Throwable getError() {

    final var futureWithError = futures.getStoredFirstOrNull(IFuture::caughtError);

    if (futureWithError == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.ERROR);
    }

    return futureWithError.getError();
  }

  //method
  @Override
  public MutableImage getStoredImage() {
    return image;
  }

  //method
  @Override
  public boolean isFinished() {
    return futures.containsOnly(IFuture::isFinished);
  }

  //method
  @Override
  public void waitUntilIsFinished() {
    futures.forEach(IFuture::waitUntilIsFinished);
  }

  //method
  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {

    final var startTimeInMilliseconds = System.currentTimeMillis();

    GlobalSequencer.waitAsLongAs(
      () -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
      && isRunning());

    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout before having finished");
    }
  }

  //method
  private void fillImageRow(final int y) {
    for (var x = 1; x <= image.getWidth(); x++) {
      fillImagePixel(x, y);
    }
  }

  //method
  private void fillImageRows(final int startImageRow, final int endImageRow) {
    for (var y = startImageRow; y <= endImageRow; y++) {
      fillImageRow(y);
    }
  }

  //method
  private void fillImagePixel(final int x, final int y) {

    final var color = Color.createAverageFrom(
      getColorOfPixel(x - 0.75, y - 0.75),
      getColorOfPixel(x - 0.75, y - 0.25),
      getColorOfPixel(x - 0.25, y - 0.75),
      getColorOfPixel(x - 0.25, y - 0.25));

    image.setPixel(x, y, color);
  }

  //method
  private IColor getColorOfPixel(final double x, final double y) {

    final var z = getComplexNumberOfPixel(x, y);

    final var iterationCount = getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(z);

    return fractal.getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(iterationCount);
  }

  //method
  private IComplexNumber getComplexNumberOfPixel(final double x, final double y) {
    return new ComplexNumber(
      FRACTAL_HELPER.getMinX(fractal).add(FRACTAL_HELPER.getUnitsForHorizontalPixelCount(fractal, x)),
      FRACTAL_HELPER.getMinY(fractal).add(FRACTAL_HELPER.getUnitsForVerticalPixelCount(fractal, y)));
  }

  //method
  private int getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
    final IComplexNumber complexNumber) {
    return FRACTAL_HELPER.getIterationCountForStartNumberWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(
      fractal,
      complexNumber,
      squaredMinMagnitudeForDivergence);
  }

  //method
  private LinkedList<IFuture> startFillImageAndGetFutures() {

    final var lFutures = new LinkedList<IFuture>();

    final var jobPool = new JobPool();

    final var heightInpixel = fractal.getHeightInPixel();
    for (var y = 1; y < heightInpixel; y += IMAGE_ROWS_PER_THREAD) {

      final var startImageRow = y;
      final var endImageRow = GlobalCalculator.getMin(heightInpixel, y + IMAGE_ROWS_PER_THREAD - 1);

      lFutures.addAtEnd(
        jobPool.enqueue(() -> fillImageRows(startImageRow, endImageRow)));
    }

    return lFutures;
  }
}
