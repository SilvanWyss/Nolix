//package declaration
package ch.nolix.business.serverdashboardaccess;

//own imports
import ch.nolix.businessapi.serverdashboardaccessapi.IApplicationSheet;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.target.ApplicationTarget;
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.core.net.targetapi.IApplicationTarget;
import ch.nolix.core.net.targetapi.IServerTarget;
import ch.nolix.system.application.guiapplication.BackendGUIClient;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.systemapi.applicationapi.guiapplicationapi.IGUIApplicationContext;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//class
public final class ApplicationSheet implements IApplicationSheet {
	
	//static method
	public static ApplicationSheet forGUIApplicationOnServer(
		final Application<BackendGUIClient<?>, ?> pGUIApplication,
		final Server server
	) {
		
		if (pGUIApplication.getRefContext().getClass().isAssignableFrom(IGUIApplicationContext.class)) {
			return
			new ApplicationSheet(
				pGUIApplication.getName(),
				(IGUIApplicationContext)pGUIApplication.getRefContext(),
				server.asTarget()
			);
		}
		
		return new ApplicationSheet(pGUIApplication.getName(), server.asTarget());
	}
	
	//attribute
	private final String applicationName;
	
	//optional attribute
	private final IImage<?> applicationLogo;
	
	//optional attribute
	private final String applicationDescription;
	
	//attribute
	private final IServerTarget serverTarget;
			
	//constructor
	private ApplicationSheet(
		final String applicationName,
		final IGUIApplicationContext pGUIApplicationContext,
		final IServerTarget serverTarget
	) {
		
		Validator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		Validator.assertThat(serverTarget).thatIsNamed(ServerTarget.class).isNotNull();
		
		this.applicationName = applicationName;
		
		if (!pGUIApplicationContext.hasApplicationLogo()) {
			applicationLogo = null;
		} else {
			applicationLogo = pGUIApplicationContext.getApplicationLogo();
		}
		
		if (!pGUIApplicationContext.hasApplicationDescription()) {
			applicationDescription = pGUIApplicationContext.getApplicationDescription();
		} else {
			applicationDescription = null;
		}
		
		this.serverTarget =serverTarget;
	}
	
	//constructor
	private ApplicationSheet(
		final String applicationName,
		final IServerTarget serverTarget
	) {
		
		Validator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		Validator.assertThat(serverTarget).thatIsNamed(ServerTarget.class).isNotNull();
		
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
	public IImage<?> getApplicationLogo() {
		
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
			throw new ArgumentDoesNotHaveAttributeException(this, "application description");
		}
	}
	
	//method
	private void assertHasApplicationLogo() {
		if (!hasApplicationLogo()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "application logo");
		}
	}
}
