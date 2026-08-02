/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.dialog;

import ch.nolix.baseapi.objectcomposition.builder.IBuilder;
import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.label.Label;
import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.control.button.ButtonRole;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.control.container.ContainerRole;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.LayerRole;
import ch.nolix.template.webgui.textcatalog.SentenceCatalog;
import ch.nolix.template.webgui.textcatalog.TextCatalog;

/**
 * @author Silvan Wyss
 */
public final class ConfirmCookieDialogBuilder implements IBuilder<ILayer> {
  /**
   * {@inheritDoc}
   */
  @Override
  public ILayer build() {
    return //
    new Layer()
      .setRole(LayerRole.DIALOG_LAYER)
      .setRootControl(
        new VerticalStack()
          .setRole(ContainerRole.DIALOG_CONTAINER)
          .addControls(
            new Label()
              .setText(SentenceCatalog.THIS_PAGE_USES_COOKIES),
            new Button()
              .setRole(ButtonRole.CONFIRM_BUTTON)
              .setText(TextCatalog.OK)
              .setLeftMouseButtonPressAction(IButton::removeParentLayerFromGui)));
  }
}
