//package declaration
package ch.nolix.systemAPI.textureAPI;

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
	 * On a texture, 1000 pixel will represent approximately 1 m.
	 * 
	 * @param width
	 * @param height
	 * @return a new texture with the given width and height.
	 */
	public abstract Image createTexture(int width, int height);
}
