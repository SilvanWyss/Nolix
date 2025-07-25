package ch.nolix.systemapiarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

final class SystemApiDependencyTest {

  @Test
  void testCase_cycles() {

    //setup
    final var rule = SlicesRuleDefinition.slices().matching("ch.nolix.systemapi.(*)..").should().beFreeOfCycles();
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.systemapi..");

    //execution & verification
    rule.check(testUnit);
  }

  @Test
  void testCase_dependencies() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.systemapi..")
      .and()
      .haveNameNotMatching(".*Test$")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage("ch.nolix.coreapi..", "ch.nolix.systemapi..", "java..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.systemapi..");

    //execution & verification
    rule.check(testUnit);
  }
}
