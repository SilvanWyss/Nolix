//package declaration
package ch.nolix.techAPITutorial.textureTutorial;

//own imports
import ch.nolix.core.serviceProvider.ServiceProvider;
import ch.nolix.element.GUI.Accordion;
import ch.nolix.element.GUI.AccordionTab;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.tech.TechFactory;
import ch.nolix.tech.texture.JuteTextureCreator;
import ch.nolix.techAPI.ITechFactory;

//class
/**
 * The {@link TextureCreatorTutorial} provides a tutorial for a {@link JuteTextureCreator}.
 * Of the {@link TextureCreatorTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 60
 */
public final class TextureCreatorTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with textures that are created from a {@link JuteTextureCreator}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ServiceProvider.register(ITechFactory.class, new TechFactory());
		
		final var juteTextureCreator = ServiceProvider.get(ITechFactory.class).createJuteTextureCreator();
		final var juteTexture = juteTextureCreator.createTexture(1000, 500);
		
		final var concreteTextureCreator = ServiceProvider.get(ITechFactory.class).createConreteTextureCreator();
		final var concreteTexture = concreteTextureCreator.createTexture(1000, 500);
		
		//Creates a frame that will show the concrete texture.
		new Frame()
		.setTitle("Texture Creator Tutorial")
		.setRootWidget(
			new Accordion(
				new AccordionTab(
					"Jute",
					new ImageWidget(
						juteTexture
					)
				),
				new AccordionTab(
					"Concrete",
					new ImageWidget(
						concreteTexture
					)
				)
			)	
		);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link TextureCreatorTutorial} can be created.
	 */
	private TextureCreatorTutorial() {}
}
