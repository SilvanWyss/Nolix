//package declaration
package ch.nolix.techAPI.textureAPI;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface ITechFactory {

	//abstract method
	/**
	 * @return a new {ITextureCreator} that will create concrete textures.
	 */
	public abstract ITextureCreator createConreteTextureCreator();
	
	//abstract method
	/**
	 * @return a new {ITextureCreator} that will create jute textures.
	 */
	public abstract ITextureCreator createJuteTextureCreator();
}
