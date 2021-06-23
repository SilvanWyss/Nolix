package ch.nolix.elementtutorial.guitutorial.basetutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.ImageApplication;
import ch.nolix.element.gui.image.MutableImage;

/**
 * The {@link EmptyLayersTutorial} is a tutorial for {@link Layer}s.
 * Of the {@link EmptyLayersTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-06-21
 * @lines 50
 */
public final class EmptyLayersTutorial {
	
	/**
	 * Creates a {@link Frame} with 2 {@link Layer}s whereas the top {@link Layer} is transparent and
	 * lets the {@link Layer} behind shine through.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Empty Layers tutorial");
		
		//Creates an Image that shows a mountain.
		final var mountainImage = 
		MutableImage.fromResource("ch/nolix/elementTutorial/guiTutorial/basetutorial/resource/Pilatus.jpg");
		
		//Adds a new Layer to top of the Frame with the mountainImage as background Image.
		frame.addLayerOnTop(new Layer().setBackgroundImage(mountainImage,	ImageApplication.SCALE_TO_FRAME));
		
		//Creates an Image that is transparent and shows 4 rectangles.
		final var rectaglesImage = MutableImage.withWidthAndHeight(2, 2);
		rectaglesImage.setPixel(1, 1, Color.BLUE);
		rectaglesImage.setPixel(2, 1, Color.RED);
		rectaglesImage.setPixel(1, 2, Color.RED);
		rectaglesImage.setPixel(2, 2, Color.BLUE);
		final var transparentRectanglesImage = rectaglesImage.withAlphaValue(0.25);
		
		//Adds a new Layer to top of the Frame with the transparentRectanglesImage as background Image.
		frame.addLayerOnTop(new Layer().setBackgroundImage(transparentRectanglesImage, ImageApplication.SCALE_TO_FRAME));
	}
	
	/**
	 * Prevents that an instance of the {@link EmptyLayersTutorial} can be created.
	 */
	private EmptyLayersTutorial() {}
}
