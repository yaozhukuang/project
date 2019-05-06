package com.zw.yzk.learn.ui.example;

import com.google.gson.annotations.SerializedName;
import com.zw.yzk.learn.base.BaseEntity;

import java.util.List;

public class ExampleEntity extends BaseEntity {

    @SerializedName("data")
    public List<Banner> data;

    public static class Banner{
        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public String title;
        @SerializedName("url")
        public String url;
        @SerializedName("imagePath")
        public String imagePath;
    }
}
