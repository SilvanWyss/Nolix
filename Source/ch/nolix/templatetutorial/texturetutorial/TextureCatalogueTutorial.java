package ch.nolix.templatetutorial.texturetutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.template.texture.TextureCatalogue;

public final class TextureCatalogueTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Hello World GUI tutorial", MainSession.class, new VoidObject());
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends WebClientSession<Object> {
		
		@Override
		protected void initialize() {
		
			//Creates textures.
			final var concreteTexture = TextureCatalogue.CONCRETE_TEXTURE.toScaledImage(10);
			final var fireWoodTexture = TextureCatalogue.FIR_WOOD_TEXTURE.toScaledImage(10);
			final var juteTexture = TextureCatalogue.JUTE_TEXTURE.toScaledImage(10);
			final var parchmentTexture = TextureCatalogue.PARCHMENT_TEXTURE.toScaledImage(10);
			final var whiteMarbleTexture = TextureCatalogue.WHITE_MARBLE_TEXTURE.toScaledImage(10);
			
			//Adds the textures to the GUI of the current MainSession.
			getOriGui()
			.setTitle("TextureCatalogue Tutorial")
			.pushLayerWithRootControl(
				new FloatContainer()
				.setMaxWidth(1000)
				.editStyle(
					s -> s.setPaddingForState(ControlState.BASE, 20).setChildControlMarginForState(ControlState.BASE, 20)
				)
				.addControl(
					new VerticalStack()
					.addControl(
						new Label()
						.setText("Concrete"),
						new ImageControl()
						.setImage(concreteTexture)
					),
					new VerticalStack()
					.addControl(
						new Label()
						.setText("Fir Wood"),
						new ImageControl()
						.setImage(fireWoodTexture)
					),
					new VerticalStack()
					.addControl(
						new Label()
						.setText("Jute"),
						new ImageControl()
						.setImage(juteTexture)
					),
					new VerticalStack()
					.addControl(
						new Label()
						.setText("Parchment"),
						new ImageControl()
						.setImage(parchmentTexture)
					),
					new VerticalStack()
					.addControl(
						new Label()
						.setText("White Marble"),
						new ImageControl()
						.setImage(whiteMarbleTexture)
					)
				)
			);
		}
	}
}
