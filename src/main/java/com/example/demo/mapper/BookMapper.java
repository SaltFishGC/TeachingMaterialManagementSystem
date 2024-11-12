package com.example.demo.mapper;

import com.example.demo.DTO.BuyBookDTO;
import com.example.demo.DTO.InBookDTO;
import com.example.demo.DTO.OutBookDTO;
import com.example.demo.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

//------------
// 书籍信息操作：
//------------
    //book 表：
    //查找书籍消息

    @Select("select * from book")
    List<BookEntity> getBookInfoAll();

    @Select("select count(*) from book")
    Integer getBookInfoCount();//获取书籍种类总数

    @Select("select * from book where book_id = #{id}")
    BookEntity getBookInfoById(Integer id);

    @Select("select * from book where book_name like concat('%',#{name},'%') ")
    List<BookEntity> getBookInfoByName(String name);

    @Select("select * from book where book_author like concat('%',#{name},'%')")
    List<BookEntity> getBookInfoByAuthor(String name);

    @Select({
            "select * from book ",
            "where book_name like concat('%',#{name},'%') ",
            "or book_author like concat('%',#{name},'%') ",
            "or book_publisher like concat('%',#{name},'%')",
            "or book_description like concat('%',#{name},'%')"
        })
    List<BookEntity> getBookInfoByKey(String name);

    @Select({
            "select * from book ",
            "where book_name like concat('%',#{name},'%') ",
            "or book_author like concat('%',#{name},'%') ",
            "or book_publisher like concat('%',#{name},'%')",
            "or book_description like concat('%',#{name},'%')",
            "limit #{line},10"
    })
    List<BookEntity> getBookInfoByKeyAndPage1(String name, Integer line);

    @Select({
            "select * from book ",
            "limit #{line},10"
    })
    List<BookEntity> getBookInfoByKeyAndPage2(Integer line);

    //删除书籍（以id）

    @Delete("delete from book where book_id = #{id}")
    Integer deleteBookInfo(Integer id);

    //插入书籍信息

    @Insert("insert into book(book_name,book_author,book_publisher," +
            "book_publishdate,book_price,book_quantity,book_description) " +
            "values(#{bookName},#{bookAuthor},#{bookPublisher},#{bookPublishdate},#{bookPrice},#{bookQuantity},#{bookDescription})")
    Integer addBookInfo(BookEntity bookEntity);

    //更新书籍信息

    @Update("update book set book_name = #{bookName},book_author = #{bookAuthor},book_publisher = #{bookPublisher},"+
            "book_publishdate = #{bookPublishdate},book_price = #{bookPrice},book_quantity = #{bookQuantity},book_description = #{bookDescription} where book_id = #{bookId}")
    Integer updateBookInfo(BookEntity bookEntity);

    //更新书籍数量

    @Update("update book set book_quantity = book_quantity + (#{num}) where book_id = #{id}")
    Integer updateBookQuantity(Integer id, Integer num);

//------------------------------------------------------------------------------------------------

//------------
// 购书记录操作：
//------------
    //buy购书表：
    //添加购买书籍消息

    @Insert("insert into buy_book(buy_buyer_id,buy_book_id,buy_quantity,buy_date,buy_state) " +
            "VALUES (#{buyBuyerId}, #{buyBookId}, #{buyQuantity}, #{buyDate}, #{buyState})")
    Integer addBuyBook(BuyBookEntity buyBookEntity);

    //修改购书记录
    @Update("update buy_book set buy_buyer_id = #{buyBuyerId},buy_book_id = #{buyBookId}," +
            "buy_quantity = #{buyQuantity},buy_date = #{buyDate},buy_state = #{buyState} where buy_id = #{buyId}")
    Integer updateBuyBook(BuyBookEntity buyBookEntity);

    //更新购买状态（书籍入库

    @Update("update buy_book set buy_state = 2" +
            " where buy_id = #{id}")
    Integer updateBuyState(Integer id);

    //删除购书记录

    @Delete("delete from buy_book where buy_id = #{id}")
    Integer deleteBuyBook(Integer id);

    //查询购书记录
    @Select("select * from buy_book")
    List<BuyBookEntity> getAllBuyBook();

    @Select("select * from buy_book where buy_id = #{id}")
    BuyBookEntity getBuyBookById(Integer id);

    @Select("select buy_id,buy_buyer_id,buy_book_id,book_name,buy_quantity,buy_date,buy_state " +
            "FROM buy_book LEFT JOIN book ON buy_book.buy_book_id = book.book_id")
    List<BuyBookDTO> getBuyBookInfo();

//------------------------------------------------------------------------------------------------

//------------
// 库存记录操作：
//------------

    //inbook入库表：
    //添加书籍入库记录

    @Insert("insert into in_book(in_book_id,in_quantity,in_date)" +
            "values(#{inBookId},#{inQuantity},#{inDate})")
    Integer addInBook(InBookEntity inBookEntity);

    //查询书籍入库记录

    @Select("select * from in_book")
    List<InBookEntity> getAllInBook();

    @Select("SELECT in_id,in_book_id,book_name,in_quantity,in_date " +
            "from in_book LEFT JOIN book ON in_book.in_book_id = book.book_id")
    List<InBookDTO> getInBookInfo();

    //删除书籍入库记录
    @Delete("delete from in_book where in_id = #{id}")
    Integer deleteInBook(Integer id);

    //修改书籍入库记录
    @Update("update in_book set in_book_id=#{inBookId},in_quantity=#{inQuantity}," +
            "in_date=#{inDate} where in_id=#{inId}")
    Integer updateInBook(InBookEntity inBookEntity);

    //outbook出库表：
    //添加书籍出库记录
    @Insert("INSERT INTO out_book(out_borrower_id, out_bookid, out_quantity, out_date,out_to_id) " +
            "VALUES (#{outBorrowerId}, #{outBookid}, #{outQuantity}, #{outDate},#{outToId})")
    Integer addOutBook(OutBookEntity outBookEntity);

    //查询出库记录
    @Select("SELECT * FROM out_book")
    List<OutBookEntity> getAllOutBook();

    @Select("SELECT out_id,out_borrower_id,out_bookid,book_name,out_quantity,out_date,out_to_id " +
            "FROM out_book LEFT JOIN book ON out_book.out_bookid = book.book_id")
    List<OutBookDTO> getOutBookInfo();

    //删除出库记录
    @Delete("DELETE FROM out_book WHERE out_id = #{id}")
    Integer deleteOutBook(Integer id);

    //修改出库记录
    @Update("UPDATE out_book SET out_borrower_id=#{outBorrowerId}, " +
            "out_bookid=#{outBookid}, out_quantity=#{outQuantity}, " +
            "out_date=#{outDate} WHERE out_id=#{outId}")
    Integer updateOutBook(OutBookEntity outBookEntity);



}

