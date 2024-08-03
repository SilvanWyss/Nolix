//package declaration
package ch.nolix.systemtest.webguitest.basecontroltooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlHtmlBuilderTest<CHB extends IControlHtmlBuilder<C>, C extends IControl<C, ?>>
extends StandardTest {

  //method
  @Test
  final void testCase_createHtmlElementForNewControl() {

    //setup
    final var testUnit = createTestUnit();

    //execution
    final var result = testUnit.createHtmlElementForControl(createControl());

    //verification
    expect(result).hasStringRepresentation(getExpectedStringRepresentationOfCreatedHtmlElementForNewControl());
  }

  //method declaration
  protected abstract C createControl();

  //method declaration
  protected abstract CHB createTestUnit();

  //method declaration
  protected abstract String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl();
}
