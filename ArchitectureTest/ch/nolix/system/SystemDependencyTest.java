package ch.nolix.system;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class SystemDependencyTest {

  @Test
  void testCase_chnolixsystem_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.system..")
      .and()
      .haveNameNotMatching(".*Test$")
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

    //execution & verification
    rule.check(testUnit);
  }
}
