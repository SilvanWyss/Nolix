/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.web.cssmodel.ICss;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.gui.background.IBackgroundHolder;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;
import ch.nolix.systemapi.gui.model.IGui;
import ch.nolix.systemapi.style.styleholder.StyleHolder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 * @param <G> the type of a {@link IWebGui}.
 */
public interface IWebGui<G extends IWebGui<G>> extends Clearable, IBackgroundHolder<G>, IGui<G>, StyleHolder<G> {
  boolean containsControl(Control<?, ?> control);

  ICss getCss();

  IHtmlElement getHtml();

  ExtendedIterable<IHtmlElementEvent> getHtmlElementEventRegistrations();

  int getLayerCount();

  Optional<Control<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  ExtendedIterable<Control<?, ?>> getStoredControls();

  ExtendedIterable<ILayer> getStoredLayers();

  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  ILayer getStoredTopLayer();

  boolean hasRemoveLayerAction();

  G pushLayer(ILayer layer);

  G pushLayerWithRootControl(Control<?, ?> rootControl);

  void removeLayer(ILayer layer);

  G setFrontEndReaderAndFrontEndWriter(IFrontEndReader frontEndReader, IFrontEndWriter frontEndWriter);

  G setRemoveLayerAction(Runnable removeLayerAction);
}
