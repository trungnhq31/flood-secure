package com.example.prm392_flood_secure.data.remote;

import java.util.List;

public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Meta meta;
    private ErrorDetail error;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public Meta getMeta() { return meta; }
    public ErrorDetail getError() { return error; }

    public static class Meta {
        private int total;
        private int page;
        private int limit;
        private int totalPages;

        public int getTotal() { return total; }
        public int getPage() { return page; }
        public int getLimit() { return limit; }
        public int getTotalPages() { return totalPages; }
    }

    public static class ErrorDetail {
        private String code;
        private Object details; // Can be List of String or List of Objects

        public String getCode() { return code; }
        public Object getDetails() { return details; }
    }
}
