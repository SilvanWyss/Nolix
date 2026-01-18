/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.templatearchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class TemplateArchitectureTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.template..");

  @Test
  void testCase_cycles() {
    //setup
    final var rule = SlicesRuleDefinition.slices().matching("ch.nolix.template.(*)..").should().beFreeOfCycles();

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
        "ch.nolix.core..",
        "ch.nolix.coreapi..",
        "ch.nolix.system..",
        "ch.nolix.systemapi..",
        "ch.nolix.tech..",
        "ch.nolix.techapi..",
        "ch.nolix.template..",
        "java..");

    //execution & verification
    rule.check(TEST_UNIT);
  }
}
