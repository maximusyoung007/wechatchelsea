package com.maximus.wechatchelsea.model;

import lombok.Data;

import java.util.List;

@Data
public class NewsMessage extends BaseMessage{
    private Integer FuncFlag;

    private Integer ArticleCount;

    private List<Article> Articles;
}
