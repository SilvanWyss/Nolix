package ch.nolix.systemtest.webguitest.atomiccontroltest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.system.graphic.color.X11ColorCatalogue;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.atomiccontrol.imagecontrol.ImageControl;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.imagecontrolapi.IImageControl;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

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
    final var mutableImage = MutableImage.withWidthAndHeightAndColor(16, 16, X11ColorCatalogue.WHITE);
    final var testUnit = new ImageControl();
    testUnit.setImage(mutableImage);

    //execution
    final var result = testUnit.getStoredImage();

    //verification
    expect(result).is(mutableImage);
  }
}
