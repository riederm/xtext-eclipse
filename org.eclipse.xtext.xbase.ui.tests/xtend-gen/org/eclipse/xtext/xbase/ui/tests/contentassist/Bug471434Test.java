/**
 * Copyright (c) 2015, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.xbase.ui.tests.contentassist;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractContentAssistTest;
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.xbase.testlanguages.ui.tests.ContentAssistFragmentTestLangUiInjectorProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test the content assist to make sure we inherit the default XbaseProposalProvider
 * implementation.
 * 
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=471434
 * 
 * @author Lorenzo Bettini - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(ContentAssistFragmentTestLangUiInjectorProvider.class)
@SuppressWarnings("all")
public class Bug471434Test extends AbstractContentAssistTest {
  @Test
  public void testVariableRightWithPrefix() throws Exception {
    ContentAssistProcessorTestBuilder _newBuilder = this.newBuilder();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val first = 0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val second = f");
    _newBuilder.append(_builder.toString()).assertProposal("first").withDisplayString("first");
  }
  
  @Test
  public void testVariableRight() throws Exception {
    ContentAssistProcessorTestBuilder _newBuilder = this.newBuilder();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val first = 0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val second = ");
    _newBuilder.append(_builder.toString()).assertProposal("first").withDisplayString("first");
  }
}
