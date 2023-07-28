package com.bookingcar.web.rest.vm;

import org.springframework.web.multipart.MultipartFile;

public class ImagePayload {
    String imageDescription;
    MultipartFile image;

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }


}
