package ch.nolix.templatesTutorial.textureCreatorTutorial;

import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.containerWidget.Accordion;
import ch.nolix.element.containerWidget.AccordionTab;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.templates.textureCreators.ConcreteTextureCreator;
import ch.nolix.templates.textureCreators.JuteTextureCreator;

/**
 * The {@link TextureCreatorTutorial} is a tutorial for Texture creators.
 * Of the {@link TextureCreatorTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 50
 */
public final class TextureCreatorTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates textures.
		final var concreteTexture = new ConcreteTextureCreator().createTexture(1000, 500);
		final var juteTexture = new JuteTextureCreator().createTexture(1000, 500);
		
		//Creates a Frame that will show the textures.
		new Frame(
			"Texture Creator Tutorial",
			new Accordion(
				new AccordionTab(
					"Concrete",
					new ImageWidget(concreteTexture	)
					.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.WHITE_SMOKE).setPaddings(10))
				),
				new AccordionTab(
					"Jute",
					new ImageWidget(juteTexture)
					.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.WHITE_SMOKE).setPaddings(10))
				)
			)
		);
	}
	
	private TextureCreatorTutorial() {
		throw new UninstantiableClassException(getClass());
	}
}
