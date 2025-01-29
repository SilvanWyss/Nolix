package ch.nolix.systemarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class SystemArchitectureTest {

  @Test
  void testCase_chnolixcore_package() {

    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.system..")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage(
        "ch.nolix.core..",
        "ch.nolix.coreapi..",
        "ch.nolix.system..",
        "ch.nolix.systemapi..",
        "java..",
        "javax..");

    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.system..");

    rule.check(testUnit);
  }
}
