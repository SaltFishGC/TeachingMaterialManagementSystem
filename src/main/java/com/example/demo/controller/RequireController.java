package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import com.example.demo.DTO.RecordInfoDTO;
import com.example.demo.DTO.RequireInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.Response;
import com.example.demo.entity.RequireBookEntity;
import com.example.demo.service.IRequireBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/require")
@Tag(name = "需求管理")
public class RequireController {
    @Autowired
    private IRequireBookService requireBookService;

    @PostMapping("/addRequire")
    @Operation(summary = "添加需求")
    public Response addRequire(Integer reqDemenderId, Integer reqBookId,
                               Integer reqQuantity, Date reqDate, String reqReason, Integer reqToId)
    {
        RequireBookEntity requireBookEntity = new RequireBookEntity();
        requireBookEntity.setReqDemenderId(reqDemenderId);
        /*
            reqIsfinished:
                0 未接收
                1 正在处理
                2 待取
                3 已完成
         */
        requireBookEntity.setReqIsfinished(0);
        requireBookEntity.setReqBookId(reqBookId);
        requireBookEntity.setReqQuantity(reqQuantity);
        requireBookEntity.setReqDate(reqDate);
        requireBookEntity.setReqReason(reqReason);
        requireBookEntity.setReqToId(reqToId);
        if (requireBookService.addRequire(requireBookEntity))
            return new Response(200, "添加成功", null);
        return new Response(400, "添加失败", null);
    }

    @DeleteMapping("/deleteRequire")
    @Operation(summary = "删除需求")
    public Response deleteRequire(Integer reqId){
        if (requireBookService.deleteRequire(reqId))
            return new Response(200, "删除成功", null);
        return new Response(400, "删除失败", null);
    }

    @PostMapping("/selectRequireByDemenderId")
    @Operation(summary = "根据需求者编号查询需求")
    public Response selectRequireByDemenderId(Integer reqDemenderId){
        List<RequireBookEntity> list = requireBookService.selectRequireByDemenderId(reqDemenderId);
        if (list==null)
            return new Response(400, "查询失败", null);
        if (list.size()==0)
            return new Response(300, "无此需求", null);
        return new Response(200, "查询成功", list);
    }

    @PostMapping("/selectRequireByToId")
    @Operation(summary = "根据接收者编号查询需求")
    public Response selectRequireByToId(Integer reqToId){
        List<RequireBookEntity> list = requireBookService.selectRequireByToId(reqToId);
        if (list==null)
            return new Response(400, "查询失败", null);
        if (list.size()==0)
            return new Response(300, "无此需求", null);
        return new Response(200, "查询成功", list);
    }

    @PutMapping("/updateRequire")
    @Operation(summary = "更新需求")
    public Response updateRequire(Integer reqId, Integer reqIsfinished){
        /*
            reqIsfinished:
                0 未接收
                1 正在处理
                2 待取
                3 已完成
         */
        RequireBookEntity requireBookEntity = new RequireBookEntity();
        requireBookEntity.setReqId(reqId);
        requireBookEntity.setReqIsfinished(reqIsfinished);
        if (requireBookService.updateRequire(requireBookEntity))
            return new Response(200, "更新成功", null);
        return new Response(400, "更新失败",null);
    }

    @PostMapping("/getRecordInfo")
    @Operation(summary = "(用户)获取(自己的)需求记录信息")
    public Response getRecordInfo(Integer Id){
        List<RecordInfoDTO> list = requireBookService.getRecordInfo(Id);
        if (list==null)
            return new Response(400, "查询失败", null);
        if (list.size()==0)
            return new Response(300, "无此需求", null);
        return new Response(200, "查询成功", list);
    }

    @PostMapping("/getAllRequireInfo")
    @Operation(summary = "(管理员)获取所有需求信息")
    public Response getAllRequireInfo(){
        List<RequireInfoDTO> list = requireBookService.getAllRequireInfo();
        if (list==null)
            return new Response(400, "查询失败", null);
        if (list.size()==0)
            return new Response(300, "无此需求", null);
        return new Response(200, "查询成功", list);
    }

    @PostMapping("/getAllRequireInfoByPage")
    @Operation(summary = "(管理员)获取所有需求信息(分页)")
    public Response getAllRequireInfoByPage(Integer numSet,Integer timeSet,Integer page){
        /*
            所得分页每页10项

            默认均为0，正常情况下只能有一个数>0以表示要用怎样的方式排序
            numSet:
                id:1 升序，2 降序
            timeSet:
                date:1 升序，2 降序
         */
        List<RequireInfoDTO> list = requireBookService.getAllRequireInfoByPage(numSet, timeSet, page);
        if (list==null)
            return new Response(400, "查询失败", null);
        if (list.size()==0)
            return new Response(300, "无需求", null);
        return new Response(200, "查询成功", list);
    }

    @Operation(summary = "测试(使用mybatis的provider重构而不是service选择mapper方法")
    public Response test(Integer numSet,Integer timeSet,Integer page){
        List<RequireInfoDTO> list = requireBookService.getAllRequireInfoByPage(numSet, timeSet, page);
        if (list==null)
            return new Response(400, "查询失败", null);
        if (list.size()==0)
            return new Response(300, "无此需求", null);
        if ((numSet > 0 && timeSet > 0)||(numSet > 2)||(timeSet > 2)||(numSet < 0)||(timeSet < 0))
            return new Response(400, "参数错误", null);
        return new Response(200, "查询成功", list);
    }

    @PostMapping("/solveRequire")
    @Operation(summary = "管理员处理需求")
    public Response solveRequire(Integer reqId){
        Integer res = requireBookService.solveRequire(reqId);
        if (res==1){
            return new Response(200, "已完成，待取", null);
        }else if (res==0){
            return new Response(300, "书籍数目不足", null);
        }else {
            return new Response(400, "处理异常", null);
        }
    }

}
