package ch.nolix.techarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class TechDependencyTest {

  @Test
  void testCase_chnolixtech_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.tech..")
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
        "java..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.tech..");

    //execution & verification
    rule.check(testUnit);
  }
}
