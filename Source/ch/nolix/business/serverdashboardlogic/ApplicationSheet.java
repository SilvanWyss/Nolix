//package declaration
package ch.nolix.business.serverdashboardlogic;

import ch.nolix.businessapi.serverdashboardlogicapi.IApplicationSheet;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IServerTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//class
public final class ApplicationSheet implements IApplicationSheet {
	
	//static method
	public static ApplicationSheet forGuiApplicationOnServer(
		final Application<WebClient<?>, ?> guiApplication,
		final BaseServer server
	) {
		
		if (guiApplication.getOriApplicationContext() instanceof IWebApplicationContext) {
			return
			new ApplicationSheet(
				guiApplication.getInstanceName(),
				(IWebApplicationContext)guiApplication.getOriApplicationContext(),
				server.asTarget()
			);
		}
		
		return new ApplicationSheet(guiApplication.getInstanceName(), server.asTarget());
	}
	
	//attribute
	private final String applicationName;
	
	//optional attribute
	private final IImage applicationLogo;
	
	//optional attribute
	private final String applicationDescription;
	
	//attribute
	private final IServerTarget serverTarget;
			
	//constructor
	private ApplicationSheet(
		final String applicationName,
		final IWebApplicationContext guiApplicationContext,
		final IServerTarget serverTarget
	) {
		
		GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		GlobalValidator.assertThat(serverTarget).thatIsNamed(ServerTarget.class).isNotNull();
		
		this.applicationName = applicationName;
		
		applicationLogo = guiApplicationContext.getApplicationLogo();
		
		applicationDescription = guiApplicationContext.getApplicationDescription();
		
		this.serverTarget =serverTarget;
	}
	
	//constructor
	private ApplicationSheet(
		final String applicationName,
		final IServerTarget serverTarget
	) {
		
		GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		GlobalValidator.assertThat(serverTarget).thatIsNamed(ServerTarget.class).isNotNull();
		
		this.applicationName = applicationName;
		applicationLogo = null;
		applicationDescription = null;
		this.serverTarget =serverTarget;
	}
	
	//method
	@Override
	public String getApplicationDescription() {

		assertHasApplicationDescription();
		
		return applicationDescription;
	}
	
	//method
	@Override
	public IImage getApplicationLogo() {
		
		assertHasApplicationLogo();
		
		return applicationLogo;
	}
	
	//method
	@Override
	public String getApplicationName() {
		return applicationName;
	}
	
	//method
	@Override
	public IServerTarget getServer() {
		return serverTarget;
	}
	
	//method
	@Override
	public IApplicationInstanceTarget getApplicationTarget(final SecurityLevel securityLevelForConnections) {
		return
		ApplicationInstanceTarget.forIpOrAddressNameAndPortAndApplicationNameAndSecurityLevelForConnections(
			getServer().getIpOrAddressName(),
			getServer().getPort(),
			getApplicationName(),
			securityLevelForConnections
		);
	}
	
	//method
	@Override
	public boolean hasApplicationDescription() {
		return (applicationDescription != null);
	}
	
	//method
	@Override
	public boolean hasApplicationLogo() {
		return (applicationLogo != null);
	}
	
	//method
	private void assertHasApplicationDescription() {
		if (!hasApplicationDescription()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "application description");
		}
	}
	
	//method
	private void assertHasApplicationLogo() {
		if (!hasApplicationLogo()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "application logo");
		}
	}
}
