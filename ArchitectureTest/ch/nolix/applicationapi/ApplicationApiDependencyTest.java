package ch.nolix.applicationapi;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class ApplicationApiDependencyTest {

  @Test
  void testCase_chnolixapplicationapi_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.applicationapi..")
      .and()
      .haveNameNotMatching(".*Test$")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage(
        "ch.nolix.coreapi..",
        "ch.nolix.systemapi..",
        "ch.nolix.applicationapi..",
        "java..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.applicationapi..");

    //execution & verification
    rule.check(testUnit);
  }
}
