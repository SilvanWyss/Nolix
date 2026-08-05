/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import ch.nolix.base.testing.archunit.ArchUnitRuleCatalog;

/**
 * @author Silvan Wyss
 */
final class ClassTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix..");

  @Test
  void testCase_publicClassesDoNotContainNestedClasses() {
    // execute & verify
    ArchUnitRuleCatalog.PUBLIC_CLASSES_DO_NOT_CONTAIN_NESTED_CLASSES.check(TEST_UNIT);
  }
}
