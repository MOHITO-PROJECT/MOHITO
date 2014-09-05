/** 
 * Copyright (c) 2012-2014 ModAgile Mobile (http://www.modagile-mobile.de/) and others.
 *
 * Licensed under Eclipse Public License - v 1.0
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.modagile.generator.android.templates.service

import com.google.inject.Inject
import org.eclipse.xtext.generator.IFileSystemAccess


/**
 * Contains service specific generator methods and calls all service sub de.modagile.generator.android.templates
 * */
class ServiceTemplate {
	
	@Inject extension de.modagile.generator.android.templates.service.SyncAdapterServiceTemplate serviceAdapter
	@Inject extension de.modagile.generator.android.templates.service.AccountAuthenticatorTemplate auccountAuthenticator
	@Inject extension de.modagile.generator.android.templates.service.AccountAuthenticatorServiceTemplate accountAuthenticatorService
	
 	def generateServices (String packagePrefix, String appName , IFileSystemAccess fileSystemAccess, String outputConfiguration) { 
 		serviceAdapter.generateSyncAdapterService(packagePrefix, appName, fileSystemAccess, outputConfiguration);
 		auccountAuthenticator.generateAccountAuthenticator(packagePrefix, appName, fileSystemAccess, outputConfiguration);
 		accountAuthenticatorService.generateAccountAuthenticatorService(packagePrefix, appName, fileSystemAccess, outputConfiguration);
 	} 
}