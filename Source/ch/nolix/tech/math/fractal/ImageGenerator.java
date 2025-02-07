package ch.nolix.tech.math.fractal;

//Java import
import java.math.BigDecimal;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.core.programcontrol.future.AbstractFuture;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.fractalapi.IFractal;
import ch.nolix.techapi.mathapi.fractalapi.IFractalTool;
import ch.nolix.techapi.mathapi.fractalapi.IImageGenerator;

public final class ImageGenerator extends AbstractFuture implements IImageGenerator {

  private static final int IMAGE_ROWS_PER_THREAD = 10;

  private static final IFractalTool FRACTAL_TOOL = new FractalTool();

  private final IFractal fractal;

  private final BigDecimal squaredMinMagnitudeForDivergence;

  private final MutableImage image;

  private final IContainer<IFuture> futures;

  private ImageGenerator(final IFractal fractal) {

    GlobalValidator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();

    this.fractal = fractal;

    squaredMinMagnitudeForDivergence = FRACTAL_TOOL.getSquaredMinMagnitudeForDivergence(fractal);

    image = //
    MutableImage.withWidthAndHeightAndColor(
      fractal.getWidthInPixel(),
      fractal.getHeightInPixel(),
      X11ColorCatalog.WHITE);

    futures = startFillImageAndGetFutures();
  }

  public static ImageGenerator forFractal(final IFractal fractal) {
    return new ImageGenerator(fractal);
  }

  @Override
  public boolean caughtError() {
    return futures.containsAny(IFuture::caughtError);
  }

  @Override
  public Throwable getError() {

    final var futureWithError = futures.getOptionalStoredFirst(IFuture::caughtError);

    if (futureWithError.isEmpty()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.ERROR);
    }

    return futureWithError.get().getError();
  }

  @Override
  public MutableImage getStoredImage() {
    return image;
  }

  @Override
  public boolean isFinished() {
    return futures.containsOnly(IFuture::isFinished);
  }

  @Override
  public void waitUntilIsFinished() {
    futures.forEach(IFuture::waitUntilIsFinished);
  }

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

  private void fillImageRow(final int y) {
    for (var x = 1; x <= image.getWidth(); x++) {
      fillImagePixel(x, y);
    }
  }

  private void fillImageRows(final int startImageRow, final int endImageRow) {
    for (var y = startImageRow; y <= endImageRow; y++) {
      fillImageRow(y);
    }
  }

  private void fillImagePixel(final int x, final int y) {

    final var color = Color.createAverageFrom(
      getColorOfPixel(x - 0.75, y - 0.75),
      getColorOfPixel(x - 0.75, y - 0.25),
      getColorOfPixel(x - 0.25, y - 0.75),
      getColorOfPixel(x - 0.25, y - 0.25));

    image.setPixel(x, y, color);
  }

  private IColor getColorOfPixel(final double x, final double y) {

    final var z = getComplexNumberOfPixel(x, y);

    final var iterationCount = getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(z);

    return fractal.getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(iterationCount);
  }

  private IComplexNumber getComplexNumberOfPixel(final double x, final double y) {
    return new ComplexNumber(
      FRACTAL_TOOL.getMinX(fractal).add(FRACTAL_TOOL.getUnitsForHorizontalPixelCount(fractal, x)),
      FRACTAL_TOOL.getMinY(fractal).add(FRACTAL_TOOL.getUnitsForVerticalPixelCount(fractal, y)));
  }

  private int getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
    final IComplexNumber complexNumber) {
    return FRACTAL_TOOL.getIterationCountForStartNumberWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(
      fractal,
      complexNumber,
      squaredMinMagnitudeForDivergence);
  }

  private ILinkedList<IFuture> startFillImageAndGetFutures() {

    final ILinkedList<IFuture> lFutures = LinkedList.createEmpty();

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
