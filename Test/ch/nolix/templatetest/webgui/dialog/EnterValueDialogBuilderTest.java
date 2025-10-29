package ch.nolix.templatetest.webgui.dialog;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.webgui.atomiccontrol.button.ButtonRole;
import ch.nolix.systemapi.webgui.atomiccontrol.button.IButton;
import ch.nolix.systemapi.webgui.atomiccontrol.textbox.ITextbox;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.LayerRole;
import ch.nolix.template.webgui.dialog.EnterValueDialogBuilder;

final class EnterValueDialogBuilderTest extends StandardTest {
  @Test
  void testCase_build() {
    //setup
    final var testUnit = new EnterValueDialogBuilder();

    //execution
    final var result = testUnit.build();

    //verification
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
    final var controls = result.getStoredControls();
    expect(controls).contains(this::isConfirmButton);
    expect(controls).contains(this::isCancelButton);
  }

  @Test
  @SuppressWarnings("unchecked")
  void testCase_build_thenClickCancelButton() {
    //setup
    final var testUnit = new EnterValueDialogBuilder();
    final var valueTakerMock = Mockito.mock(Consumer.class);
    testUnit.setValueTaker(valueTakerMock);

    //execution part 1
    final var result = testUnit.build();

    //execution part 2
    final var cancelButton = (IButton) result.getStoredControls().getStoredFirst(this::isCancelButton);
    cancelButton.pressLeftMouseButton();

    //verification
    Mockito.verify(valueTakerMock, Mockito.never()).accept(ArgumentMatchers.any());
  }

  @Test
  @SuppressWarnings("unchecked")
  void testCase_build_thenClickConfirmButton() {
    //setup
    final var testUnit = new EnterValueDialogBuilder();
    final var valueTakerMock = Mockito.mock(Consumer.class);
    testUnit.setValueTaker(valueTakerMock);

    //execution part 1
    final var result = testUnit.build();

    //execution part 2
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

    //verification
    Mockito.verify(valueTakerMock).accept(StringCatalog.EMPTY_STRING);
  }

  @Test
  @SuppressWarnings("unchecked")
  void testCase_build_thenAddToWebGui_thenClickCancelButton() {
    //parameter definition
    final var value = "777";

    //setup
    final var webGui = new WebGui();
    final var testUnit = new EnterValueDialogBuilder();
    final var valueTakerMock = Mockito.mock(Consumer.class);
    testUnit.setValueTaker(valueTakerMock);

    //execution part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

    //execution part 2
    final var valueTextbox = result.getStoredControls().getStoredFirstOfType(ITextbox.class);
    valueTextbox.setText(value);

    //execution part 3
    final var cancelButton = (IButton) result.getStoredControls().getStoredFirst(this::isCancelButton);
    cancelButton.pressLeftMouseButton();

    //verification
    expect(result.belongsToGui()).isFalse();
    Mockito.verify(valueTakerMock, Mockito.never()).accept(ArgumentMatchers.any());
  }

  @Test
  @SuppressWarnings("unchecked")
  void testCase_build_thenAddToWebGui_thenEnterValue_thenClickConfirmButton() {
    //parameter definition
    final var value = "777";

    //setup
    final var webGui = new WebGui();
    final var testUnit = new EnterValueDialogBuilder();
    final var valueTakerMock = Mockito.mock(Consumer.class);
    testUnit.setValueTaker(valueTakerMock);

    //execution part 1
    final var result = testUnit.build();
    webGui.pushLayer(result);

    //execution part 2
    final var valueTextbox = result.getStoredControls().getStoredFirstOfType(ITextbox.class);
    valueTextbox.setText(value);

    //execution part 3
    final var confirmButton = (IButton) result.getStoredControls().getStoredFirst(this::isConfirmButton);
    confirmButton.pressLeftMouseButton();

    //verification
    expect(result.belongsToGui()).isFalse();
    Mockito.verify(valueTakerMock).accept(value);
  }

  private boolean isCancelButton(final IControl<?, ?> control) {
    if (control instanceof final IButton button) {
      return (button.getRole() == ButtonRole.CANCEL_BUTTON);
    }

    return false;
  }

  private boolean isConfirmButton(final IControl<?, ?> control) {
    if (control instanceof final IButton button) {
      return (button.getRole() == ButtonRole.CONFIRM_BUTTON);
    }

    return false;
  }
}
