package ch.nolix.coreapitest.programatomapitest.variableapitest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

final class PluralLowerCaseVariableCatalogueTest extends StandardTest {

  private static final IStringTool STRING_TOOL = new StringTool();

  @Test
  void testCase_constants() {

    //setup
    final var exceptions = //
    LinkedList.withElement(PluralLowerCaseVariableCatalogue.GUIS, PluralLowerCaseVariableCatalogue.URLS);

    //verification
    for (final var c : GlobalReflectionTool.getPublicStaticFieldValuesOfClass(PluralLowerCaseVariableCatalogue.class)) {

      expect(c).isOfType(String.class);

      final var stringValue = c.toString();
      if (!exceptions.containsEqualing(stringValue)) {
        expect(stringValue).fulfills(STRING_TOOL::isLowerCase);
      }
    }
  }
}
