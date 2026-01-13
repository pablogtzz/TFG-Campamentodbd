package com.example.practicatfgbd.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
    private String mensaje;
    private String url;

    public ApiResponse(String mensaje, String url) {
        this.mensaje = mensaje;
        this.url = url.replace("uri", "");
    }
}
