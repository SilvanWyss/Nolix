package ch.nolix.template;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class TemplateDependencyTest {

  @Test
  void testCase_chnolixtemplate_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.template..")
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
        "java..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.template..");

    //execution & verification
    rule.check(testUnit);
  }
}
