/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.main;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.systemapi.webapplication.component.RefreshTrigger;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class WebClientHtmlEventExecutor {
  private WebClientHtmlEventExecutor() {
  }

  public static void runHtmlEventOfTriggeredControlAndUpdateAccordingly(
    final Control<?, ?> triggeredControl,
    final String htmlEvent,
    final BooleanSupplier openStateRequester,
    final Runnable guiUpdater,
    final Consumer<Control<?, ?>> controlUpdater) {
    final var gui = triggeredControl.getStoredParentGui();
    final var originalLayerCount = gui.getLayerCount();

    triggeredControl.runHtmlEvent(htmlEvent);

    final var refreshBehaviour = getRefreshTrigger(triggeredControl, originalLayerCount, openStateRequester);

    switch (refreshBehaviour) {
      case REFRESH_GUI:
        guiUpdater.run();
        break;
      case REFRESH_COMPONENT:
        final var controlToUpdate = getStoredControlToUpdateFromTriggeredConntrol(triggeredControl);
        controlUpdater.accept(controlToUpdate);
        break;
      default:
    }
  }

  private static RefreshTrigger getRefreshTrigger(
    final Control<?, ?> triggeredControl,
    final int originalLayerCount,
    final BooleanSupplier openStateRequester) {
    if (!openStateRequester.getAsBoolean()) {
      return RefreshTrigger.DO_NOT_REFRESH;
    }

    if (!triggeredControl.belongsToGui()) {
      return RefreshTrigger.REFRESH_GUI;
    }

    final var gui = triggeredControl.getStoredParentGui();
    final var layerCount = gui.getLayerCount();

    if (layerCount != originalLayerCount || !gui.containsControl(triggeredControl)) {
      return RefreshTrigger.REFRESH_GUI;
    }

    return RefreshTrigger.REFRESH_COMPONENT;
  }

  private static Control<?, ?> getStoredControlToUpdateFromTriggeredConntrol(final Control<?, ?> triggeredControl) {
    final var componentContainer = ControlHelper.getOptionalStoredParentComponentOfControl(triggeredControl);

    if (componentContainer.isPresent()) {
      return componentContainer.get();
    }

    return triggeredControl;
  }
}
