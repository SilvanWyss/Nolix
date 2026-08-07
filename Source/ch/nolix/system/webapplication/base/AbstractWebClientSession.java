/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.base;

import ch.nolix.base.net.sessionserver.AbstractSession;
import ch.nolix.baseapi.net.sessionserver.IApplication;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link AbstractWebClient} of a
 *            {@link AbstractWebClientSession}.
 * @param <S> the type of the application service of the {@link IApplication} of
 *            the {@link AbstractWebClient} of a
 *            {@link AbstractWebClientSession}.
 */
public abstract class AbstractWebClientSession<C extends AbstractWebClient<C, S>, S> extends AbstractSession<C, S> {
  protected final IFrontEndReader createFrontendReader() {
    return FrontendReader.forBackendWebClient(getStoredParentClient());
  }

  protected final IFrontEndWriter createFrontendWriter() {
    return FrontendWriter.forBackendWebClient(getStoredParentClient());
  }
}
