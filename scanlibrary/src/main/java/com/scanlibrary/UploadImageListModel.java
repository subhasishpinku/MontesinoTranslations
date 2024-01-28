package com.scanlibrary;

public class UploadImageListModel {
    int id;
    String name;
    String image_type;
    String file_size;
    String file_path;
    int words;
    String translate_from;
    String translate_to;
    int status;

    public UploadImageListModel(int id, String name, String image_type, String file_size, String file_path, int words, String translate_from, String translate_to, int status) {
        this.id = id;
        this.name = name;
        this.image_type = image_type;
        this.file_size = file_size;
        this.file_path = file_path;
        this.words = words;
        this.translate_from = translate_from;
        this.translate_to = translate_to;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage_type() {
        return image_type;
    }

    public String getFile_size() {
        return file_size;
    }

    public String getFile_path() {
        return file_path;
    }

    public int getWords() {
        return words;
    }

    public String getTranslate_from() {
        return translate_from;
    }

    public String getTranslate_to() {
        return translate_to;
    }

    public int getStatus() {
        return status;
    }
}
