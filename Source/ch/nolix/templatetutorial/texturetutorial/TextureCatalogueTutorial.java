package ch.nolix.templatetutorial.texturetutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.containerwidget.FloatContainer;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.template.texture.TextureCatalogue;

public final class TextureCatalogueTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates textures.
		final var concreteTexture = TextureCatalogue.CONCRETE_TEXTURE.toScaledImage(10);
		final var fireWoodTexture = TextureCatalogue.FIR_WOOD_TEXTURE.toScaledImage(10);
		final var juteTexture = TextureCatalogue.JUTE_TEXTURE.toScaledImage(10);
		final var whiteMarbleTexture = TextureCatalogue.WHITE_MARBLE_TEXTURE.toScaledImage(10);
		
		//Creates a Frame that will show the textures.
		new Frame()
		.setTitle("TextureCatalogue Tutorial")
		.pushLayerWithRootWidget(
			new FloatContainer()
			.setProposalWidth(1000)
			.onLook(
				l -> l.setPaddingForState(WidgetLookState.BASE, 20).setElementMarginForState(WidgetLookState.BASE, 20)
			)
			.addWidget(
				new VerticalStack()
				.addWidget(
					new Label()
					.setText("Concrete"),
					new ImageWidget()
					.setImage(concreteTexture)
				),
				new VerticalStack()
				.addWidget(
					new Label()
					.setText("Fir Wood"),
					new ImageWidget()
					.setImage(fireWoodTexture)
				),
				new VerticalStack()
				.addWidget(
					new Label()
					.setText("Jute"),
					new ImageWidget()
					.setImage(juteTexture)
				),
				new VerticalStack()
				.addWidget(
					new Label()
					.setText("White Marble"),
					new ImageWidget()
					.setImage(whiteMarbleTexture)
				)
			)
		);
	}
}
