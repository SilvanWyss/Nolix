//package declaration
package ch.nolix.tech.serverdashboardapplication.view;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.techapi.serverdashboardlogicapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardlogicapi.IWebApplicationSheet;

//class
public final class ServerDashboardSession extends WebClientSession<IServerDashboardContext> {
	
	//constant
	private static final WebApplicationControlFactory WEB_APPLICATION_CONTROL_FACTORY =
	new WebApplicationControlFactory();
	
	//constant
	private static final ServerDashboardStyleCreator SERVER_DASHBOARD_STYLE_CREATOR = new ServerDashboardStyleCreator();
	
	//method
	@Override
	protected void initialize() {
		getOriGui()
		.pushLayerWithRootControl(
			new VerticalStack()
			.setRole(ContainerRole.OVERALL_CONTAINER)
			.addControl(
				new Label()
				.setRole(LabelRole.TITLE)
				.setText(getApplicationName()),
				new FloatContainer()
				.setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
				.addControls(
					getRelevantWebApplicationSheets()
					.to(WEB_APPLICATION_CONTROL_FACTORY::createWebApplicationControl)
				)
			)
		)
		.setStyle(SERVER_DASHBOARD_STYLE_CREATOR.createServerDashboardStyle());
	}
	
	//method
	private IContainer<IWebApplicationSheet> getRelevantWebApplicationSheets() {
		return
		getOriApplicationContext()
		.getWebApplicationSheets()
		.getOriOther(
			as -> as.getApplicationInstanceTarget().getApplicationInstanceName().equals(getApplicationName())
		);
	}
}
