//package declaration
package ch.nolix.templatetest.webguitest.dialogtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DialogTestPool extends TestPool {

  //constructor
  public DialogTestPool() {
    super(ShowValueDialogBuilderTest.class, WaitDialogBuilderTest.class, YesNoDialogBuilderTest.class);
  }
}
