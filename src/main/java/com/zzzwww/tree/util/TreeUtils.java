package com.zzzwww.tree.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.zzzwww.tree.dto.TreeConfig;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeUtils {

    public static <T, E> List<T> buildTree(List<T> listParam, E parentId, TreeConfig treeConfig) {
        Map<Object, List<T>> listMap = listParam
                .stream()
                .filter(t -> Objects.nonNull(TreeUtils.getParentId(t, treeConfig))
                        && Objects.nonNull(TreeUtils.getId(t, treeConfig)))
                .collect(Collectors.groupingBy(x -> TreeUtils.getParentId(x, treeConfig)));
        List<T> oneLevel = listMap.get(parentId);
        if (CollUtil.isEmpty(oneLevel)) {
            return Collections.emptyList();
        }
        TreeUtils.handleTree(oneLevel, treeConfig, listMap);
        return oneLevel;
    }

    private static <T> void handleTree(List<T> children, TreeConfig treeConfig, Map<Object, List<T>> listMap) {
        for (T t : children) {
            Object id = TreeUtils.getId(t, treeConfig);
            List<T> childrens = listMap.get(id);
            if (CollUtil.isNotEmpty(childrens)) {
                TreeUtils.setChildren(t, treeConfig, childrens);
                TreeUtils.handleTree(childrens, treeConfig, listMap);
            }
        }
    }

    private static <T> Object getParentId(T t, TreeConfig treeConfig){
        Field[] fields = ReflectUtil.getFields(t.getClass());
        for (Field field : fields) {
            ReflectUtil.setAccessible(field);
            String name = field.getName();
            String parentIdKey = treeConfig.getParentIdKey();
            if (Objects.equals(name, parentIdKey)) {
                ReflectUtil.getFieldsValue(field);
                return ReflectUtil.getFieldValue(t, field);
            }
        }
        throw new RuntimeException("[TreeUtils.getFields]TreeConfig Error");
    }

    private static <T> Object getId(T t, TreeConfig treeConfig) {
        Field[] fields = ReflectUtil.getFields(t.getClass());
        for (Field field : fields) {
            ReflectUtil.setAccessible(field);
            String name = field.getName();
            String id = treeConfig.getIdKey();
            if (Objects.equals(name, id)) {
                return ReflectUtil.getFieldValue(t, field);
            }
        }
        throw new RuntimeException("[TreeUtils.getFields]TreeConfig Error");
    }


    private static <T> void setChildren(T t, TreeConfig treeConfig, List<T> childrens) {
        Field[] fields = ReflectUtil.getFields(t.getClass());
        for (Field field : fields) {
            ReflectUtil.setAccessible(field);
            String name = field.getName();
            String children = treeConfig.getChildren();
            if (Objects.equals(name, children)) {
                ReflectUtil.setFieldValue(t, field, childrens);
            }
        }
    }

}
