//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.system.application.main.Session;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndWriter;

//class
public abstract class BaseBackendWebClientSession<
	BBWC extends BaseBackendWebClient<BBWC, AC>,
	AC
>
extends Session<BBWC, AC> {
	
	//method
	protected final IFrontEndWriter createFrontEndWriter() {
		return BaseBackendWebClientFrontendWriter.forBackendWebClient(getParentClient());
	}
}
