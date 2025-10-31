package ch.nolix.template.webgui.dialog;

import java.util.function.Consumer;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.objectcreation.builder.IBuilder;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webatomiccontrol.button.ButtonRole;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
import ch.nolix.systemapi.webgui.basecontainer.ContainerRole;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.LayerRole;

public final class ShowValueDialogBuilder implements IBuilder<ILayer<?>> {
  private static final String DEFAULT_VALUE_NAME = LowerCaseVariableCatalog.VALUE;

  private static final String DEFAULT_CONFIRM_BUTTON_TEXT = StringCatalog.LONG_LEFT_ARROW;

  private String valueName = DEFAULT_VALUE_NAME;

  private String value = StringCatalog.ZERO;

  private String confirmButtonText = DEFAULT_CONFIRM_BUTTON_TEXT;

  private Consumer<String> valueCopier;

  @Override
  public ILayer<?> build() {
    return new Layer()
      .setRole(LayerRole.DIALOG_LAYER)
      .setRootControl(
        new VerticalStack()
          .setRole(ContainerRole.DIALOG_CONTAINER)
          .addControl(
            new Label()
              .setText(getValueName() + ":"),
            new HorizontalStack()
              .addControl(
                new Label()
                  .setText(getValue()),
                new Button()
                  .setVisibility(hasValueCopier())
                  .setText("Copy")
                  .setLeftMouseButtonPressAction(
                    (IButton button) -> {
                      getValueCopier().accept(value);
                      button.getStoredParentLayer().removeSelfFromGui();
                    })),
            new Button()
              .setRole(ButtonRole.CONFIRM_BUTTON)
              .setText(getConfirmButtonText())
              .setLeftMouseButtonPressAction(b -> b.getStoredParentLayer().removeSelfFromGui())));
  }

  public ShowValueDialogBuilder setConfirmButtonText(final String confirmButtonText) {
    this.confirmButtonText = confirmButtonText;

    return this;
  }

  public ShowValueDialogBuilder setValue(final String value) {
    this.value = value;

    return this;
  }

  public ShowValueDialogBuilder setValueCopier(final Consumer<String> valueCopier) {
    this.valueCopier = valueCopier;

    return this;
  }

  public ShowValueDialogBuilder setValueName(final String valueName) {
    this.valueName = valueName;

    return this;
  }

  private void assertHasValueCopier() {
    if (!hasValueCopier()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "value copier");
    }
  }

  private String getConfirmButtonText() {
    return confirmButtonText;
  }

  private String getValue() {
    return value;
  }

  private Consumer<String> getValueCopier() {
    assertHasValueCopier();

    return valueCopier;
  }

  private String getValueName() {
    return valueName;
  }

  private boolean hasValueCopier() {
    return (valueCopier != null);
  }
}
