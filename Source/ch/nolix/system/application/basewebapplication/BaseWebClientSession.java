package ch.nolix.system.application.basewebapplication;

import ch.nolix.system.application.main.Session;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

public abstract class BaseWebClientSession<BBWC extends BaseWebClient<BBWC, AS>, AS>
extends Session<BBWC, AS> {

  protected final IFrontEndReader createFrontendReader() {
    return BaseWebClientFrontendReader.forBackendWebClient(getStoredParentClient());
  }

  protected final IFrontEndWriter createFrontendWriter() {
    return BaseWebClientFrontendWriter.forBackendWebClient(getStoredParentClient());
  }
}
