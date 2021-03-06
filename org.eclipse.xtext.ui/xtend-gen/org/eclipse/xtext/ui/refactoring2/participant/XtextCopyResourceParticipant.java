/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.refactoring2.participant;

import com.google.inject.Inject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.CopyArguments;
import org.eclipse.ltk.core.refactoring.participants.CopyParticipant;
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.xtext.ide.refactoring.ResourceRelocationContext;
import org.eclipse.xtext.ui.refactoring2.participant.ResourceRelocationProcessor;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
@SuppressWarnings("all")
public class XtextCopyResourceParticipant extends CopyParticipant implements ISharableParticipant {
  @Inject
  private ResourceRelocationProcessor processor;
  
  private Change change;
  
  @Override
  public RefactoringStatus checkConditions(final IProgressMonitor pm, final CheckConditionsContext context) throws OperationCanceledException {
    try {
      this.change = this.processor.createChange(this.getName(), ResourceRelocationContext.ChangeType.COPY, pm);
      return this.processor.getIssues().getRefactoringStatus();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public Change createChange(final IProgressMonitor pm) throws CoreException, OperationCanceledException {
    return this.change;
  }
  
  @Override
  public String getName() {
    return "Xtext copy resource participant";
  }
  
  @Override
  protected boolean initialize(final Object element) {
    boolean _xblockexpression = false;
    {
      this.addElement(element, this.getArguments());
      _xblockexpression = true;
    }
    return _xblockexpression;
  }
  
  @Override
  public void addElement(final Object element, final RefactoringArguments arguments) {
    if ((arguments instanceof CopyArguments)) {
      if ((element instanceof IResource)) {
        final Object destination = ((CopyArguments)arguments).getDestination();
        if (((destination instanceof IFolder) || (destination instanceof IProject))) {
          IFile _switchResult = null;
          boolean _matched = false;
          if (destination instanceof IFolder) {
            _matched=true;
            _switchResult = ((IFolder)destination).getFile(((IResource)element).getName());
          }
          if (!_matched) {
            if (destination instanceof IProject) {
              _matched=true;
              _switchResult = ((IProject)destination).getFile(((IResource)element).getName());
            }
          }
          final IFile destinationFile = _switchResult;
          this.processor.addChangedResource(((IResource)element), ((IResource)element).getFullPath(), destinationFile.getFullPath());
        }
      }
    }
  }
}
