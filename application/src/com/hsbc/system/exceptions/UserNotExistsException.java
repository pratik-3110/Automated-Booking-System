package com.hsbc.system.exceptions;

/**
 * 
 * @author The Xceptionals
 * Throw this exception if user with same field values already exists
 *
 */
public class UserNotExistsException extends Exception {

	@Override
	public String toString() {
		return "User not Exists";
	}
}
