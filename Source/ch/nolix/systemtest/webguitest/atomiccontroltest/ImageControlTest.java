//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControl;

//class
public final class ImageControlTest extends ControlTest<IImageControl> {
	
	//method
	@Override
	protected IImageControl createTestUnit() {
		return new ImageControl();
	}
	
	//method
	@TestCase
	public void testCase_getStoredImage_whenIsEmpty() {
		
		//setup
		final var testUnit = new ImageControl();
		
		//setup verification
		expect(testUnit.isEmpty());
		
		//execution & verification
		expectRunning(testUnit::getStoredImage)
		.throwsException()
		.ofType(ArgumentDoesNotHaveAttributeException.class);
	}
	
	//method
	@TestCase
	public void testCase_getStoredImage_whenContainsMutableImage() {
		
		//setup
		final var mutableImage = MutableImage.withWidthAndHeightAndColor(16, 16, Color.WHITE);
		final var testUnit = new ImageControl();
		testUnit.setImage(mutableImage);
		
		//execution
		final var result = testUnit.getStoredImage();
		
		//verification
		expect(result).is(mutableImage);
	}
}
