package com.example.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.demo.DTO.Response;
import com.example.demo.entity.InvoicesEntity;
import com.example.demo.service.IInvoicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/invoices")
@Tag(name = "单据管理")
public class InvoicesController {
    @Autowired
    IInvoicesService InvoicesService;

    @PostMapping("/addInvoices")
    @Operation(summary = "添加单据")
    public Response addInvoices(Integer reqId, Date date) {
        InvoicesEntity invoicesEntity = new InvoicesEntity();
        String num = RandomUtil.randomString(10);
        String sec = RandomUtil.randomString(5);

        invoicesEntity.setReqId(reqId);
        invoicesEntity.setSec(sec);
        invoicesEntity.setNum(num);
        invoicesEntity.setDate(date);

        if (InvoicesService.addInvoices(invoicesEntity)){
            return new Response(200, "添加成功", null);
        }
        return new Response(400, "添加失败", null);
    }

    @DeleteMapping("/deleteInvoices")
    @Operation(summary = "单据ID删除单据")
    public Response deleteInvoices(Integer id) {
        if (InvoicesService.deleteInvoicesById(id)){
            return new Response(200, "删除成功", null);
        }
        return new Response(400, "删除失败", null);
    }

    @DeleteMapping("/deleteInvoicesByReqId")
    @Operation(summary = "需求单号ID删除单据")
    public Response deleteInvoicesByReqId(Integer reqId) {
        if (InvoicesService.deleteInvoicesByReqId(reqId)){
            return new Response(200, "删除成功", null);
        }
        return new Response(400, "删除失败", null);
    }

    @PostMapping("/selectAll")
    @Operation(summary = "查询所有单据")
    public Response selectAll() {
        List<InvoicesEntity> invoicesEntities = InvoicesService.selectAll();
        if (invoicesEntities==null)
            return new Response(400, "查询失败", null);
        return new Response(200, "查询成功", invoicesEntities);
    }

    @PostMapping("/selectInvoicesById")
    @Operation(summary = "根据单据ID查询单据")
    public Response selectInvoicesById(Integer id) {
        InvoicesEntity invoicesEntity = InvoicesService.selectInvoicesById(id);
        if (invoicesEntity==null)
            return new Response(400, "查询失败", null);
        return new Response(200, "查询成功", invoicesEntity);
    }

    @PostMapping("/selectInvoicesByReqId")
    @Operation(summary = "需求单号ID查询单据")
    public Response selectInvoicesByReqId(Integer reqId) {
        List<InvoicesEntity> invoicesEntities = InvoicesService.selectInvoicesByReqId(reqId);
        if (invoicesEntities==null)
            return new Response(400, "查询失败", null);
        return new Response(200, "查询成功", invoicesEntities);
    }

    @PutMapping("/updateInvoices")
    @Operation(summary = "修改单据")
    public Response updateInvoices(Integer id, Integer reqId, String sec, Date date, String num) {
        InvoicesEntity invoicesEntity = new InvoicesEntity();
        invoicesEntity.setId(id);
        invoicesEntity.setReqId(reqId);
        invoicesEntity.setSec(sec);
        invoicesEntity.setDate(date);
        invoicesEntity.setNum(num);
        if (InvoicesService.updateInvoices(invoicesEntity))
            return new Response(200, "更新成功", null);
        return new Response(400, "更新失败",null);
    }

}
