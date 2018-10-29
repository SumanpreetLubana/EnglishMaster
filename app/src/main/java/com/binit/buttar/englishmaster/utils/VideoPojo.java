package com.binit.buttar.englishmaster.utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoPojo {


    @SerializedName("success")
    String success;
    @SerializedName("data")
    List<VideoPojo.Data> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("id")
        String id;
        @SerializedName("video_ref")
        String video_ref;
        @SerializedName("name")
        String name;
        @SerializedName("title")
        String title;
        @SerializedName("url")
        String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideo_ref() {
            return video_ref;
        }

        public void setVideo_ref(String video_ref) {
            this.video_ref = video_ref;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
