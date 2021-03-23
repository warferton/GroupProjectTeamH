package com.teamh.teamhfinalproject.ui;

import com.google.gson.JsonObject;

public class ObjectExample {
    public ObjectExample(String i)
    {
        JsonObject myJB = new JsonObject();
        myJB.addProperty("this_name", i);
    }
}
