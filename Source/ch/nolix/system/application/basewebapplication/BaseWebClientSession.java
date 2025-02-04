package ch.nolix.system.application.basewebapplication;

import ch.nolix.system.application.main.AbstractSession;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

public abstract class BaseWebClientSession<C extends BaseWebClient<C, S>, S> extends AbstractSession<C, S> {

  protected final IFrontEndReader createFrontendReader() {
    return BaseWebClientFrontendReader.forBackendWebClient(getStoredParentClient());
  }

  protected final IFrontEndWriter createFrontendWriter() {
    return BaseWebClientFrontendWriter.forBackendWebClient(getStoredParentClient());
  }
}
