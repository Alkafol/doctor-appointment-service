package com.svi.group5.json;

import java.util.HashMap;

import static com.svi.group5.helpers.JsonMapper.jsonAsString;

public class AsyncRequest extends HashMap<String, Object> {
    @Override
    public String toString() { return jsonAsString(this); }
}
