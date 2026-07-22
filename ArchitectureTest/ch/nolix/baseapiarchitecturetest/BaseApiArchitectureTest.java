/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapiarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class BaseApiArchitectureTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.baseapi..");

  @Test
  void testCase_cycles() {
    // setup
    final var rule = SlicesRuleDefinition.slices().matching("ch.nolix.baseapi.(*)..").should().beFreeOfCycles();

   // execute & verification
    rule.check(TEST_UNIT);
  }

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
        "java..");

   // execute & verification
    rule.check(TEST_UNIT);
  }
}
