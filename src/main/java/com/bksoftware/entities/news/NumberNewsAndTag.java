package com.bksoftware.entities.news;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NumberNewsAndTag {

    private int newsNumber;

    private int tagNumber;

    public NumberNewsAndTag(){}
}
