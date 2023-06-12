package com.zzzwww.tree.dto;

import lombok.Data;

@Data
public class TreeConfig {

    private String idKey = "id";

    private String parentIdKey = "parentId";

    private String children = "children";
}