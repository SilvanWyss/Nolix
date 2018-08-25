//package declaration
package ch.nolix.techAPI;

//own imports
import ch.nolix.techAPI.resourceAPI.IResource;
import ch.nolix.techAPI.textureAPI.ITextureCreator;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 40
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
	
	//abstract method
	/**
	 * @param name
	 * @return a new {@link IResource} with the given name.
	 */
	public abstract IResource createResource(final String name);
	
	/**
	 * @param name
	 * @param baseResources
	 * @return a new {@link IResource} with the given name and base resources.
	 */
	public abstract IResource createResource(final String name, final IResource... baseResources);
}
