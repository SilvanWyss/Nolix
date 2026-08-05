package ch.nolix.baseapitestarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class BaseApiTestArchitectureTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.baseapitest..");

  @Test
  void testCase_dependencies() {
    // setup
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

    // execute & verify
    rule.check(TEST_UNIT);
  }
}
