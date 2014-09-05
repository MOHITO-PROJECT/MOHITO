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
package info.multiplatform.mohito.framework.exceptions;

import java.util.GregorianCalendar;

/**
 * @author Gerald.Huebsch
 *
 */
public class DataLayerException extends Exception {
	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = -1340341044403115545L;

	/**
	 * This code should specify the cause of the exception for exception handling.
	 */
	private String detailCode;
	
	/** Created date in ms. */
	private long createdOn = GregorianCalendar.getInstance().getTimeInMillis();

	/**
	 * Constructs a new exception.
	 * 
	 * @param message
	 *           a message
	 * @param detailCode
	 *           the detailcode indicating the reason for the exception
	 */
	public DataLayerException(final String message, final String detailCode) {
		super(message);
		this.detailCode = detailCode;
	}

	/**
	 * Constructs a new exception.
	 * 
	 * @param message
	 *           a message
	 * @param detailCode
	 *           the detailcode indicating the reason for the exception
	 * @param cause
	 *           a {@link Throwable} that caused this exception
	 */
	public DataLayerException(final String message, final String detailCode,
			final Throwable cause) {
		super(message, cause);
		this.detailCode = detailCode;
	}

	/**
	 * Returns the detailcode.
	 * 
	 * @return the detailcode
	 */
	public String getDetailCode() {
		return detailCode;
	}

	/**
	 * Sets the detailcode.
	 * 
	 * @param detailCode
	 *           the detailcode to set
	 */
	public void setDetailCode(final String detailCode) {
		this.detailCode = detailCode;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder(this.getClass().getName());
		buffer.append(":");
		buffer.append(getDetailCode());
		buffer.append(" - ");
		buffer.append(getMessage());
		buffer.append(" created on:");
		buffer.append(createdOn);
		
		return buffer.toString();
	}

	/**
	 * @return Returns the exception's creation date (in ms).
	 */
	public long getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn The created on time (ms) to set.
	 */
	public void setCreatedOn(final long createdOn) {
		this.createdOn = createdOn;
	}

}
