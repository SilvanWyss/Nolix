//package declaration
package ch.nolix.templatesTutorial.textureCreatorTutorial;

//own imports
import ch.nolix.element.GUI.Accordion;
import ch.nolix.element.GUI.AccordionTab;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.templates.textureCreators.ConcreteTextureCreator;
import ch.nolix.templates.textureCreators.JuteTextureCreator;

//class
/**
 * The {@link TextureCreatorTutorial} provides a tutorial for {@link JuteTextureCreator}.
 * Of the {@link TextureCreatorTutorial} an instance cannot be created.
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
		
		//Creates textures.
		final var concreteTexture = new ConcreteTextureCreator().createTexture(1000, 500);
		final var juteTexture = new JuteTextureCreator().createTexture(1000, 500);
		
		//Creates a frame that will show the textures.
		new Frame()
		.setTitle("Texture Creator Tutorial")
		.setRootWidget(
			new Accordion(
				new AccordionTab(
					"Concrete",
					new ImageWidget(
						concreteTexture
					)
				),
				new AccordionTab(
					"Jute",
					new ImageWidget(
						juteTexture
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
