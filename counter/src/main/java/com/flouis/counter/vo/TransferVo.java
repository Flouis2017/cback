package com.flouis.counter.vo;

import com.flouis.counter.entity.Transfer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransferVo {
    private Long id;

    private String uid;

    private Integer bank;

    private Integer type;

    private Integer moneytype;

    private BigDecimal money;

    private Date createtime;

    private String password;

    public static Transfer toEntity(TransferVo vo){
        Transfer transfer = new Transfer();
        transfer.setId(vo.getId());
        transfer.setUid(vo.getUid());
        transfer.setBank(vo.getBank());
        transfer.setType(vo.getType());
        transfer.setMoneytype(vo.getMoneytype());
        transfer.setMoney(vo.getMoney());
        return transfer;
    }

}