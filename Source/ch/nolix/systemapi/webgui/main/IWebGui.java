package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.coreapi.web.cssmodel.ICss;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.gui.background.IBackgroundHolder;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;
import ch.nolix.systemapi.gui.model.IGui;
import ch.nolix.systemapi.style.stylable.IStyleElement;

/**
 * @author Silvan Wyss
 * @param <G> is the type of a {@link IWebGui}.
 */
public interface IWebGui<G extends IWebGui<G>> extends Clearable, IBackgroundHolder<G>, IGui<G>, IStyleElement<G> {
  boolean containsControl(IControl<?, ?> control);

  ICss getCss();

  IHtmlElement getHtml();

  IContainer<IHtmlElementEvent> getHtmlElementEventRegistrations();

  int getLayerCount();

  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  IContainer<IControl<?, ?>> getStoredControls();

  IContainer<ILayer<?>> getStoredLayers();

  ILayer<?> getStoredTopLayer();

  boolean hasRemoveLayerAction();

  G pushLayer(ILayer<?> layer);

  G pushLayerWithRootControl(IControl<?, ?> rootControl);

  void removeLayer(ILayer<?> layer);

  G setFrontEndReaderAndFrontEndWriter(IFrontEndReader frontEndReader, IFrontEndWriter frontEndWriter);

  G setRemoveLayerAction(Runnable removeLayerAction);
}
