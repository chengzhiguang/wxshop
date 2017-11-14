package com.chengzg.wxshop.exception;

/**
 * Created by czg on 2016/10/31.
 */
public class TransactTypeException extends ServiceException {

    public TransactTypeException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
