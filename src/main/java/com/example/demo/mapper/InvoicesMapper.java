package com.example.demo.mapper;

import com.example.demo.entity.InvoicesEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InvoicesMapper {
    // 通过单据ID查询单据
    @Select("select * from invoices where id = #{id}")
    InvoicesEntity selectInvoicesById(Integer id);

    // 通过需求单编号查询订单信息
    @Select("select * from invoices where req_id = #{reqId}")
    List<InvoicesEntity> selectInvoicesByReqId(Integer reqId);

    // 查询所有单据信息
    @Select("select * from invoices")
    List<InvoicesEntity> selectAll();

    // 添加单据信息
    @Insert("insert into invoices(req_id,sec,date,num) " +
            "values(#{reqId},#{sec},#{date},#{num})")
    Integer addInvoices(InvoicesEntity invoicesEntity);

    // 删除单据信息
    @Delete("delete from invoices where id = #{id}")
    Integer deleteInvoices(Integer id);

    // 通过单据编号删除订单信息
    @Insert("delete from invoices where req_id = #{reqId}")
    Integer deleteInvoicesByReqId(Integer reqId);

    // 修改单据信息
    @Insert("update invoices set req_id = #{reqId},sec = #{sec},date = #{date},num = #{num},date = #{date} where id = #{id}")
    Integer updateInvoices(InvoicesEntity invoicesEntity);
}
