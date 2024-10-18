package ch.nolix.system.application.webapplication;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.basewebapplication.BaseWebClientSession;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

public abstract class WebClientSession<AC> extends BaseWebClientSession<WebClient<AC>, AC> {

  private final IWebGui<?> webGui = new WebGui();

  public final IWebGui<?> getStoredGui() {
    return webGui;
  }

  @Override
  public final void refresh() {
    getStoredParentClient().internalUpdateCounterpartFromWebGui(getStoredGui(), true);
  }

  public final void updateControlOnCounterpart(final IControl<?, ?> control, final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlOnCounterpart(control, updateConstellationOrStyle);
  }

  public final void updateControlsOnCounterpart(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

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
