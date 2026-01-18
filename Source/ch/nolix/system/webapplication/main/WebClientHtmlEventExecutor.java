/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.main;

import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webapplication.component.RefreshTrigger;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class WebClientHtmlEventExecutor {
  public void runHtmlEventOfTriggeredControlAndUpdateAccordingly(
    final IControl<?, ?> triggeredControl,
    final String htmlEvent,
    final BooleanSupplier openStateRequester,
    final Runnable guiUpdater,
    final Consumer<IControl<?, ?>> controlUpdater) {
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

  private Optional<IControl<?, ?>> getOptionalStoredParentComponentOfControl(final IControl<?, ?> control) {
    if (control.isLinkedToAnObject()
    && control.getStoredLinkedObjects().getStoredFirst() instanceof final IComponent component) {
      return Optional.of(component);
    }

    if (control.belongsToControl()) {
      return getOptionalStoredParentComponentOfControl(control.getStoredParentControl());
    }

    return Optional.empty();
  }

  private RefreshTrigger getRefreshTrigger(
    final IControl<?, ?> triggeredControl,
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

  private IControl<?, ?> getStoredControlToUpdateFromTriggeredConntrol(final IControl<?, ?> triggeredControl) {
    final var componentContainer = getOptionalStoredParentComponentOfControl(triggeredControl);

    return componentContainer.orElse(triggeredControl);
  }
}
