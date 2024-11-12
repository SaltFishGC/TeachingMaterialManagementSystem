package com.example.demo.service.Impl;

import com.example.demo.DTO.RecordInfoDTO;
import com.example.demo.DTO.RequireInfoDTO;
import com.example.demo.entity.RequireBookEntity;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.ReqBookMapper;
import com.example.demo.service.IRequireBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequireBookService implements IRequireBookService {
    @Autowired
    ReqBookMapper reqBookMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public Boolean addRequire(RequireBookEntity requireBookEntity) {
        try {
            return reqBookMapper.addRequire(requireBookEntity) > 0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateRequire(RequireBookEntity requireBookEntity) {
        try {
            return reqBookMapper.updateRequire(requireBookEntity.getReqId(), requireBookEntity.getReqIsfinished()) > 0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean deleteRequire(Integer reqId) {
        try {
            return reqBookMapper.deleteRequire(reqId) > 0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<RequireBookEntity> selectRequireByDemenderId(Integer reqDemenderId) {
        try {
            return reqBookMapper.selectRequireByDemenderId(reqDemenderId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RequireBookEntity> selectRequireByToId(Integer reqToId) {
        try {
            return reqBookMapper.selectRequireByToId(reqToId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RecordInfoDTO> getRecordInfo(Integer reqId) {
        try {
            return reqBookMapper.getRecordInfo(reqId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RequireInfoDTO> getRequireInfo(Integer reqToId) {
        try {
            return reqBookMapper.getRequireInfo(reqToId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RequireInfoDTO> getAllRequireInfo() {
        try {
            return reqBookMapper.getAllRequireInfo();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RequireInfoDTO> getAllRequireInfoByPage(Integer numSet, Integer timeSet, Integer page) {
        Integer line = (page-1)*10;
        if (numSet>0&&timeSet>0){
            System.out.println("getAllRequireInfoByPage-ERROR：set条件出错");
            return null;
        }
        try {
            if ((numSet==0&&timeSet==0)||numSet==1)
                return reqBookMapper.getAllRequireInfoByPage1(line);
            else if (numSet==2)
                return reqBookMapper.getAllRequireInfoByPage2(line);
            else if (timeSet==1)
                return reqBookMapper.getAllRequireInfoByPage3(line);
            else if (timeSet==2)
                return reqBookMapper.getAllRequireInfoByPage4(line);
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RequireInfoDTO> getAllRequireInfoByPageTest(Integer numSet,Integer timeSet,Integer page) {
        Integer line = (page-1)*10;
        try {
            return reqBookMapper.getAllRequireInfoByPage(numSet,timeSet,line);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Integer solveRequire(Integer reqId) {
        try {
            RequireBookEntity requireBookEntity = reqBookMapper.selectRequireById(reqId);
            Integer needNum = requireBookEntity.getReqQuantity();
            Integer bookNum = bookMapper.getBookInfoById(requireBookEntity.getReqBookId()).getBookQuantity();
            /*
                需求数量<=书籍数量:
                    返回 1
                    更新需求状态为已解决，待取
                    更新书籍数量
                需求数量>书籍数量:
                    返回 0
                    更新需求状态为正在处理
             */

            if (needNum<=bookNum){
                bookMapper.updateBookQuantity(requireBookEntity.getReqBookId(),-needNum);
                reqBookMapper.updateRequire(reqId,2);
                return 1;
            }else {
                reqBookMapper.updateRequire(reqId,1);
                return 0;
            }
        }catch (Exception e){
            return null;
        }
    }
}
