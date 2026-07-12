package ch.nolix.baseapitestarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class BaseApiTestArchitectureTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.baseapitest..");

  @Test
  void testCase_cycles() {
    //setup
    final var rule = SlicesRuleDefinition.slices().matching("ch.nolix.baseapitest.(*)..").should().beFreeOfCycles();

    //execution & verification
    rule.check(TEST_UNIT);
  }

  @Test
  void testCase_dependencies() {
    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage(
        "ch.nolix.baseapi..",
        "ch.nolix.baseapitest..",
        "ch.nolix.base..",
        "java..",
        "org.junit.jupiter..",
        "org.mockito..");

    //execution & verification
    rule.check(TEST_UNIT);
  }
}
