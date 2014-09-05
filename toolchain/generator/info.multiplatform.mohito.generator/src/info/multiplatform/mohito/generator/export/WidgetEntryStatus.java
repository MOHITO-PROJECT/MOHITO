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

import org.eclipse.core.runtime.IStatus;

/**Status used to validate the entry of widgets in wizards.
 * @author hgroenda
 *
 */
public class WidgetEntryStatus implements IStatus {
	/** String representing unused entries of IStatus fields. */
	private static final String UNUSED_ENTRY = "not used";
	/** Severity of the status. */
	private final int severity;
	/** Status message / description. */
	private final String message;
	
	public WidgetEntryStatus(int severity, String message) {
		this.severity = severity;
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#getChildren()
	 */
	@Override
	public IStatus[] getChildren() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#getCode()
	 */
	@Override
	public int getCode() {
		return severity;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#getException()
	 */
	@Override
	public Throwable getException() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#getPlugin()
	 */
	@Override
	public String getPlugin() {
		return UNUSED_ENTRY;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#getSeverity()
	 */
	@Override
	public int getSeverity() {
		return severity;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#isMultiStatus()
	 */
	@Override
	public boolean isMultiStatus() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#isOK()
	 */
	@Override
	public boolean isOK() {
		if (severity == IStatus.WARNING || severity == IStatus.ERROR || severity == IStatus.INFO) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IStatus#matches(int)
	 */
	@Override
	public boolean matches(int severityMask) {
		return (severityMask & severity) > 0;
	}

}
