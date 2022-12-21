package com.zzzwww.post.dto.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;

@Data
public class User {
    private Long id;

    private String name;

    private Integer age;

    private Integer sex;
    private Long updateUser;

    private Date updateTime;

    private Integer isDelete;

}

class CheckRequired {

    private static final String ID = "id";

    private void buildUserDeleted(User user) {
        user.setUpdateUser(111l);
        user.setUpdateTime(new Date());
        user.setIsDelete(1);
    }

    private <T> void buildUserReflect(T t) throws IllegalAccessException {
        Class<?> clzss = t.getClass();
        Field[] fields = clzss.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (Objects.equals("updateUser", name)) {
                field.set(t, 111l);
                continue;
            }
            if (Objects.equals("updateTime", name)) {
                field.set(t, new Date());
                continue;
            }
            if (Objects.equals("isDelete", name)) {
                field.set(t, 1);
            }
        }
    }


    private <T> void checkUserRequiredReflect(T t) throws IllegalAccessException {
        Class<?> clzss = t.getClass();
        Field[] fields = clzss.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (Objects.equals(ID, name)) {
                continue;
            }
            if (Objects.isNull(field.get(t))) {
                throw new RuntimeException("Please complete the form before submitting.");
            }
        }
    }
}