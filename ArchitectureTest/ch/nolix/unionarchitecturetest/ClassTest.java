/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import ch.nolix.base.testing.archunit.ArchUnitRuleCatalog;

/**
 * @author Silvan Wyss
 */
final class ClassTest {
  @Test
  void testCase_publicClassesDoNotContainNestedClasses() {
    // setup
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix..");

   // execute & verification
    ArchUnitRuleCatalog.PUBLIC_CLASSES_DO_NOT_CONTAIN_NESTED_CLASSES.check(testUnit);
  }
}
