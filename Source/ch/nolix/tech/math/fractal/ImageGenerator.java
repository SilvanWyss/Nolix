/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.tech.math.fractal;

//Java import
import java.math.BigDecimal;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.math.main.Calculator;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.programcontrol.future.AbstractFuture;
import ch.nolix.base.programcontrol.jobpool.JobPool;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.programcontrol.future.IFuture;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.techapi.math.fractal.IFractal;
import ch.nolix.techapi.math.fractal.IImageGenerator;

/**
 * @author Silvan Wyss
 */
public final class ImageGenerator extends AbstractFuture implements IImageGenerator {
  private static final int IMAGE_ROWS_PER_THREAD = 10;

  private static final FractalTool FRACTAL_TOOL = new FractalTool();

  private final IFractal fractal;

  private final BigDecimal squaredMinMagnitudeForDivergence;

  private final MutableImage image;

  private final ExtendedIterable<IFuture> futures;

  private ImageGenerator(final IFractal fractal) {
    Validator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();

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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean caughtError() {
    return futures.containsMatching(IFuture::caughtError);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getError() {
    final var futureWithError = futures.getOptionalStoredFirst(IFuture::caughtError);

    if (futureWithError.isEmpty()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.ERROR);
    }

    return futureWithError.get().getError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MutableImage getStoredImage() {
    return image;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFinished() {
    return futures.containsMatchingOnly(IFuture::isFinished);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished() {
    futures.forEach(IFuture::waitUntilIsFinished);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {
    final var startTimeInMilliseconds = System.currentTimeMillis();

    FlowController.waitAsLongAs(
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
    final var color = Color.createAverageFromColors(
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
    final var realComponent = //
    FRACTAL_TOOL.getMinX(fractal).add(FRACTAL_TOOL.getUnitsForHorizontalPixelCount(fractal, x));

    final var imaginaryComponent = //
    FRACTAL_TOOL.getMinY(fractal).add(FRACTAL_TOOL.getUnitsForVerticalPixelCount(fractal, y));

    return ComplexNumber.withRealComponentAndImaginaryComponent(realComponent, imaginaryComponent);
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
      final var endImageRow = Calculator.getMin(heightInpixel, y + IMAGE_ROWS_PER_THREAD - 1);

      lFutures.addAtEnd(
        jobPool.enqueue(() -> fillImageRows(startImageRow, endImageRow)));
    }

    return lFutures;
  }
}
