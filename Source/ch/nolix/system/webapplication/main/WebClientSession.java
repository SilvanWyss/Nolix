/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.main;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.net.clientserver.Application;
import ch.nolix.system.webapplication.base.AbstractWebClientSession;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of the {@link Application} of
 *            the {@link WebClient} of a {@link WebClientSession}.
 */
public abstract class WebClientSession<S> // NOSONAR: A web client session class is expected to be abstract.
extends AbstractWebClientSession<WebClient<S>, S> {
  private final IWebGui<?> webGui = new WebGui();

  /**
   * {@inheritDoc}
   */
  @Override
  public final Class<?> getClientClass() {
    return WebClient.class;
  }

  /**
   * @return the GUI of the current {@link WebClientSession}
   */
  public final IWebGui<?> getStoredGui() {
    return webGui;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalFullInitialize() {
    getStoredGui()
      .setTitle(getApplicationName())
      .setFrontEndReaderAndFrontEndWriter(createFrontendReader(), createFrontendWriter());

    initialize();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void refresh() {
    getStoredParentClient().internalUpdateCounterpartFromWebGui(getStoredGui(), true);
  }

  /**
   * Updates the given control on the counterpart of the current
   * {@link WebClientSession}.
   * 
   * @param control
   * @param updateConstellationOrStyle
   */
  public final void updateControlOnCounterpart(final Control<?, ?> control, final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlOnCounterpart(control, updateConstellationOrStyle);
  }

  /**
   * Updates the given controls on the counterpart of the current
   * {@link WebClientSession}.
   * 
   * @param controls
   * @param updateConstellationOrStyle
   */
  public final void updateControlsOnCounterpart(
    final ExtendedIterable<Control<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  protected abstract void initialize();
}
