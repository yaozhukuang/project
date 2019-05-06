package com.zw.yzk.learn.greendao.entity;


import com.zw.yzk.learn.greendao.IntegerConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * 用于测试，数据库创建类似这种方式
 */
@Entity
public class User {
    @Id
    private Long id;
    private String email;
    private String icon;
    private int userId;
    private String password;
    private String userName;
    private int type;
    private boolean login;
    @Convert(columnType = String.class, converter = IntegerConverter.class)
    private List<Integer> collectId;

    @Generated(hash = 1167637594)
    public User(Long id, String email, String icon, int userId, String password,
                String userName, int type, boolean login, List<Integer> collectId) {
        this.id = id;
        this.email = email;
        this.icon = icon;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.type = type;
        this.login = login;
        this.collectId = collectId;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public boolean getLogin() {
        return this.login;
    }
    public void setLogin(boolean login) {
        this.login = login;
    }
    public List<Integer> getCollectId() {
        return this.collectId;
    }
    public void setCollectId(List<Integer> collectId) {
        this.collectId = collectId;
    }
}
