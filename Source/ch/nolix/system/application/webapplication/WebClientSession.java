//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.basewebapplication.BaseWebClientSession;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public abstract class WebClientSession<AC> extends BaseWebClientSession<WebClient<AC>, AC> {

  //attribute
  private final IWebGui<?> webGui = new WebGui();

  //method
  public final IWebGui<?> getStoredGui() {
    return webGui;
  }

  //method
  @Override
  public final void refresh() {
    getStoredParentClient().internalUpdateCounterpartFromWebGui(getStoredGui(), true);
  }

  //method
  public final void updateControlOnCounterpart(final IControl<?, ?> control, final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlOnCounterpart(control, updateConstellationOrStyle);
  }

  //method
  public final void updateControlsOnCounterpart(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    getStoredParentClient().internalUpdateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  //method
  @Override
  protected final void fullInitialize() {

    getStoredGui()
      .setTitle(getApplicationName())
      .setFrontEndReaderAndFrontEndWriter(createFrontendReader(), createFrontendWriter());

    initialize();
  }

  //method declaration
  protected abstract void initialize();

  //method
  @Override
  protected final Class<?> getClientClass() {
    return WebClient.class;
  }
}
