//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//class
public class WebApplicationContext implements IWebApplicationContext {
	
	//optional attribute
	private IImage applicationLogo;
	
	//optional attribute
	private String applicationDescription;
	
	//method
	@Override
	public final String getApplicationDescription() {

		assertHasApplicationDescription();
		
		return applicationDescription;
	}
	
	//method
	@Override
	public final IImage getApplicationLogo() {
		
		assertHasApplicationLogo();
		
		return applicationLogo;
	}
	
	//method
	@Override
	public final boolean hasApplicationDescription() {
		return (applicationDescription != null);
	}
	
	//method
	@Override
	public final boolean hasApplicationLogo() {
		return (applicationLogo != null);
	}
	
	//method
	public final WebApplicationContext setApplicationDescription(final String applicationDescription) {
		
		GlobalValidator.assertThat(applicationDescription).thatIsNamed("application description").isNotBlank();
		
		this.applicationDescription = applicationDescription;
		
		return this;
	}
	
	//method
	public final WebApplicationContext setApplicationLogo(final IImage applicationLogo) {
		
		GlobalValidator.assertThat(applicationLogo).thatIsNamed("application logo").isNotNull();
		
		this.applicationLogo = applicationLogo;
		
		return this;
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
