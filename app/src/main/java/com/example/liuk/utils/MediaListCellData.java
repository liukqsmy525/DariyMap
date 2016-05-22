package com.example.liuk.utils;

import com.example.liuk.myapplication.R;

/**
 * Created by LIUK on 2016/5/21.
 */
public class MediaListCellData {
    int id = -1;
    String path = "";

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    int type = 0;
    int iconId = R.mipmap.ic_launcher;

    public int getId() {
        return id;
    }

    public  MediaListCellData(String path)
    {
        this.path = path;

        if(path.endsWith(".jpg")){
            iconId = R.mipmap.icon_photo;
            type = MyConstants.MEDIA_TYPE_PHOTO;
        }
        else if(path.endsWith(".mp4")){
            iconId = R.mipmap.icon_video;
            type = MyConstants.MEDIA_TYPE_VEDIO;
        }
    }
    public  MediaListCellData(String path, int id)
    {
        this(path);
        this.id = id;
    }


}
