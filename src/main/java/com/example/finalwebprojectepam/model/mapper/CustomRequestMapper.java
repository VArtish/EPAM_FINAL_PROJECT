package com.example.finalwebprojectepam.model.mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface CustomRequestMapper {
    Map<String, String> mapRequest(HttpServletRequest request);
}