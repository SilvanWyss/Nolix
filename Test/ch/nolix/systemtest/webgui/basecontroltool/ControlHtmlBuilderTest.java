package ch.nolix.systemtest.webgui.basecontroltool;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;

public abstract class ControlHtmlBuilderTest<B extends IControlHtmlBuilder<C>, C extends IControl<C, ?>>
extends StandardTest {

  @Test
  final void testCase_createHtmlElementForNewControl() {

    //setup
    final B testUnit = createTestUnit();

    //execution
    final var result = testUnit.createHtmlElementForControl(createControl());

    //verification
    expect(result).hasStringRepresentation(getExpectedStringRepresentationOfCreatedHtmlElementForNewControl());
  }

  protected abstract C createControl();

  protected abstract B createTestUnit();

  protected abstract String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl();
}
