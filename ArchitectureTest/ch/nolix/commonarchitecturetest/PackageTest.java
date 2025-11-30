package ch.nolix.commonarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class PackageTest {
  @Test
  void testCase_packagesHaveAMaxHierarchyDepthOf5() {
    //parameter definition
    final var maxPackageHierarchyDepth = 5;

    //setup
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

    //execution & verification
    rule.check(testUnit);
  }
}
