package com.example.belajarsqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class mhsModel implements Parcelable {
    int id ;
    String nama;
    String nim;
    String nohp;

    public mhsModel(int id, String nama, String nim, String nohp) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.nohp = nohp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.nim);
        dest.writeString(this.nohp);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nama = source.readString();
        this.nim = source.readString();
        this.nohp = source.readString();
    }

    public mhsModel() {
    }

    protected mhsModel(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.nim = in.readString();
        this.nohp = in.readString();
    }

    public static final Creator<mhsModel> CREATOR = new Creator<mhsModel>() {
        @Override
        public mhsModel createFromParcel(Parcel source) {
            return new mhsModel(source);
        }

        @Override
        public mhsModel[] newArray(int size) {
            return new mhsModel[size];
        }
    };
}
