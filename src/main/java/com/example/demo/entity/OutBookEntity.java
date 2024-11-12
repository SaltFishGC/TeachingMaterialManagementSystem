package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "出库信息")
@Entity
@Table(name = "out_book", schema = "software")
public class OutBookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "out_id")
    @Schema(description = "出库单id")
    private int outId;
    @Basic
    @Column(name = "out_borrower_id")
    @Schema(description = "出库人id")
    private Integer outBorrowerId;
    @Basic
    @Column(name = "out_bookid")
    @Schema(description = "出库书籍id")
    private Integer outBookid;
    @Basic
    @Column(name = "out_quantity")
    @Schema(description = "出库数量")
    private Integer outQuantity;
    @Basic
    @Column(name = "out_date")
    @Schema(description = "出库时间")
    private Date outDate;
    @Basic
    @Column(name = "out_to_id")
    @Schema(description = "接收人id")
    private Integer outToId;


}
