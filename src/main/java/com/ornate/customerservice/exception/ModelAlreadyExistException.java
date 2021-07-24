package com.ornate.customerservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This exception is thrown if the wanted model already exists.
 *
 * Author : Owolabi babalola Joseph
 * Email *: babs.owolabi@gmail.com
 * date **: November 16, 2016  14:43 PM
 * -------------------------------------------------------------
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ModelAlreadyExistException extends RuntimeException {

	private String fieldOrClassName;
	private String argumentSupplied;
	private String errorCode;

	public ModelAlreadyExistException(String fieldOrClassName, String argumentSupplied) {
		super(argumentSupplied);
		this.fieldOrClassName = fieldOrClassName;
		this.argumentSupplied = argumentSupplied;
	}

	public ModelAlreadyExistException(String fieldOrClassName, String argumentSupplied,String errorCode) {
		super(argumentSupplied);
		this.fieldOrClassName = fieldOrClassName;
		this.argumentSupplied = argumentSupplied;
		this.errorCode= errorCode;
	}

	public ModelAlreadyExistException(String argumentSupplied) {
		this.argumentSupplied = argumentSupplied;

	}
}
