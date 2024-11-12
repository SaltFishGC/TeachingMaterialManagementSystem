package com.example.demo.service;

import com.example.demo.entity.InvoicesEntity;

import java.util.List;

public interface IInvoicesService {
    Boolean addInvoices(InvoicesEntity invoicesEntity);

    Boolean deleteInvoicesById(Integer id);

    Boolean deleteInvoicesByReqId(Integer reqId);

    List<InvoicesEntity> selectAll();

    List<InvoicesEntity> selectInvoicesByReqId(Integer reqId);

    InvoicesEntity selectInvoicesById(Integer id);

    Boolean updateInvoices(InvoicesEntity invoicesEntity);
}
