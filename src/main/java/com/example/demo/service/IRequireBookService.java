package com.example.demo.service;

import com.example.demo.DTO.RecordInfoDTO;
import com.example.demo.DTO.RequireInfoDTO;
import com.example.demo.entity.RequireBookEntity;

import java.util.List;

public interface IRequireBookService {
    Boolean addRequire(RequireBookEntity requireBookEntity);
    Boolean updateRequire(RequireBookEntity requireBookEntity);
    Boolean deleteRequire(Integer reqId);

    List<RequireBookEntity> selectRequireByDemenderId(Integer reqDemenderId);
    List<RequireBookEntity> selectRequireByToId(Integer reqToId);
    List<RecordInfoDTO> getRecordInfo(Integer reqId);

    List<RequireInfoDTO> getRequireInfo(Integer reqToId);

    List<RequireInfoDTO> getAllRequireInfo();

    List<RequireInfoDTO> getAllRequireInfoByPage(Integer numSet, Integer timeSet, Integer line);

    List<RequireInfoDTO> getAllRequireInfoByPageTest(Integer numSet, Integer timeSet, Integer page);

    Integer solveRequire(Integer reqId);
}
