package dev.legrug.positionalparser.parser.vo;


import dev.legrug.positionalparser.annotation.PositionalField;
import dev.legrug.positionalparser.annotation.PositionalList;
import dev.legrug.positionalparser.annotation.PositionalMonetary;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

@Getter @Setter
public class PositionalFieldVO {

    private PositionalListVO positionalListVO;
    private PositionalMonetaryVO positionalMonetaryVO;

    private int length;
    private String pattern;
    boolean trim;


    public PositionalFieldVO(Field currentJavaField) {
        PositionalField positionalField = currentJavaField.getAnnotation(PositionalField.class);
        if(positionalField != null)
        {
            this.length = positionalField.length();
            this.pattern = positionalField.pattern();
            this.trim = positionalField.trim();
            extractListInfoIfAny(positionalField);
            extractMonetaryInfoIfAny(positionalField);
        }
    }

    private void extractListInfoIfAny(PositionalField positionalField) {
        PositionalList positionalList = positionalField.listInfo();
        if(positionalList != null)
        {
            positionalListVO = PositionalListVO.fromAnnotaion(positionalList);
        }
    }

    private void extractMonetaryInfoIfAny(PositionalField positionalField) {
        PositionalMonetary positionalMonetary = positionalField.monetaryInfo();
        if(positionalMonetary != null)
        {
            positionalMonetaryVO = PositionalMonetaryVO.fromAnnotaion(positionalMonetary);
        }
    }

}
