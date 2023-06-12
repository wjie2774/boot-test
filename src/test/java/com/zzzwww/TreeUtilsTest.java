package com.zzzwww;

import com.zzzwww.tree.dto.TreeConfig;
import com.zzzwww.tree.util.TreeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeUtilsTest {

    private static final List<Category> CATEGORIES = Arrays.asList(
            new Category(1L, 0L, "Root"),
            new Category(2L, 1L, "Child 1"),
            new Category(3L, 1L, "Child 2"),
            new Category(4L, 2L, "Grandchild 1"),
            new Category(5L, 2L, "Grandchild 2")
    );

    @Test
    void buildTree() {
        List<Category> categories = new ArrayList<>(CATEGORIES);
        List<Category> tree = TreeUtils.buildTree(categories, 0L, new TreeConfig());

        Assertions.assertEquals(1L, tree.get(0).getId());
        Assertions.assertEquals("Root", tree.get(0).getName());
        Assertions.assertEquals(2L, tree.get(0).getChildren().get(0).getId());
        Assertions.assertEquals("Child 1", tree.get(0).getChildren().get(0).getName());
        Assertions.assertEquals(4L, tree.get(0).getChildren().get(0).getChildren().get(0).getId());
        Assertions.assertEquals("Grandchild 1", tree.get(0).getChildren().get(0).getChildren().get(0).getName());
        Assertions.assertEquals(5L, tree.get(0).getChildren().get(0).getChildren().get(1).getId());
        Assertions.assertEquals("Grandchild 2", tree.get(0).getChildren().get(0).getChildren().get(1).getName());
        Assertions.assertEquals(3L, tree.get(0).getChildren().get(1).getId());
        Assertions.assertEquals("Child 2", tree.get(0).getChildren().get(1).getName());

        // Make sure the original list is not modified
        Assertions.assertEquals(CATEGORIES, categories);
    }

    static class Category {
        private Long id;
        private Long parentId;
        private String name;
        private List<Category> children;

        public Category(Long id, Long parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Category> getChildren() {
            return children;
        }

        public void setChildren(List<Category> children) {
            this.children = children;
        }
    }

}
