package com.example.jamesli.codewarschallenge.error;

import android.util.Log;

import java.io.IOException;

import retrofit2.HttpException;

public enum ErrorCategory {
    CONNECTION_ERROR,
    SERVER_ERROR,
    GENERIC_ERROR,
    NOT_FOUND,
    BAD_REQUEST,
    CONFLICT;

    private static final String TAG = ErrorCategory.class.getSimpleName();

    public static ErrorCategory translate(int httpErrorCode) {
        ErrorCategory errorCategory = null;

        if (HttpResponseCodeChecker.isHttpServerError(httpErrorCode)) {
            errorCategory = SERVER_ERROR;
        } else if (HttpResponseCodeChecker.isHttpNotFound(httpErrorCode)) {
            errorCategory = NOT_FOUND;
        } else if (HttpResponseCodeChecker.isHttpBadRequest(httpErrorCode)) {
            errorCategory = BAD_REQUEST;
        } else if (HttpResponseCodeChecker.isHttpAConflict(httpErrorCode)) {
            errorCategory = CONFLICT;
        } else {
            errorCategory = GENERIC_ERROR;
        }

        Log.v(TAG, String.format("Error code %s category %s", httpErrorCode, errorCategory.name()));

        return errorCategory;
    }

    public static ErrorCategory translate(Throwable e) {
        if (e == null) {
            return ErrorCategory.GENERIC_ERROR;
        } else if (e instanceof ErrorCategoryException) {
            ErrorCategoryException errorCategoryException = (ErrorCategoryException) e;
            return errorCategoryException.getErrorCategory();
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            return translate(httpException.code());
        } else if (e instanceof IOException) {
            return ErrorCategory.CONNECTION_ERROR;
        } else {
            return reportError("Unknown exception %s %s", e);
        }
    }

    private static ErrorCategory reportError(String stringFormat, Throwable e) {
        String msg = String.format(stringFormat,
                e.getClass().getSimpleName(),
                e.getMessage());
        Log.v(TAG, msg);
        return ErrorCategory.GENERIC_ERROR;
    }
}
