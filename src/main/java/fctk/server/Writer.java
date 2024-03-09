package fctk.server;

/**
 * вывод
 */
public class Writer {
    private String response;

    public Writer(String request) {
        this.response = request;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return response;
    }
}
