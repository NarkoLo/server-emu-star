package fctk.server;

import ru_omsu_fctk_simpleserver.exception.ReaderException;

/**
 * воходящий запрос вида "запрос: json строка"
 */
public class Reader {
    private String endpoint;
    private String json;

    public Reader(String request) throws ReaderException {
        String[] str = request.split("::");
        if (str.length != 2) {
            throw new ReaderException("Некоректный запрос");
        }
        this.endpoint = str[0];
        this.json = str[1];
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getJson() {
        return json;
    }
}
