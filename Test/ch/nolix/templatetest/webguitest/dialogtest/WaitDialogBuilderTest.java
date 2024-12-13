package ch.nolix.templatetest.webguitest.dialogtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;
import ch.nolix.template.webgui.dialog.WaitDialogBuilder;

final class WaitDialogBuilderTest extends StandardTest {

  @Test
  void testCase_build() {

    //setup
    final var testUnit = new WaitDialogBuilder();

    //execution
    final var result = testUnit.build();

    //verification
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
    final var controls = result.getStoredControls();
    expect(controls).contains(this::isMainLabel);
  }

  private boolean isMainLabel(final IControl<?, ?> control) {

    if (control instanceof final ILabel label) {
      return (label.getRole() == LabelRole.MAIN_LABEL);
    }

    return false;
  }
}
