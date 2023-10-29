//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.system.application.main.Session;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

//class
public abstract class BaseWebClientSession<BBWC extends BaseWebClient<BBWC, AC>, AC>
extends Session<BBWC, AC> {

  //method
  protected final IFrontEndReader createFrontendReader() {
    return BaseWebClientFrontendReader.forBackendWebClient(getStoredParentClient());
  }

  //method
  protected final IFrontEndWriter createFrontendWriter() {
    return BaseWebClientFrontendWriter.forBackendWebClient(getStoredParentClient());
  }
}
