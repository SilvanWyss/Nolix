package ch.nolix.template.webgui.dialog;

import java.util.function.Consumer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.structurecontrolapi.builderapi.IBuilder;
import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.atomiccontrol.textbox.Textbox;
import ch.nolix.system.webgui.atomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

public final class EnterValueDialogBuilder implements IBuilder<ILayer<?>> {

  private static final String DEFAULT_INFO_TEXT = "Enter value";

  private static final String DEFAULT_ORIGINAL_VALUE = StringCatalogue.EMPTY_STRING;

  private static final String DEFAULT_CONFIRM_BUTTON_TEXT = StringCatalogue.FAT_CHECK_MARK;

  private static final String DEFAULT_CANCEL_BUTTON_TEXT = StringCatalogue.LONG_LEFT_ARROW;

  private static final Consumer<String> DEFAULT_VALUE_TAKER = (String value) -> {
  };

  private String infoText = DEFAULT_INFO_TEXT;

  private String originalValue = DEFAULT_ORIGINAL_VALUE;

  private String cancelButtonText = DEFAULT_CANCEL_BUTTON_TEXT;

  private String confirmButtonText = DEFAULT_CONFIRM_BUTTON_TEXT;

  private Consumer<String> valueTaker = DEFAULT_VALUE_TAKER;

  @Override
  public ILayer<?> build() {

    GlobalValidator.assertThat(valueTaker).thatIsNamed("value taker").isNotNull();

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
                  .setLeftMouseButtonPressAction(b -> confirmNewValue(b, valueTextbox, valueTaker)))));
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

  private void confirmNewValue(
    final IButton confirmButton,
    final ITextbox valueTextbox,
    final Consumer<String> valueTaker) {

    final var newValue = valueTextbox.getText();

    valueTaker.accept(newValue);

    confirmButton.getStoredParentLayer().removeSelfFromGui();
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
