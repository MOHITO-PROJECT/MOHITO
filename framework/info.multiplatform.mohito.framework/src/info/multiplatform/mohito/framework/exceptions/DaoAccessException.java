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

/**
 * @author egailus
 *
 */
public class DaoAccessException extends Exception {

	/**
	 * UID used during deserialization to verify that the sender and receiver of a serialized object 
	 * have loaded classes for that object that are compatible with respect to serialization.
	 */
	private static final long serialVersionUID = 4070998183777445543L;

	/**
	 * Constructor setting an additional message to describe the exception
	 * @param message to describe the exception
	 */
	public DaoAccessException(String message) {
        super(message);
    }

    /**
     * Constructor setting the throwable that is the cause for this exception
     * @param throwable	that is the cause for this exception
     */
    public DaoAccessException(Throwable throwable) {
        super(throwable);
    }
	
    /**
     * Constructor setting an additional message to describe the exception and
     * an additional message to describe the exception
     * @param message to describe the exception
     * @param throwable	that is the cause for this exception
     */
    public DaoAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }
	
}
