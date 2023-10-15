//package declaration
package ch.nolix.template.webgui.dialog;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.skillapi.IBuilder;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class EnterValueDialogBuilder implements IBuilder<ILayer<?>> {

  // constant
  private static final String DEFAULT_INFO_TEXT = "Enter value";

  // constant
  private static final String DEFAULT_ORIGINAL_VALUE = StringCatalogue.EMPTY_STRING;

  // constant
  private static final String DEFAULT_CONFIRM_BUTTON_TEXT = StringCatalogue.FAT_CHECK_MARK;

  // constant
  private static final String DEFAULT_CANCEL_BUTTON_TEXT = StringCatalogue.LONG_LEFT_ARROW;

  // constant
  private static final IElementTaker<String> DEFAULT_VALUE_TAKER = (String value) -> {
  };

  // attribute
  private String infoText = DEFAULT_INFO_TEXT;

  // attribute
  private String originalValue = DEFAULT_ORIGINAL_VALUE;

  // attribute
  private String cancelButtonText = DEFAULT_CANCEL_BUTTON_TEXT;

  // attribute
  private String confirmButtonText = DEFAULT_CONFIRM_BUTTON_TEXT;

  // attribute
  private IElementTaker<String> valueTaker = DEFAULT_VALUE_TAKER;

  // method
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

  // method
  public EnterValueDialogBuilder setCancelButtonText(String cancelButtonText) {

    this.cancelButtonText = cancelButtonText;

    return this;
  }

  // method
  public EnterValueDialogBuilder setConfirmButtonText(final String confirmButtonText) {

    this.confirmButtonText = confirmButtonText;

    return this;
  }

  // method
  public EnterValueDialogBuilder setInfoText(final String infoText) {

    this.infoText = infoText;

    return this;
  }

  // method
  public EnterValueDialogBuilder setOriginalValue(final String originalValue) {

    this.originalValue = originalValue;

    return this;
  }

  // method
  public EnterValueDialogBuilder setValueTaker(final IElementTaker<String> valueTaker) {

    this.valueTaker = valueTaker;

    return this;
  }

  // method
  private void confirmNewValue(
      final IButton confirmButton,
      final ITextbox valueTextbox,
      final IElementTaker<String> valueTaker) {

    final var newValue = valueTextbox.getText();

    valueTaker.run(newValue);

    confirmButton.getStoredParentLayer().removeSelfFromGui();
  }

  // method
  private String getCancelButtonText() {
    return cancelButtonText;
  }

  // method
  private String getConfirmButtonText() {
    return confirmButtonText;
  }

  // method
  private String getInfoText() {
    return infoText;
  }
}
