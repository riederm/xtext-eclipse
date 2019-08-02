/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.tests.validation

import java.util.Map
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.xtext.generator.OutputConfiguration
import org.eclipse.xtext.generator.OutputConfiguration.SourceMapping
import org.eclipse.xtext.xbase.ui.validation.ProjectAwareUniqueClassNameValidator
import org.junit.Test

import static org.junit.Assert.*

/**
 * @author Holger Schill - Initial contribution and API
 */
class ProjectAwareUniqueClassNameValidatorTest {

	MockedProjectAwareUniqueClassNameValidator classNameValidator = new MockedProjectAwareUniqueClassNameValidator()

	@Test
	def void testIfDerived_MavenPath() {
		val output = new OutputConfiguration("TEST")
		output.outputDirectory = "xtend_gen"
		val sourceMapping = new SourceMapping("src/main/xtend_gen")
		output.sourceMappings.add(sourceMapping)
		classNameValidator.context.put("ProjectAwareUniqueClassNameValidator.outputConfigs", #[output])
		val file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("foo/src/main/xtend_gen/org/eclipse/test/foo.bar"))
		assertTrue(classNameValidator.isDerived(file))
	}
	
	@Test
	def void testIfDerived_MavenPath_2() {
		val output = new OutputConfiguration("TEST")
		output.outputDirectory = "xtend_gen"
		classNameValidator.context.put("ProjectAwareUniqueClassNameValidator.outputConfigs", #[output])
		val file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("foo/src/main/xtend_gen/org/eclipse/test/foo.bar"))
		assertFalse(classNameValidator.isDerived(file))
	}
	
	@Test
	def void testIfDerived_MavenPath_3() {
		val output = new OutputConfiguration("TEST")
		output.outputDirectory = "xtend_gen"
		val sourceMapping = new SourceMapping("src/main/xtend_gen")
		output.sourceMappings.add(sourceMapping)
		classNameValidator.context.put("ProjectAwareUniqueClassNameValidator.outputConfigs", #[output])
		val file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("foo/src/main/src/org/eclipse/test/foo.bar"))
		assertFalse(classNameValidator.isDerived(file))
	}
	
	@Test
	def void testIfDerived_MavenPath_4() {
		val output = new OutputConfiguration("TEST")
		output.outputDirectory = "xtend_gen"
		val sourceMapping = new SourceMapping("src/main/xtend_gen")
		output.sourceMappings.add(sourceMapping)
		val sourceMapping2 = new SourceMapping("src/test/xtend_gen")
		output.sourceMappings.add(sourceMapping2)
		classNameValidator.context.put("ProjectAwareUniqueClassNameValidator.outputConfigs", #[output])
		val file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("foo/src/test/xtend_gen/org/eclipse/test/foo.bar"))
		assertTrue(classNameValidator.isDerived(file))
	}
	
	@Test
	def void testIfDerived_Plain() {
		val output = new OutputConfiguration("TEST")
		output.outputDirectory = "xtend_gen"
		val sourceMapping = new SourceMapping("xtend_gen")
		output.sourceMappings.add(sourceMapping)
		classNameValidator.context.put("ProjectAwareUniqueClassNameValidator.outputConfigs", #[output])
		val file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("foo/xtend_gen/org/eclipse/test/foo.bar"))
		assertTrue(classNameValidator.isDerived(file))
	}
	
	@Test
	def void testIfDerived_Plain_1() {
		val output = new OutputConfiguration("TEST")
		output.outputDirectory = "xtend_gen"
		classNameValidator.context.put("ProjectAwareUniqueClassNameValidator.outputConfigs", #[output])
		val file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("foo/xtend_gen/org/eclipse/test/foo.bar"))
		assertTrue(classNameValidator.isDerived(file))
	}

}

class MockedProjectAwareUniqueClassNameValidator extends ProjectAwareUniqueClassNameValidator {
	Map<Object, Object> context = newHashMap
	override getContext() {
		context
	}
	override isDerived(IResource resource) {
		super.isDerived(resource)
	}
}
