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
package info.multiplatform.mohito.modeling.annotation.database;


import info.multiplatform.mohito.modeling.annotation.AbstractAnnotationPropertiesSection;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationCategoryDefinition;

import java.util.Arrays;
import java.util.Collection;

/** Tabbed property section for displaying and modifying the details of the MOHITO-Annotation Database.
 *  
 * @author bejan
 * @author hgroenda
 *
 */
public class DatabaseAnnotationPropertiesSection extends AbstractAnnotationPropertiesSection {
	private static final MohitoAnnotationCategoryDefinition DATABASE_CATEGORY = new DatabaseMohitoAnnotationCategory();

	@Override
	protected Collection<MohitoAnnotationCategoryDefinition> getCategoryDefinitions() {
		return Arrays.asList(DATABASE_CATEGORY);
	}

}
