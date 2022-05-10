package ch.nolix.systemtutorial.guitutorial.basetutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;

/**
 * The {@link FrameBackgroundImageTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link FrameBackgroundImageTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-27
 */
public final class FrameBackgroundImageTutorial {
	
	/**
	 * Creates a {@link Frame} and sets a background {@link MutableImage} to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Frame background image tutorial");
		
		//Creates an Image from file.
		final var image =
		MutableImage.fromResource("ch/nolix/systemTutorial/guiTutorial/basetutorial/resource/Pilatus.jpg");
		
		//Sets the Image as background to the Frame.
		frame.setBackgroundImage(image, ImageApplication.SCALE_TO_FRAME);
	}
	
	/**
	 * Prevents that an instance of the {@link FrameBackgroundImageTutorial} can be created.
	 */
	private FrameBackgroundImageTutorial() {}
}
