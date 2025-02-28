package ch.nolix.system.application.basewebapplication;

import ch.nolix.system.application.main.AbstractSession;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

public abstract class AbstractWebClientSession<C extends AbstractWebClient<C, S>, S> extends AbstractSession<C, S> {

  protected final IFrontEndReader createFrontendReader() {
    return FrontendReader.forBackendWebClient(getStoredParentClient());
  }

  protected final IFrontEndWriter createFrontendWriter() {
    return FrontendWriter.forBackendWebClient(getStoredParentClient());
  }
}
