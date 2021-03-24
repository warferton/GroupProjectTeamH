package com.teamh.teamhfinalproject.api;

public enum ConnectionDetails {
    API_URL("https://openapi.etsy.com/v2/"),
    API_KEY("?api_key=fe9ajqvj4nsrgj2p7x5lnlc0"),
    USER_SECRET("");// TODO: idk if we would use authentication

    private final String uri_piece;

    ConnectionDetails(String uri_piece) {
        this.uri_piece = uri_piece;
    }

    public String getUri(){
        return uri_piece;
    }

}
