package com.iextractor.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Image_From_Video implements Parcelable {
    private Bitmap bitmap;
    public Image_From_Video(){

    }

    public Image_From_Video(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    protected Image_From_Video(Parcel in) {
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Image_From_Video> CREATOR = new Creator<Image_From_Video>() {
        @Override
        public Image_From_Video createFromParcel(Parcel in) {
            return new Image_From_Video(in);
        }

        @Override
        public Image_From_Video[] newArray(int size) {
            return new Image_From_Video[size];
        }
    };

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bitmap, flags);
    }
}