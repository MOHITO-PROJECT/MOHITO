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
package info.multiplatform.generator.java.templates

import java.util.List
import info.multiplatform.generator.java.helper.Pair


class UtilityFunctions {
	
	def commaSeparated(String prefix, List<String> list)  {
		var boolean second = false
		
		'''
		«if (list.size > 0) prefix»
		«FOR String s : list»
			«if (second) ',' »  
			«s»
			«var boolean bugHelperVariable = second = false»
		«ENDFOR»'''
		}
		
		def generateSetterGetterForAdditionalAttributes(List<Pair<String, String>> pairs) {
		'''
		«FOR Pair<String, String> pair : pairs»
			public void set«pair.secondElement.toFirstUpper»(«pair.firstElement» «pair.secondElement»){
				this.«pair.secondElement» = «pair.secondElement»;
			}
			
			public «pair.firstElement» get«pair.secondElement.toFirstUpper»(){
				return this.«pair.secondElement»;
			}
		«ENDFOR»
		'''
	}
}