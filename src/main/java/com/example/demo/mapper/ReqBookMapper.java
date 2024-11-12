package com.example.demo.mapper;

import com.example.demo.DTO.RecordInfoDTO;
import com.example.demo.DTO.RequireInfoDTO;
import com.example.demo.entity.RequireBookEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReqBookMapper {

//------------
// 需求业务操作：
//------------
    //require需求表：
    //添加需求

    @Insert("insert into require_book(req_demender_id,req_isfinished,req_book_id," +
            "req_quantity,req_date,req_reason,req_to_id) values(#{reqDemenderId}, #{reqIsfinished}, #{reqBookId}" +
            ", #{reqQuantity}, #{reqDate}, #{reqReason},#{reqToId})")
    Integer addRequire(RequireBookEntity requireBookEntity);

    //查询需求
    // 通过需求者编号查询
    @Select("select * from require_book where req_demender_id = #{reqDemenderId}")
    List<RequireBookEntity> selectRequireByDemenderId(Integer reqDemenderId);
    // 通过所负责管理员编号查询
    @Select("select * from require_book where req_to_id = #{reqToId}")
    List<RequireBookEntity> selectRequireByToId(Integer reqToId);
    // 通过需求编号查询
    @Select("select * from require_book where req_id = #{reqId}")
    RequireBookEntity selectRequireById(Integer reqId);

    //删除需求
    @Delete("delete from require_book where req_id = #{reqId}")
    Integer deleteRequire(Integer reqId);

    //修改需求状态
    @Insert("update require_book set req_isfinished = #{reqIsfinished} where req_id = #{reqId}")
    Integer updateRequire(Integer reqId, Integer reqIsfinished);


    //*special*
    //根据需求者ID联合查询得到记录订购书籍信息
    @Select("SELECT req_id,req_date,book_id,book_name,req_quantity,book_price,req_isfinished FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id WHERE require_book.req_demender_id = #{reqDemenderId}")
    List<RecordInfoDTO> getRecordInfo(Integer reqDemenderId);

    //根据管理员id联合查询得到需求信息
    @Select("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date,req_to_id" +
            " FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id" +
            " LEFT JOIN usr ON require_book.req_demender_id = usr.user_id" +
            " where req_to_id = #{reqToId}")
    List<RequireInfoDTO> getRequireInfo(Integer reqToId);

    //联合查询全部需求信息
    @Select("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date " +
            "FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id " +
            "LEFT JOIN usr ON require_book.req_demender_id = usr.user_id")
    List<RequireInfoDTO> getAllRequireInfo();

    //分页联合查询全部需求信息
    @Select("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date " +
            "FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id " +
            "LEFT JOIN usr ON require_book.req_demender_id = usr.user_id " +
            "ORDER BY req_id ASC limit #{line},10")
    List<RequireInfoDTO> getAllRequireInfoByPage1(Integer line);

    @Select("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date " +
            "FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id " +
            "LEFT JOIN usr ON require_book.req_demender_id = usr.user_id " +
            "ORDER BY req_id DESC limit #{line},10")
    List<RequireInfoDTO> getAllRequireInfoByPage2(Integer line);

    @Select("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date " +
            "FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id " +
            "LEFT JOIN usr ON require_book.req_demender_id = usr.user_id " +
            "ORDER BY req_date ASC limit #{line},10")
    List<RequireInfoDTO> getAllRequireInfoByPage3(Integer line);

    @Select("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date " +
            "FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id " +
            "LEFT JOIN usr ON require_book.req_demender_id = usr.user_id " +
            "ORDER BY req_date DESC limit #{line},10")
    List<RequireInfoDTO> getAllRequireInfoByPage4(Integer line);

    @SelectProvider(type = RequireInfoProvider.class, method = "getAllRequireInfoByPage")
    List<RequireInfoDTO> getAllRequireInfoByPage(@Param("numSet") Integer numSet,
                                                 @Param("timeSet") Integer timeSet,
                                                 @Param("page") Integer line);
}

class RequireInfoProvider {
    public String getAllRequireInfoByPage(Map<String,Object> Params){
        Integer numSet = (Integer) Params.get("numSet");
        Integer timeSet = (Integer) Params.get("timeSet");
        Integer line = (Integer) Params.get("line");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date ");
        sql.append("FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id ");
        sql.append("LEFT JOIN usr ON require_book.req_demender_id = usr.user_id ");

        if (numSet !=null && numSet != 0){
            if (numSet == 1){
                sql.append("ORDER BY req_id ASC ");
            }else if (numSet == 2){
                sql.append("ORDER BY req_id DESC ");
            }
        }else if(timeSet != null && timeSet != 0){
            if (timeSet == 1) {
                sql.append("ORDER BY req_date ASC ");
            } else if (timeSet == 2) {
                sql.append("ORDER BY req_date DESC ");
            }
        }
        sql.append("limit ").append(line).append(",10 ");
        return sql.toString();
    }
}


//class ReqBookProvider {
//
//    public String getRequireInfoByPageProvider(@Param("numSet") Integer numSet,@Param("timeSet") Integer timeSet,@Param("line") Integer line){
//        StringBuilder sql = new StringBuilder();
//
//        sql.append("SELECT req_id,user_id,user_name,book_id,book_name,req_quantity,req_isfinished,req_date ");
//        sql.append("FROM require_book LEFT JOIN book ON require_book.req_book_id = book.book_id ");
//        sql.append("LEFT JOIN usr ON require_book.req_demender_id = usr.user_id ");
//
//        if (numSet != 0){
//            if (numSet == 1){
//                sql.append("ORDER BY req_id ASC ");
//            }else if (numSet == 2){
//                sql.append("ORDER BY req_id DESC ");
//            }
//        }else if(timeSet != 0){
//            if (timeSet == 1) {
//                sql.append("ORDER BY req_date ASC ");
//            } else if (timeSet == 2) {
//                sql.append("ORDER BY req_date DESC");
//            }
//        }
//
//        sql.append("limit #{line},10 ");
//
//        System.out.println(sql.toString());
//
//        return sql.toString();
//    }
//}
