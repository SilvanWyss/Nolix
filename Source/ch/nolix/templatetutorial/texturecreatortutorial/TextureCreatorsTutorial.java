package ch.nolix.templatetutorial.texturecreatortutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.Accordion;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.system.texture.TextureCreator;
import ch.nolix.template.texturecreator.ConcreteTextureCreator;
import ch.nolix.template.texturecreator.JuteTextureCreator;

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
		new Frame()
		.setTitle("TextureCreators Tutorial")
		.addLayerOnTop(
			new Accordion()
			.addTab(
				"Concrete",
				new ImageWidget().setImage(concreteTexture)
				.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.LAVENDER).setPaddings(10))
			)
			.addTab(
				"Jute",
				new ImageWidget().setImage(juteTexture)
				.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.LAVENDER).setPaddings(10))
			)
		);
	}
	
	/**
	 * Avoids that an instance of the {@link TextureCreatorsTutorial} can be created.
	 */
	private TextureCreatorsTutorial() {}
}
