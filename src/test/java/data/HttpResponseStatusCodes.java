package data;

public enum HttpResponseStatusCodes {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    NOCONTENT(204, "NO CONTENT"),
    BADREQUEST(400, "BAD REQUEST"),
    NOTFOUND(404, "NOT FOUND");

    private final int code;
    private final String message;

    // Constructor
    HttpResponseStatusCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter methods
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

