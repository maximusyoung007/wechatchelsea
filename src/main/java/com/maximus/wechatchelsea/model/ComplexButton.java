package com.maximus.wechatchelsea.model;

import lombok.Data;

/**
 * 父按钮
 */
@Data
public class ComplexButton extends Button{
    private Button[] sub_button;
}
