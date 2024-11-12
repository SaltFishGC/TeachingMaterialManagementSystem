package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices", schema = "software")
public class InvoicesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @Schema(description = "发票id")
    private int id;
    @Basic
    @Column(name = "num")
    @Schema(description = "发票编号")
    private String num;
    @Basic
    @Column(name = "sec")
    @Schema(description = "单据对应号")
    private String sec;
    @Basic
    @Column(name = "date")
    @Schema(description = "单据日期")
    private Date date;
    @Basic
    @Column(name = "req_id")
    @Schema(description = "需求单id")
    private Integer reqId;
}
