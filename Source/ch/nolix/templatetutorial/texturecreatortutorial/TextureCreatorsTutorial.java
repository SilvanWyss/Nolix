package ch.nolix.templatetutorial.texturecreatortutorial;

//own imports
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.Accordion;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.WidgetLookState;
import ch.nolix.system.texture.TextureCreator;
import ch.nolix.template.texturecreator.ConcreteTextureCreator;
import ch.nolix.template.texturecreator.JuteTextureCreator;

/**
 * The {@link TextureCreatorsTutorial} is a tutorial for {@link TextureCreator}s.
 * Of the {@link TextureCreatorsTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-08-25
 */
public final class TextureCreatorsTutorial {
	
	/**
	 * Lets several {@link TextureCreator}s create textures and shows the textures.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates textures.
		final var concreteTexture = new ConcreteTextureCreator().createTexture(80, 50).toScaledImage(10);
		final var juteTexture = new JuteTextureCreator().createTexture(80, 50).toScaledImage(10);
		
		//Creates a Frame that will show the textures.
		new Frame()
		.setTitle("TextureCreators tutorial")
		.addLayerOnTop(
			new Accordion()
			.addTab(
				"Concrete",
				new ImageWidget()
				.setImage(concreteTexture)
				.onLook(
					l ->
					l
					.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
					.setPaddingForState(WidgetLookState.BASE, 10)
				)
			)
			.addTab(
				"Jute",
				new ImageWidget()
				.setImage(juteTexture)
				.onLook(
					l ->
					l
					.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
					.setPaddingForState(WidgetLookState.BASE, 10)
				)
			)
		);
	}
	
	/**
	 * Prevents that an instance of the {@link TextureCreatorsTutorial} can be created.
	 */
	private TextureCreatorsTutorial() {}
}
