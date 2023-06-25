//package declaration
package ch.nolix.business.serverdashboardlogic;

//own imports
import ch.nolix.businessapi.serverdashboardlogicapi.IApplicationSheet;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//class
public final class ApplicationSheet implements IApplicationSheet {
	
	//static method
	public static ApplicationSheet forWebApplication(final Application<WebClient<?>, ?> webApplication) {
		
		if (webApplication.getOriApplicationContext() instanceof IWebApplicationContext webApplicationContext) {
			return new ApplicationSheet(webApplication.asTarget(), webApplicationContext);
		}
		
		return new ApplicationSheet(webApplication.asTarget());
	}
	
	//attribute
	private final IApplicationInstanceTarget applicationInstanceTarget;
	
	//optional attribute
	private final IImage applicationLogo;
	
	//constructor
	private ApplicationSheet(final IApplicationInstanceTarget applicationInstanceTarget) {
		
		GlobalValidator
		.assertThat(applicationInstanceTarget)
		.thatIsNamed(IApplicationInstanceTarget.class)
		.isNotNull();
		
		this.applicationInstanceTarget = applicationInstanceTarget;
		applicationLogo = null;
	}
	
	//constructor
	private ApplicationSheet(
		final IApplicationInstanceTarget applicationInstanceTarget,
		final IWebApplicationContext webApplicationContext
	) {
		
		GlobalValidator
		.assertThat(applicationInstanceTarget)
		.thatIsNamed(IApplicationInstanceTarget.class)
		.isNotNull();
		
		this.applicationInstanceTarget = applicationInstanceTarget;
		applicationLogo = webApplicationContext.getApplicationLogo();
	}
	
	//method
	@Override
	public IImage getApplicationLogo() {
		
		assertHasApplicationLogo();
		
		return applicationLogo;
	}
	
	//method
	@Override
	public IApplicationInstanceTarget getApplicationInstanceTarget() {
		return applicationInstanceTarget;
	}
	
	//method
	@Override
	public boolean hasApplicationLogo() {
		return (applicationLogo != null);
	}
	
	//method
	private void assertHasApplicationLogo() {
		if (!hasApplicationLogo()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "application logo");
		}
	}
}
