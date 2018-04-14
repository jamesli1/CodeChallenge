package com.example.jamesli.codewarschallenge.error;

import com.example.jamesli.codewarschallenge.R;

public class ErrorHandler {

    public static int getMessageFor(Throwable throwable) {
        ErrorCategory errorCategory = ErrorCategory.translate(throwable);
        switch (errorCategory) {
            case CONNECTION_ERROR:
                return R.string.error_network_status;
            case SERVER_ERROR:
                return R.string.error_dialog_server_error;
            case GENERIC_ERROR:
                return R.string.error_message;
            case NOT_FOUND:
                return R.string.error_not_found;
            case BAD_REQUEST:
                return R.string.error_dialog_last_request_was_invalid;
            case CONFLICT:
                return R.string.error_dialog_conflict_message;
        }

        return R.string.error_message;
    }
}
