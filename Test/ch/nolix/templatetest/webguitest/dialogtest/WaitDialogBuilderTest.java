//package declaration
package ch.nolix.templatetest.webguitest.dialogtest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;
import ch.nolix.template.webgui.dialog.WaitDialogBuilder;

//class
final class WaitDialogBuilderTest extends StandardTest {

  //method
  @Test
  void testCase_build() {

    //setup
    final var testUnit = new WaitDialogBuilder();

    //execution
    final var result = testUnit.build();

    //verification part 1
    final var expectedStructure = new Layer().setRootControl(new VerticalStack().addControl(new Label()));
    expect(result.getStructureSpecification())
      .hasSameStringRepresentationAs(expectedStructure.getStructureSpecification());

    //verification part 2
    expect(result.getRole()).is(LayerRole.DIALOG_LAYER);
  }
}
