package com.example.jamesli.codewarschallenge.error;


public class ErrorCategoryException extends Exception {

    private ErrorCategory mErrorCategory;

    public ErrorCategoryException(ErrorCategory errorCategory) {
        mErrorCategory = errorCategory;
    }

    public ErrorCategory getErrorCategory() {
        return mErrorCategory;
    }
}
