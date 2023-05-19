//package declaration
package ch.nolix.business.serverdashboardcontext;

import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationSheet;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.target.ApplicationTarget;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationTarget;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IServerTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//class
public final class ApplicationSheet implements IApplicationSheet {
	
	//static method
	public static ApplicationSheet forGUIApplicationOnServer(
		final Application<BackendWebClient<?>, ?> pGUIApplication,
		final Server server
	) {
		
		if (pGUIApplication.getOriApplicationContext() instanceof IWebApplicationContext) {
			return
			new ApplicationSheet(
				pGUIApplication.getInstanceName(),
				(IWebApplicationContext)pGUIApplication.getOriApplicationContext(),
				server.asTarget()
			);
		}
		
		return new ApplicationSheet(pGUIApplication.getInstanceName(), server.asTarget());
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
		final IWebApplicationContext pGUIApplicationContext,
		final IServerTarget serverTarget
	) {
		
		GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		GlobalValidator.assertThat(serverTarget).thatIsNamed(ServerTarget.class).isNotNull();
		
		this.applicationName = applicationName;
		
		if (!pGUIApplicationContext.hasApplicationLogo()) {
			applicationLogo = null;
		} else {
			applicationLogo = pGUIApplicationContext.getApplicationLogo();
		}
		
		if (!pGUIApplicationContext.hasApplicationDescription()) {
			applicationDescription = null;
		} else {
			applicationDescription = pGUIApplicationContext.getApplicationDescription();
		}
		
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
	public IApplicationTarget getApplicationTarget() {
		return
		ApplicationTarget.forIpOrAddressNameAndPortAndApplicationName(
			getServer().getIpOrAddressName(),
			getServer().getPort(),
			getApplicationName()
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
