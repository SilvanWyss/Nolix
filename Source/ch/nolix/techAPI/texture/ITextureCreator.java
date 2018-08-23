//package declaration
package ch.nolix.techAPI.texture;

//own import
import ch.nolix.element.image.Image;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface ITextureCreator {
	
	//abstract method
	/**
	 * @param width
	 * @param height
	 * @param pixelPerMeter
	 * @return a new texture with the given width and height
	 * and the given number of pixels per meter as scale.
	 */
	public abstract Image generateTexture(
		int width,
		int height,
		double pixelPerMeter
	);
}
