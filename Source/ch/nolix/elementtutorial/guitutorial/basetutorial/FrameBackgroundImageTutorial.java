package ch.nolix.elementtutorial.guitutorial.basetutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.image.Image;
import ch.nolix.element.gui.image.ImageApplication;

/**
 * The {@link FrameBackgroundImageTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link FrameBackgroundImageTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-27
 * @lines 30
 */
public final class FrameBackgroundImageTutorial {
	
	/**
	 * Creates a {@link Frame} and sets a background {@link Image} to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Frame background image tutorial");
		
		//Creates an Image from file.
		final var image = Image.fromResource("ch/nolix/elementTutorial/guiTutorial/basetutorial/resource/Pilatus.jpg");
		
		//Sets the Image as background to the Frame.
		frame.setBackgroundImage(image, ImageApplication.SCALE_TO_FRAME);
	}
	
	/**
	 * Prevents that an instance of the {@link FrameBackgroundImageTutorial} can be created.
	 */
	private FrameBackgroundImageTutorial() {}
}
