package ch.nolix.systemtutorial.webguitutorial.controltutorial;

import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.system.application.main.SecureServer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;

/**
 * The {@link SecureButtonTutorial} does the same as the {@link ButtonTutorial}, but
 * it is configured for a productive environment.
 * That means there is required a {@link SecureServer}, a domain and a valid SSL certificate.
 * A {@link SecureServer} is a secure web-socket server.
 * 
 * @author Silvan Wyss
 * @date 2023-05-07
 */
public final class SecureButtonTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server =
		SecureServer.forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Button tutorial", MainSession.class, new VoidObject());
	}
	
	private static final class MainSession extends WebClientSession<Object> {
		
		private int count;
		
		private final Label countLabel = new Label().setText(String.valueOf(count));
		
		@Override
		protected void initialize() {
			getOriGUI().pushLayerWithRootControl(
				new VerticalStack()
				.addControl(
					countLabel,
					new Button().setText("Increment").setLeftMouseButtonPressAction(this::incrementCount)
				)
			);
		}
		
		private void incrementCount() {
			count++;
			countLabel.setText(String.valueOf(count));
			updateCounterpart();
		}
	}
	
	private SecureButtonTutorial() {}
}
