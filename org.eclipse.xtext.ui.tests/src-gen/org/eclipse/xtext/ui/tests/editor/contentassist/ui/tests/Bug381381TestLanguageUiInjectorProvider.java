/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.eclipse.xtext.ui.tests.ui.internal.TestsActivator;

public class Bug381381TestLanguageUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return TestsActivator.getInstance().getInjector("org.eclipse.xtext.ui.tests.editor.contentassist.Bug381381TestLanguage");
	}

}
