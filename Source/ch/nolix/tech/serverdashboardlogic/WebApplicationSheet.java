//package declaration
package ch.nolix.tech.serverdashboardlogic;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.techapi.serverdashboardlogicapi.IWebApplicationSheet;

//class
public final class WebApplicationSheet implements IWebApplicationSheet {
	
	//static method
	public static WebApplicationSheet forWebApplication(final Application<WebClient<?>, ?> webApplication) {
		return new WebApplicationSheet(webApplication);
	}
	
	//attribute
	private final IApplicationInstanceTarget applicationInstanceTarget;
	
	//optional attribute
	private final IImage applicationLogo;
	
	//constructor
	private WebApplicationSheet(final Application<WebClient<?>, ?> webApplication) {
		
		applicationInstanceTarget = webApplication.asTarget();
		
		final var applicationContext = webApplication.getOriApplicationContext();
						
		if (applicationContext instanceof IWebApplicationContext webApplicationContext) {
			applicationLogo = webApplicationContext.getApplicationLogo();
		} else {
			applicationLogo = null;
		}
	}
	
	//method
	@Override
	public IApplicationInstanceTarget getApplicationInstanceTarget() {
		return applicationInstanceTarget;
	}
	
	//method
	@Override
	public IImage getApplicationLogo() {
		
		assertHasApplicationLogo();
		
		return applicationLogo;
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
