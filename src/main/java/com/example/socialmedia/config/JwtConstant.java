package com.example.socialmedia.config;

public class JwtConstant {
    public static final String JWT_HEADER = "Authorization";
    public static final String SECRET_KEY = System.getenv("JWT_SECRET_KEY");
}

