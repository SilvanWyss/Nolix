package ch.nolix.templatetest.webgui.dialog;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabel;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.LabelRole;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.LayerRole;
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
