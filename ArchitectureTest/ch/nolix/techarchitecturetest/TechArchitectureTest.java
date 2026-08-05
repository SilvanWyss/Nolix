/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class TechArchitectureTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.tech..");

  @Test
  void testCase_dependencies() {
    // setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage(
        "ch.nolix.base..",
        "ch.nolix.baseapi..",
        "ch.nolix.system..",
        "ch.nolix.systemapi..",
        "ch.nolix.tech..",
        "ch.nolix.techapi..",
        "java..");

    // execute & verify
    rule.check(TEST_UNIT);
  }
}
