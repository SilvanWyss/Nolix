/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.web.cssmodel.ICss;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.gui.background.BackgroundManager;
import ch.nolix.systemapi.gui.main.Gui;
import ch.nolix.systemapi.gui.main.IFrontEndReader;
import ch.nolix.systemapi.gui.main.IFrontEndWriter;
import ch.nolix.systemapi.style.styleholder.StyleHolder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 * @param <G> the type of a {@link IWebGui}.
 */
public interface IWebGui<G extends IWebGui<G>> extends Clearable, BackgroundManager<G>, Gui<G>, StyleHolder<G> {
  /**
   * @param control
   * @return true if the current {@link IWebGui} contains the given control, false
   *         otherwise
   */
  boolean containsControl(Control<?, ?> control);

  /**
   * @return the CSS of the current {@link IWebGui}
   */
  ICss getCss();

  /**
   * @return the HTML of the current {@link IWebGui}
   */
  IHtmlElement getHtml();

  /**
   * @return the {@link IHtmlElementEvent}s that are registered at the current
   *         {@link IWebGui}
   */
  ExtendedIterable<IHtmlElementEvent> getHtmlElementEventRegistrations();

  /**
   * @return the number of {@link ILayer}s of the current {@link IWebGui}
   */
  int getLayerCount();

  /**
   * @param internalId
   * @return a {@link Optional} with the {@link Control} with the given internalId
   *         of the current {@link IWebGui} if the current {@link IWebGui}
   *         contains such a {@link Control}, an empty {@link Optional} otherwise
   */
  Optional<Control<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  /**
   * @return the {@link Control}s of the current {@link IWebGui}
   */
  ExtendedIterable<Control<?, ?>> getStoredControls();

  /**
   * @return the {@link ILayer}s of the current {@link IWebGui}
   */
  ExtendedIterable<ILayer> getStoredLayers();

  /**
   * @return the structure {@link Control}s of the current {@link IWebGui}
   */
  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  /**
   * @return the top {@link ILayer} of the current {@link IWebGui}
   * @throws RuntimeException if the current {@link IWebGui} does not contain a
   *                          {@link ILayer}
   */
  ILayer getStoredTopLayer();

  /**
   * @return true if the current {@link IWebGui} has a remove layer action, false
   *         otherwise
   */
  boolean hasRemoveLayerAction();

  /**
   * Pushes the given layer on the current {@link IWebGui}.
   * 
   * @param layer
   * @return the current {@link IWebGui}
   */
  G pushLayer(ILayer layer);

  /**
   * Pushes a new {@link ILayer} with the given rootControl on the current
   * {@link IWebGui}.
   * 
   * @param rootControl
   * @return the current {@link IWebGui}
   */
  G pushLayerWithRootControl(Control<?, ?> rootControl);

  /**
   * Removes the given layer from the current {@link IWebGui}.
   * 
   * @param layer
   */
  void removeLayer(ILayer layer);

  /**
   * Sets the given frontEndReader and frontEndWriter to the current
   * {@link IWebGui}.
   * 
   * @param frontEndReader
   * @param frontEndWriter
   * @return the current {@link IWebGui}
   */
  G setFrontEndReaderAndFrontEndWriter(IFrontEndReader frontEndReader, IFrontEndWriter frontEndWriter);

  /**
   * Sets the given removeLayerAction to the current {@link IWebGui}. The given
   * removeLayerAction is run when a {@link ILayer} is removed from the current
   * {@link IWebGui}.
   * 
   * @param removeLayerAction
   * @return the current {@link IWebGui}
   */
  G setRemoveLayerAction(Runnable removeLayerAction);
}
