package com.firstmeridian.ipmgmt.common.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3155216047649520916L;
	/**
	 * 
	 */
	private String errorDescription;
	
	/**
     * Creates a new <code>BusinessException</code>.
     *
     * @param titleMessage
     *            error message
     */
    public BusinessException(String titleMessage, String description) { 
        super(titleMessage);
        this.errorDescription = description; 
    }

    /**
     * Creates a new <code>BusinessException</code> with attached original Throwable. Use only if the
     * Throwable is not transported over component boundaries this way (otherwise use the other
     * constructor!).
     *
     * @param message
     *            error message
     * @param cause
     *            original exception
     */
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
