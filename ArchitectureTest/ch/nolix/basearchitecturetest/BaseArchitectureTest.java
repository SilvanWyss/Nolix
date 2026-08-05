/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basearchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class BaseArchitectureTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.base..");

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
        "com.tngtech.archunit..",
        "io.netty..",
        "java..",
        "javax..");

    // execute & verify
    rule.check(TEST_UNIT);
  }
}
