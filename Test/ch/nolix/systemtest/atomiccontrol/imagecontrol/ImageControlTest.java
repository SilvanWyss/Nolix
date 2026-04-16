/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.imagecontrol;

import org.junit.jupiter.api.Test;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.system.atomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.systemapi.atomiccontrol.imagecontrol.IImageControl;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class ImageControlTest extends ControlTest<IImageControl> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IImageControl createTestUnit() {
    return new ImageControl();
  }

  @Test
  void testCase_getStoredImage_whenIsEmpty() {
    //setup
    final var testUnit = new ImageControl();

    //setup verification
    expect(testUnit.isEmpty()).isTrue();

    //execution & verification
    expectRunning(testUnit::getStoredImage).throwsException().ofType(ArgumentDoesNotContainElementException.class);
  }

  @Test
  void testCase_getStoredImage_whenContainsMutableImage() {
    //setup
    final var mutableImage = MutableImage.withWidthAndHeightAndColor(16, 16, X11ColorCatalog.WHITE);
    final var testUnit = new ImageControl();
    testUnit.setImage(mutableImage);

    //execution
    final var result = testUnit.getStoredImage();

    //verification
    expect(result).is(mutableImage);
  }
}
