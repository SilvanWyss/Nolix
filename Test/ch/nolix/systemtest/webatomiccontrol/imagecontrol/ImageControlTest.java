package ch.nolix.systemtest.webatomiccontrol.imagecontrol;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControl;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControl;
import ch.nolix.systemtest.webgui.main.ControlTest;

/**
 * @author Silvan Wyss
 */
final class ImageControlTest extends ControlTest<IImageControl> {
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
    expectRunning(testUnit::getStoredImage)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
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
