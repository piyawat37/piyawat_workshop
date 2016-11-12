package com.example.evitected.workshop.datamodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Evitected on 12/11/2559.
 */
public class Result {

    @SerializedName("result")
    private int result;

    @SerializedName("result_desc")
    private String result_desc;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResult_desc() {
        return result_desc;
    }

    public void setResult_desc(String result_desc) {
        this.result_desc = result_desc;
    }
}
