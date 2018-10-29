package com.binit.buttar.englishmaster.utils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommonPojo {

    @SerializedName("success")
    String success;
    @SerializedName("data")
    ArrayList<Data> dataArrayList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<Data> getDataArrayList() {
        return dataArrayList;
    }

    public void setDataArrayList(ArrayList<Data> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    public class Data {
        @SerializedName("category_ref")
        String category_ref;
        @SerializedName("category_name")
        String category_name;

        @SerializedName("description")
        String description = null;

        @SerializedName("id")
        String id = null;
        @SerializedName("primary")
        String primary = null;
        @SerializedName("checked")
        String checked=null;

        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        public String getDescription() {
            return description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategory_ref() {
            return category_ref;
        }

        public void setCategory_ref(String category_ref) {
            this.category_ref = category_ref;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
