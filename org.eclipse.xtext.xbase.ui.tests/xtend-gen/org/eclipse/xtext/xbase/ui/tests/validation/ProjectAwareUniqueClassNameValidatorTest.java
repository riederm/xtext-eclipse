/**
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.ui.tests.validation;

import java.util.Collections;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.ui.tests.validation.MockedProjectAwareUniqueClassNameValidator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Holger Schill - Initial contribution and API
 */
@SuppressWarnings("all")
public class ProjectAwareUniqueClassNameValidatorTest {
  private MockedProjectAwareUniqueClassNameValidator classNameValidator = new MockedProjectAwareUniqueClassNameValidator();
  
  @Test
  public void testIfDerived_MavenPath() {
    final OutputConfiguration output = new OutputConfiguration("TEST");
    output.setOutputDirectory("xtend_gen");
    final OutputConfiguration.SourceMapping sourceMapping = new OutputConfiguration.SourceMapping("src/main/xtend_gen");
    output.getSourceMappings().add(sourceMapping);
    this.classNameValidator.getContext().put("ProjectAwareUniqueClassNameValidator.outputConfigs", Collections.<OutputConfiguration>unmodifiableList(CollectionLiterals.<OutputConfiguration>newArrayList(output)));
    IWorkspaceRoot _root = ResourcesPlugin.getWorkspace().getRoot();
    Path _path = new Path("foo/src/main/xtend_gen/org/eclipse/test/foo.bar");
    final IFile file = _root.getFile(_path);
    Assert.assertTrue(this.classNameValidator.isDerived(file));
  }
  
  @Test
  public void testIfDerived_MavenPath_2() {
    final OutputConfiguration output = new OutputConfiguration("TEST");
    output.setOutputDirectory("xtend_gen");
    this.classNameValidator.getContext().put("ProjectAwareUniqueClassNameValidator.outputConfigs", Collections.<OutputConfiguration>unmodifiableList(CollectionLiterals.<OutputConfiguration>newArrayList(output)));
    IWorkspaceRoot _root = ResourcesPlugin.getWorkspace().getRoot();
    Path _path = new Path("foo/src/main/xtend_gen/org/eclipse/test/foo.bar");
    final IFile file = _root.getFile(_path);
    Assert.assertFalse(this.classNameValidator.isDerived(file));
  }
  
  @Test
  public void testIfDerived_MavenPath_3() {
    final OutputConfiguration output = new OutputConfiguration("TEST");
    output.setOutputDirectory("xtend_gen");
    final OutputConfiguration.SourceMapping sourceMapping = new OutputConfiguration.SourceMapping("src/main/xtend_gen");
    output.getSourceMappings().add(sourceMapping);
    this.classNameValidator.getContext().put("ProjectAwareUniqueClassNameValidator.outputConfigs", Collections.<OutputConfiguration>unmodifiableList(CollectionLiterals.<OutputConfiguration>newArrayList(output)));
    IWorkspaceRoot _root = ResourcesPlugin.getWorkspace().getRoot();
    Path _path = new Path("foo/src/main/src/org/eclipse/test/foo.bar");
    final IFile file = _root.getFile(_path);
    Assert.assertFalse(this.classNameValidator.isDerived(file));
  }
  
  @Test
  public void testIfDerived_MavenPath_4() {
    final OutputConfiguration output = new OutputConfiguration("TEST");
    output.setOutputDirectory("xtend_gen");
    final OutputConfiguration.SourceMapping sourceMapping = new OutputConfiguration.SourceMapping("src/main/xtend_gen");
    output.getSourceMappings().add(sourceMapping);
    final OutputConfiguration.SourceMapping sourceMapping2 = new OutputConfiguration.SourceMapping("src/test/xtend_gen");
    output.getSourceMappings().add(sourceMapping2);
    this.classNameValidator.getContext().put("ProjectAwareUniqueClassNameValidator.outputConfigs", Collections.<OutputConfiguration>unmodifiableList(CollectionLiterals.<OutputConfiguration>newArrayList(output)));
    IWorkspaceRoot _root = ResourcesPlugin.getWorkspace().getRoot();
    Path _path = new Path("foo/src/test/xtend_gen/org/eclipse/test/foo.bar");
    final IFile file = _root.getFile(_path);
    Assert.assertTrue(this.classNameValidator.isDerived(file));
  }
  
  @Test
  public void testIfDerived_Plain() {
    final OutputConfiguration output = new OutputConfiguration("TEST");
    output.setOutputDirectory("xtend_gen");
    final OutputConfiguration.SourceMapping sourceMapping = new OutputConfiguration.SourceMapping("xtend_gen");
    output.getSourceMappings().add(sourceMapping);
    this.classNameValidator.getContext().put("ProjectAwareUniqueClassNameValidator.outputConfigs", Collections.<OutputConfiguration>unmodifiableList(CollectionLiterals.<OutputConfiguration>newArrayList(output)));
    IWorkspaceRoot _root = ResourcesPlugin.getWorkspace().getRoot();
    Path _path = new Path("foo/xtend_gen/org/eclipse/test/foo.bar");
    final IFile file = _root.getFile(_path);
    Assert.assertTrue(this.classNameValidator.isDerived(file));
  }
  
  @Test
  public void testIfDerived_Plain_1() {
    final OutputConfiguration output = new OutputConfiguration("TEST");
    output.setOutputDirectory("xtend_gen");
    this.classNameValidator.getContext().put("ProjectAwareUniqueClassNameValidator.outputConfigs", Collections.<OutputConfiguration>unmodifiableList(CollectionLiterals.<OutputConfiguration>newArrayList(output)));
    IWorkspaceRoot _root = ResourcesPlugin.getWorkspace().getRoot();
    Path _path = new Path("foo/xtend_gen/org/eclipse/test/foo.bar");
    final IFile file = _root.getFile(_path);
    Assert.assertTrue(this.classNameValidator.isDerived(file));
  }
}
