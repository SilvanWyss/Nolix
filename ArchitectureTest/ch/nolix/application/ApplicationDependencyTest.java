package ch.nolix.application;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class ApplicationDependencyTest {

  @Test
  void testCase_chnolixapplicationapi_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.application..")
      .and()
      .haveNameNotMatching(".*Test$")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage(
        "ch.nolix.coreapi..",
        "ch.nolix.core..",
        "ch.nolix.systemapi..",
        "ch.nolix.system..",
        "ch.nolix.techapi..",
        "ch.nolix.tech..",
        "ch.nolix.template..",
        "ch.nolix.applicationapi..",
        "ch.nolix.application..",
        "java..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.application..");

    //execution & verification
    rule.check(testUnit);
  }
}
