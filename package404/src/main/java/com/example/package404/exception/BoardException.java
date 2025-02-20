package com.example.package404.exception;

import com.example.package404.response.BoardResponseStatus;

public class BoardException extends BaseException {
    public BoardException(BoardResponseStatus status) {
        super(status);
    }
}
