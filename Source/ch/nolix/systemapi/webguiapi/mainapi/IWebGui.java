package ch.nolix.systemapi.webguiapi.mainapi;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.elementapi.styleapi.IStyleElement;
import ch.nolix.systemapi.guiapi.canvasapi.ICanvas;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;
import ch.nolix.systemapi.guiapi.mainapi.IGui;

public interface IWebGui<WG extends IWebGui<WG>> extends Clearable, ICanvas<WG>, IGui<WG>, IStyleElement<WG> {

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

  WG pushLayer(ILayer<?> layer);

  WG pushLayerWithRootControl(IControl<?, ?> rootControl);

  void removeLayer(ILayer<?> layer);

  WG setFrontEndReaderAndFrontEndWriter(IFrontEndReader frontEndReader, IFrontEndWriter frontEndWriter);

  WG setRemoveLayerAction(Runnable removeLayerAction);
}
