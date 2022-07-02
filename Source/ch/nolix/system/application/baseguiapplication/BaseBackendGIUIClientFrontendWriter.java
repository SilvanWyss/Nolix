//package declaration
package ch.nolix.system.application.baseguiapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.targetuniversalapi.IApplicationTarget;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndWriter;

//class
final class BaseBackendGIUIClientFrontendWriter implements IFrontEndWriter {
	
	//attribute
	private final BaseBackendGUIClient<?, ?> parentBackendGUIClient;
	
	//constructor
	public BaseBackendGIUIClientFrontendWriter(final BaseBackendGUIClient<?, ?> parentBackendGUIClient) {
		
		GlobalValidator.assertThat(parentBackendGUIClient).thatIsNamed("parent backend GUI client").isNotNull();
		
		this.parentBackendGUIClient = parentBackendGUIClient;
	}
	
	//method
	@Override
	public void openNewTabWithURL(final String pURL) {
		parentBackendGUIClient.internalOpenNewTabOnCounterpartWithURL(pURL);	
	}
	
	//method
	@Override
	public void redirectTo(final IApplicationTarget applicationTarget) {
		parentBackendGUIClient.internalRedirectTo(applicationTarget);
	}
	
	//method
	@Override
	public void saveFile(byte[] bytes) {
		parentBackendGUIClient.saveFileOnCounterpart(bytes);
	}
}
