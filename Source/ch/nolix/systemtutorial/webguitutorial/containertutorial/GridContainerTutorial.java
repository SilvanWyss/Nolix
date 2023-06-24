package ch.nolix.systemtutorial.webguitutorial.containertutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.GridContainer;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class GridContainerTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("GridContainer tutorial", MainSession.class, new VoidObject());
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends WebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Creates a GridContainer.
			final var gridContainer = new GridContainer();
			
			//Inserts Controls into the GridContainer.
			gridContainer
			.insertControlAtRowAndColumn(1, 1, new Label().setText("A"))
			.insertControlAtRowAndColumn(1, 3, new Label().setText("B"))
			.insertControlAtRowAndColumn(2, 2, new Label().setText("C"))
			.insertControlAtRowAndColumn(2, 4, new Label().setText("D"))
			.insertControlAtRowAndColumn(3, 1, new Label().setText("E"))
			.insertControlAtRowAndColumn(3, 3, new Label().setText("F"))
			.insertControlAtRowAndColumn(4, 2, new Label().setText("G"))
			.insertControlAtRowAndColumn(4, 4, new Label().setText("H"));
			
			//Configures the style of the GridContainer.
			gridContainer
			.getOriStyle()
			.setTextSizeForState(ControlState.BASE, 50);
			
			//Adds the GridContainer to the GUI of the current MainSession.
			getOriGui().pushLayerWithRootControl(gridContainer);
		}
	}
	
	private GridContainerTutorial() {}
}
