package ch.nolix.template.webgui.dialog;

import java.util.function.Consumer;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.objectcreation.builder.IBuilder;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webatomiccontrol.button.ButtonRole;
import ch.nolix.systemapi.webgui.basecontainer.ContainerRole;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.LayerRole;

public final class EnterValueDialogBuilder implements IBuilder<ILayer<?>> {
  private static final String DEFAULT_INFO_TEXT = "Enter value";

  private static final String DEFAULT_ORIGINAL_VALUE = StringCatalog.EMPTY_STRING;

  private static final String DEFAULT_CONFIRM_BUTTON_TEXT = StringCatalog.FAT_CHECK_MARK;

  private static final String DEFAULT_CANCEL_BUTTON_TEXT = StringCatalog.LONG_LEFT_ARROW;

  private static final Consumer<String> DEFAULT_VALUE_TAKER = //
  _ -> {
  };

  private String infoText = DEFAULT_INFO_TEXT;

  private String originalValue = DEFAULT_ORIGINAL_VALUE;

  private String cancelButtonText = DEFAULT_CANCEL_BUTTON_TEXT;

  private String confirmButtonText = DEFAULT_CONFIRM_BUTTON_TEXT;

  private Consumer<String> valueTaker = DEFAULT_VALUE_TAKER;

  @Override
  public ILayer<?> build() {
    Validator.assertThat(valueTaker).thatIsNamed("value taker").isNotNull();

    final var valueTextbox = new Textbox().setText(originalValue);

    return new Layer()
      .setRole(LayerRole.DIALOG_LAYER)
      .setRootControl(
        new VerticalStack()
          .setRole(ContainerRole.DIALOG_CONTAINER)
          .addControl(
            new Label()
              .setText(getInfoText()),
            new ValidationLabel(),
            valueTextbox,
            new HorizontalStack()
              .addControl(
                new Button()
                  .setRole(ButtonRole.CANCEL_BUTTON)
                  .setText(getCancelButtonText())
                  .setLeftMouseButtonPressAction(b -> b.getStoredParentLayer().removeSelfFromGui()),
                new Button()
                  .setRole(ButtonRole.CONFIRM_BUTTON)
                  .setText(getConfirmButtonText())
                  .setLeftMouseButtonPressAction(
                    () -> EnterValueDialogBuilderHelper.confirmNewValue(valueTextbox, valueTaker)))));
  }

  public EnterValueDialogBuilder setCancelButtonText(String cancelButtonText) {
    this.cancelButtonText = cancelButtonText;

    return this;
  }

  public EnterValueDialogBuilder setConfirmButtonText(final String confirmButtonText) {
    this.confirmButtonText = confirmButtonText;

    return this;
  }

  public EnterValueDialogBuilder setInfoText(final String infoText) {
    this.infoText = infoText;

    return this;
  }

  public EnterValueDialogBuilder setOriginalValue(final String originalValue) {
    this.originalValue = originalValue;

    return this;
  }

  public EnterValueDialogBuilder setValueTaker(final Consumer<String> valueTaker) {
    this.valueTaker = valueTaker;

    return this;
  }

  private String getCancelButtonText() {
    return cancelButtonText;
  }

  private String getConfirmButtonText() {
    return confirmButtonText;
  }

  private String getInfoText() {
    return infoText;
  }
}
