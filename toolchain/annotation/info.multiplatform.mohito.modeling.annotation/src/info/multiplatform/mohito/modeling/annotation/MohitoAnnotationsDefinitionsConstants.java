/** 
 * Copyright (c) 2012-2014 MOHITO Project
 *
 * Licensed under the EUPL V.1.1
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.mohito.modeling.annotation;

/**Constants used in the definitions of MOHITO-Annotations.
 * 
 * @author hgroenda
 *
 */
public interface MohitoAnnotationsDefinitionsConstants {
	// Supported data types
	public static final String DATA_TYPE_BOOLEAN = "Boolean";
	public static final String DATA_TYPE_STRING = "String";
	public static final String DATA_TYPE_INTEGER = "Integer";
	public static final String DATA_TYPE_LONG = "Long";

	// Supported annotation target types
	public static final String TARGET_TYPE_EPACKAGE = "EPackage";
	public static final String TARGET_TYPE_ECLASS = "EClass";
	public static final String TARGET_TYPE_EATTRIBUTE = "EAttribute";
	public static final String TARGET_TYPE_EENUM = "EEnum";
}
