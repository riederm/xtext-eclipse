/*
 * generated by Xtext
 */
package org.eclipse.xtext.example.fowlerdsl

/** 
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
class StatemachineStandaloneSetup extends StatemachineStandaloneSetupGenerated {
	def static void doSetup() {
		new StatemachineStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
