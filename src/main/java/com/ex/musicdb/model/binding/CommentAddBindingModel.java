package com.ex.musicdb.model.binding;

import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CommentAddBindingModel {


    @NotEmpty
    @Size(min = 5)
    private String textContent;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime addedOn;

    public CommentAddBindingModel() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentAddBindingModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentAddBindingModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
