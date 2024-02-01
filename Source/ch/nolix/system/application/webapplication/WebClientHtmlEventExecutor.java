//package declaration
package ch.nolix.system.application.webapplication;

//Java imports
import java.util.Optional;
import java.util.function.BooleanSupplier;

//own imports
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.applicationapi.componentapi.RefreshBehavior;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

//class
public final class WebClientHtmlEventExecutor {

  //method
  public void runHtmlEventOfTriggeredControlAndUpdateAccordingly(
    final IControl<?, ?> triggeredControl,
    final String htmlEvent,
    final BooleanSupplier openStateRequester,
    final Runnable guiUpdater,
    final Consumer<IControl<?, ?>> controlUpdater) {

    final var gui = triggeredControl.getStoredParentGui();
    final var originalLayerCount = gui.getLayerCount();

    triggeredControl.runHtmlEvent(htmlEvent);

    final var refreshBehaviour = getRefreshBehavior(triggeredControl, originalLayerCount, openStateRequester);

    switch (refreshBehaviour) {
      case REFRESH_GUI:
        guiUpdater.run();
        break;
      case REFRESH_SELF:
        final var controlToUpdate = getStoredControlToUpdateFromTriggeredConntrol(triggeredControl);
        controlUpdater.accept(controlToUpdate);
        break;
      default:
    }
  }

  //method
  private Optional<IControl<?, ?>> getOptionalStoredParentComponentOfControl(final IControl<?, ?> control) {

    if (control.isLinkedToAnObject()
    && control.getStoredLinkedObjects().getStoredFirst() instanceof IComponent component) {
      return Optional.of(component);
    }

    if (control.belongsToControl()) {
      return getOptionalStoredParentComponentOfControl(control.getStoredParentControl());
    }

    return Optional.empty();
  }

  //method
  private RefreshBehavior getRefreshBehavior(
    final IControl<?, ?> triggeredControl,
    final int originalLayerCount,
    final BooleanSupplier openStateRequester) {

    if (!openStateRequester.getAsBoolean()) {
      return RefreshBehavior.DO_NOT_REFRESH_ANYTHING;
    }

    if (!triggeredControl.belongsToGui()) {
      return RefreshBehavior.REFRESH_GUI;
    }

    final var gui = triggeredControl.getStoredParentGui();
    final var layerCount = gui.getLayerCount();

    if (layerCount != originalLayerCount || !gui.containsControl(triggeredControl)) {
      return RefreshBehavior.REFRESH_GUI;
    }

    return RefreshBehavior.REFRESH_SELF;
  }

  //method
  private IControl<?, ?> getStoredControlToUpdateFromTriggeredConntrol(final IControl<?, ?> triggeredControl) {

    final var componentContainer = getOptionalStoredParentComponentOfControl(triggeredControl);

    return componentContainer.orElse(triggeredControl);
  }
}
