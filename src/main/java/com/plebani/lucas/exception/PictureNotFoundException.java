package com.plebani.lucas.exception;

public class PictureNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PictureNotFoundException(String message) {
        super(message);
    }
}

