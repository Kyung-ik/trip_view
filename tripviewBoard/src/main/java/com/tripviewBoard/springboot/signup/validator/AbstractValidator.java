package com.tripviewBoard.springboot.signup.validator;

import javax.xml.crypto.dsig.dom.DOMValidateContext;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractValidator<T> implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	//밑에껀 컴파일러에서 경고하지 않도록 하기 위해 사용함.
	@SuppressWarnings("unchecked")
	@Override
	public void validate(Object target, Errors errors) {
		try {
			doValidate((T) target, errors);
		}catch (RuntimeException e) {
			log.error("아이디 중복입니다.",e);
			throw e;
		}
	}
	protected abstract void doValidate(final T dto, final Errors errors);
}
