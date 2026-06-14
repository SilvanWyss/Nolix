/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.main;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.system.webapplication.base.AbstractWebClientSession;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.application.main.IApplication;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 * @param <S> is the type of the application service of the {@link IApplication}
 *            of the {@link WebClient} of a {@link WebClientSession}.
 */
public abstract class WebClientSession<S> //NOSONAR: A web client session class is expected to be abstract.
extends AbstractWebClientSession<WebClient<S>, S> {
  private final IWebGui<?> webGui = new WebGui();

  public final IWebGui<?> getStoredGui() {
    return webGui;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void refresh() {
    getStoredParentClient().internalUpdateCounterpartFromWebGui(getStoredGui(), true);
  }

  public final void updateControlOnCounterpart(final IControl<?, ?> control, final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlOnCounterpart(control, updateConstellationOrStyle);
  }

  public final void updateControlsOnCounterpart(
    final IWellOrderContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void fullInitialize() {
    getStoredGui()
      .setTitle(getApplicationName())
      .setFrontEndReaderAndFrontEndWriter(createFrontendReader(), createFrontendWriter());

    initialize();
  }

  protected abstract void initialize();

  @Override
  protected final Class<?> getClientClass() {
    return WebClient.class;
  }
}
