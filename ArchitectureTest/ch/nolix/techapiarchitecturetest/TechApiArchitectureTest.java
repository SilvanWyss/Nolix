/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techapiarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class TechApiArchitectureTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.techapi..");

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
        "ch.nolix.systemapi..",
        "ch.nolix.techapi..",
        "java..");

    // execute & verify
    rule.check(TEST_UNIT);
  }
}
