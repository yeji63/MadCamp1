package com.example.madcamp1;

import android.graphics.Bitmap;

public class PuzzleBlock {
    private Bitmap image;
    private int tag;
    private boolean empty;

    PuzzleBlock(Bitmap image, int tag, boolean empty) {
        this.image = image;
        this.tag = tag;
        this.empty = empty;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getTag() {
        return tag;
    }

    public boolean isEmpty() {
        return empty;
    }

}