package ch.nolix.templateTutorial.textureCreatorTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.containerWidgets.Accordion;
import ch.nolix.element.widgets.ImageWidget;
import ch.nolix.system.texture.TextureCreator;
import ch.nolix.template.textureCreator.ConcreteTextureCreator;
import ch.nolix.template.textureCreator.JuteTextureCreator;

/**
 * The {@link TextureCreatorsTutorial} is a tutorial for {@link TextureCreator}s.
 * Of the {@link TextureCreatorsTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 50
 */
public final class TextureCreatorsTutorial {
	
	/**
	 * Lets several {@link TextureCreator}s create textures and shows the textures.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates textures.
		final var concreteTexture = new ConcreteTextureCreator().createTexture(80, 50).toScaledImage(10);
		final var juteTexture = new JuteTextureCreator().createTexture(80, 50).toScaledImage(10);
		
		//Creates a Frame that will show the textures.
		new Frame(
			"TextureCreators Tutorial",
			new Accordion()
			.addTab(
				"Concrete",
				new ImageWidget(concreteTexture)
				.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.WHITE_SMOKE).setPaddings(5))
			)
			.addTab(
				"Jute",
				new ImageWidget(juteTexture)
				.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.WHITE_SMOKE).setPaddings(5))
			)
		);
	}
	
	/**
	 * Avoids that an instance of the {@link TextureCreatorsTutorial} an instance can be created.
	 */
	private TextureCreatorsTutorial() {}
}
