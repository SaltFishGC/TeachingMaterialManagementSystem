package com.example.demo.service.Impl;

import com.example.demo.entity.InvoicesEntity;
import com.example.demo.mapper.InvoicesMapper;
import com.example.demo.service.IInvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoicesService implements IInvoicesService {
    @Autowired
    InvoicesMapper invoicesMapper;

    @Override
    public Boolean addInvoices(InvoicesEntity invoicesEntity) {
        return invoicesMapper.addInvoices(invoicesEntity) > 0;
    }

    @Override
    public Boolean deleteInvoicesById(Integer id) {
        return invoicesMapper.deleteInvoices(id) > 0;
    }

    @Override
    public Boolean deleteInvoicesByReqId(Integer reqId) {
        return invoicesMapper.deleteInvoicesByReqId(reqId) > 0;
    }

    @Override
    public List<InvoicesEntity> selectAll() {
        return invoicesMapper.selectAll();
    }

    @Override
    public List<InvoicesEntity> selectInvoicesByReqId(Integer reqId) {
        return invoicesMapper.selectInvoicesByReqId(reqId);
    }

    @Override
    public InvoicesEntity selectInvoicesById(Integer id) {
        return invoicesMapper.selectInvoicesById(id);
    }

    @Override
    public Boolean updateInvoices(InvoicesEntity invoicesEntity) {
        return invoicesMapper.updateInvoices(invoicesEntity) > 0;
    }
}
