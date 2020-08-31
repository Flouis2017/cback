package com.flouis.counter.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Transfer {
    private Long id;

    private Long uid;

    private String bank;

    private Integer type;

    private Integer moneytype;

    private BigDecimal money;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMoneytype() {
        return moneytype;
    }

    public void setMoneytype(Integer moneytype) {
        this.moneytype = moneytype;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}