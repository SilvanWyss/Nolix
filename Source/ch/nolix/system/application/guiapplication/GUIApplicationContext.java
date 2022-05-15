//package declaration
package ch.nolix.system.application.guiapplication;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.applicationapi.guiapplicationapi.IGUIApplicationContext;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//class
public class GUIApplicationContext implements IGUIApplicationContext {
	
	//optional attribute
	private IImage<?> applicationLogo;
	
	//optional attribute
	private String applicationDescription;
	
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
	public boolean hasApplicationDescription() {
		return (applicationDescription != null);
	}
	
	//method
	@Override
	public boolean hasApplicationLogo() {
		return (applicationLogo != null);
	}
	
	//method
	public void setApplicationDescription(final String applicationDescription) {
		
		Validator.assertThat(applicationDescription).thatIsNamed("application description").isNotBlank();
		
		this.applicationDescription = applicationDescription;
	}
	
	//method
	public void setApplicationLogo(final IImage<?> applicationLogo) {
		
		Validator.assertThat(applicationLogo).thatIsNamed("application logo").isNotNull();
		
		this.applicationLogo = applicationLogo;
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
