/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

/**
 * @author Silvan Wyss
 */
final class PackagesTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix..");

  @Test
  void testCase_packagesDoNotHaveCyclicDependencies() {
    // setup
    final var rule = SlicesRuleDefinition.slices().matching("ch.nolix.(**)..").should().beFreeOfCycles();

    // execute & verify
    rule.check(TEST_UNIT);
  }

  @Test
  void testCase_packagesHaveAMaxHierarchyDepthOf5() {
    // define test parameters
    final var maxPackageHierarchyDepth = 5;

    // setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .arePublic()
      .and()
      .areNotNestedClasses()
      .should()
      .haveNameMatching("ch[.]nolix([.][0-9a-zA-Z[-]]*){0," + (maxPackageHierarchyDepth - 1) + "}");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix...");

    // execute & verify
    rule.check(testUnit);
  }
}
