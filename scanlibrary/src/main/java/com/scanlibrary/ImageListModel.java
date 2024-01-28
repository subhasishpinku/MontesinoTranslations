package com.scanlibrary;

import android.net.Uri;

import java.io.Serializable;

public class ImageListModel implements Serializable{

    String originalLink;
    String magicColorLink;
    String greyScaleLink;
    String bwLink;
    String showLink;
    String originalUri;

    public ImageListModel(String originalUri, String originalLink, String magicColorLink, String greyScaleLink, String bwLink, String showLink) {
        this.originalLink = originalLink;
        this.magicColorLink = magicColorLink;
        this.greyScaleLink = greyScaleLink;
        this.bwLink = bwLink;
        this.showLink = showLink;
        this.originalUri = originalUri;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public String getMagicColorLink() {
        return magicColorLink;
    }

    public String getGreyScaleLink() {
        return greyScaleLink;
    }

    public String getBwLink() {
        return bwLink;
    }

    public String getShowLink() {
        return showLink;
    }

    public String getOriginalUri() {
        return originalUri;
    }
}
