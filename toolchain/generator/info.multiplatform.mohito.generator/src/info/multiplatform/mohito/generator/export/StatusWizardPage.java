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
package info.multiplatform.mohito.generator.export;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Widget;

/**Generic functionality to show the most severe status message depending on available status messages.
 * @author hgroenda
 *
 */
public abstract class StatusWizardPage extends WizardPage {

	/** Used to track the status and errors for the different widgets. */
	private final Map<Widget, IStatus> statuses;
	
	/**Default constructor.
	 * @param pageName Name used for the page internally.
	 */
	public StatusWizardPage(String pageName) {
		super(pageName);
		this.statuses = new HashMap<Widget, IStatus>();
	}

	/**Generic handler for status messages in wizard pages.
	 * @param widget Widget reporting the status.
	 * @param newStatus The status.
	 */
	protected void updateStatus(Widget widget, IStatus newStatus) {
		statuses.put(widget, newStatus);
		// determine most severe status
		IStatus mostSevereStatus = null;
		for (IStatus status : statuses.values()) {
			if (mostSevereStatus != null) {
				mostSevereStatus = mostSevereStatus.getSeverity() > status.getSeverity() ? mostSevereStatus : status;
			} else {
				mostSevereStatus = status;
			}
 		}
		// set messages according to most severe status
		if (mostSevereStatus != null) {
			switch (mostSevereStatus.getSeverity()) {
			case IStatus.OK:
				setMessage(null, IMessageProvider.NONE);
				setErrorMessage(null);
				setPageComplete(true);
				break;
			case IStatus.WARNING:
				setMessage(mostSevereStatus.getMessage(), IMessageProvider.WARNING);
				setErrorMessage(null);
				setPageComplete(false);
				break;
			case IStatus.INFO:
				setMessage(mostSevereStatus.getMessage(), IMessageProvider.INFORMATION);
				setErrorMessage(null);
				setPageComplete(false);
				break;
			default:
				setMessage(null);
				setErrorMessage(mostSevereStatus.getMessage().length() == 0 ? null : mostSevereStatus.getMessage());
				setPageComplete(false);
				break;
			}
		} else {
			setMessage(null);
			setErrorMessage(null);
			setPageComplete(false);
		}
	}
	
}
