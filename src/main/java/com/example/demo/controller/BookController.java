package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.BuyBookDTO;
import com.example.demo.DTO.InBookDTO;
import com.example.demo.DTO.OutBookDTO;
import com.example.demo.DTO.Response;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BuyBookEntity;
import com.example.demo.entity.InBookEntity;
import com.example.demo.entity.OutBookEntity;
import com.example.demo.service.IBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/book")
@Tag(name = "书本管理")
public class BookController {
    @Autowired
    IBookService bookService;

//------------
// 实际业务操作
//------------
    @PostMapping("/work/orderBook")
    @Operation(summary = "业务购书操作")
    public Response buyBook(Integer buyBuyerId, Integer buyBookId,Integer buyQuantity, Date buyDate){
        BuyBookEntity buyBookEntity = new BuyBookEntity();
        buyBookEntity.setBuyBookId(buyBookId);
        buyBookEntity.setBuyBuyerId(buyBuyerId);
        buyBookEntity.setBuyQuantity(buyQuantity);
        buyBookEntity.setBuyDate(buyDate);
        buyBookEntity.setBuyState(1);
        if (!bookService.buyBook(buyBookEntity))
            return new Response(400, "添加购书信息失败", null);
        return new Response(200, "添加购书信息成功", null);
    }

    @PostMapping("/work/inBook")
    @Operation(summary = "业务入库书籍")
    public Response inBook(Integer buyId,Integer buyBookId,Date date){
        BuyBookEntity buyBookEntity = bookService.getBuyBookById(buyId);
        if (!bookService.updateBuyState(buyBookEntity.getBuyId()))
            return new Response(400, "修改购书状态失败", null);
        Integer buyQuantity = buyBookEntity.getBuyQuantity();
        if (!bookService.updateBookQuantity(buyBookId,buyQuantity))
            return new Response(400, "添加书籍数量失败", null);
        InBookEntity inBookEntity = new InBookEntity();
        inBookEntity.setInBookId(buyBookEntity.getBuyBookId());
        inBookEntity.setInQuantity(buyQuantity);
        inBookEntity.setInDate(date);
        if (!bookService.addInBook(inBookEntity))
            return new Response(400, "添加入库信息失败", null);
        return new Response(200, "添加入库信息成功", null);
    }

    @PostMapping("/work/outBook")
    @Operation(summary = "业务出库书籍")
    public Response outBook(Integer outBorrowerId,Integer outBookId,
                            Integer outQuantity,Date date,Integer outToId){
        OutBookEntity outBookEntity = new OutBookEntity();
        outBookEntity.setOutBookid(outBookId);
        outBookEntity.setOutBorrowerId(outBorrowerId);
        outBookEntity.setOutQuantity(outQuantity);
        outBookEntity.setOutDate(date);
        outBookEntity.setOutToId(outToId);
        if (!bookService.addOutBook(outBookEntity))
            return new Response(400, "添加出库信息失败", null);
        return new Response(200, "出库成功", null);
    }
//------------
// 书籍信息操作
//------------
    @PostMapping("/info/add")
    @Operation(summary = "添加图书信息")
    public Response addBookInfo(String name, String author,
                                String press, String description, Integer quantity,
                                Double price, Date publishdate) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(name);
        bookEntity.setBookAuthor(author);
        bookEntity.setBookPublisher(press);
        bookEntity.setBookQuantity(quantity);
        bookEntity.setBookDescription(description);
        bookEntity.setBookPrice(price);
        bookEntity.setBookPublishdate(publishdate);
        if (bookService.addBookInfo(bookEntity))
            return new Response(200, "添加成功", null);
        return new Response(400, "添加失败", null);
    }

    @DeleteMapping("/info/delete")
    @Operation(summary = "删除图书信息")
    public Response deleteBookInfo(Integer id) {
        if (bookService.deleteBookInfo(id))
            return new Response(200, "删除成功", null);
        return new Response(400, "删除失败", null);
    }

    @PutMapping("/info/update")
    @Operation(summary = "修改图书信息")
    public Response updateBookInfo(Integer id, String name, String author,
                                   String press, String description, Integer quantity,
                                   Double price, Date publishdate) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(id);
        bookEntity.setBookName(name);
        bookEntity.setBookAuthor(author);
        bookEntity.setBookPublisher(press);
        bookEntity.setBookQuantity(quantity);
        bookEntity.setBookDescription(description);
        bookEntity.setBookPrice(price);
        bookEntity.setBookPublishdate(publishdate);
        if (bookService.updateBookInfo(bookEntity))
            return new Response(200, "修改成功", null);
        return new Response(400, "修改失败", null);
    }

    @PutMapping("/info/updateQuantity")
    @Operation(summary = "添加/减少(使用负数)书籍数量")
    public Response updateBookQuantity(Integer id, Integer num) {
        if (bookService.updateBookQuantity(id,num))
            return new Response(200, "修改成功", null);
        return new Response(400, "修改失败", null);
    }

    @PostMapping("/info/getAll")
    @Operation(summary = "获取所有图书信息")
    public Response getAllBookInfo() {
        List<BookEntity> res = bookService.getBookInfoAll();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/info/getById")
    @Operation(summary = "通过ID获取图书信息")
    public Response getBookInfoById(Integer id) {
        BookEntity res = bookService.getBookInfoById(id);
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/info/getBookNum")
    @Operation(summary = "获取图书数量")
    public Response getNumOfBook() {
        Integer res = bookService.getBookInfoCount();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/info/getByName")
    @Operation(summary = "获取图书信息-书名")
    public Response getBookInfoByName(String name) {
        List<BookEntity> res = bookService.getBookInfoByName(name);
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/info/getByAuthor")
    @Operation(summary = "获取图书信息-作者")
    public Response getBookInfoByAuthor(String name) {
        List<BookEntity> res = bookService.getBookInfoByAuthor(name);
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/info/getByKey")
    @Operation(summary = "获取图书信息-关键词")
    public Response getBookInfoByKey(String name) {
        List<BookEntity> res = bookService.getBookInfoByKey(name);
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/info/getByKAP")
    @Operation(summary = "获取图书信息-关键词&分页(每页10项")
    public Response getBookInfoByKeyAndPage(String name,Integer page){
        List<BookEntity> res =bookService.getBookInfoByKeyAndPage(name,page);
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

//------------
// 库存记录操作：
//------------
    // 入库

    @PostMapping("/in/add")
    @Operation(summary = "添加入库信息")
    public Response addInBook(Integer inBookId, Integer quantity, Date date) {
        InBookEntity inBookEntity = new InBookEntity();
        inBookEntity.setInBookId(inBookId);
        inBookEntity.setInQuantity(quantity);
        inBookEntity.setInDate(date);
        if (bookService.addInBook(inBookEntity))
            return new Response(200, "添加成功", null);
        return new Response(400, "添加失败", null);
    }

    @DeleteMapping("/in/delete")
    @Operation(summary = "删除入库信息")
    public Response deleteInBook(Integer id) {
        if (bookService.deleteInBook(id))
            return new Response(200, "删除成功", null);
        return new Response(400, "删除失败", null);
    }

    @PutMapping("/in/update")
    @Operation(summary = "修改入库信息")
    public Response updateInBook(Integer id, Integer inBookId, Integer quantity, Date date) {
        InBookEntity inBookEntity = new InBookEntity();
        inBookEntity.setInId(id);
        inBookEntity.setInBookId(inBookId);
        inBookEntity.setInQuantity(quantity);
        inBookEntity.setInDate(date);
        if (bookService.updateInBook(inBookEntity))
            return new Response(200, "修改成功", null);
        return new Response(400, "修改失败", null);
    }

    @PostMapping("/in/getAll")
    @Operation(summary = "获取所有入库信息")
    public Response getAllInBook() {
        List<InBookEntity> res = bookService.getAllInBook();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/in/getInfo")
    @Operation(summary = "获取(带书籍名称)入库信息")
    public Response getInBookInfo(){
        List<InBookDTO> res = bookService.getInBookInfo();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    // 出库

    @PostMapping("/out/add")
    @Operation(summary = "添加出库信息")
    public Response addOutBook(Integer outBookId, Integer quantity, Date date) {
        OutBookEntity outBookEntity = new OutBookEntity();
        outBookEntity.setOutBookid(outBookId);
        outBookEntity.setOutQuantity(quantity);
        outBookEntity.setOutDate(date);
        if (bookService.addOutBook(outBookEntity))
            return new Response(200, "添加成功", null);
        return new Response(400, "添加失败", null);
    }

    @DeleteMapping("/out/delete")
    @Operation(summary = "删除出库信息")
    public Response deleteOutBook(Integer id) {
        if (bookService.deleteOutBook(id))
            return new Response(200, "删除成功", null);
        return new Response(400, "删除失败", null);
    }

    @PutMapping("/out/update")
    @Operation(summary = "修改出库信息")
    public Response updateOutBook(Integer id, Integer outBookId, Integer quantity, Date date) {
        OutBookEntity outBookEntity = new OutBookEntity();
        outBookEntity.setOutId(id);
        outBookEntity.setOutBookid(outBookId);
        outBookEntity.setOutQuantity(quantity);
        outBookEntity.setOutDate(date);
        if (bookService.updateOutBook(outBookEntity))
            return new Response(200, "修改成功", null);
        return new Response(400, "修改失败", null);
    }

    @PostMapping("/out/getAll")
    @Operation(summary = "获取所有出库信息")
    public Response getAllOutBook() {
        List<OutBookEntity> res = bookService.getAllOutBook();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/out/getInfo")
    @Operation(summary = "获取(带书籍名称)出库信息")
    public Response getOutBookInfo(){
        List<OutBookDTO> res = bookService.getOutBookInfo();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

//------------
// 购书记录操作：
//------------

    @PostMapping("/buy/add")
    @Operation(summary = "添加(只有)购书信息")
    public Response addBuyBook(Integer buyBuyerId, Integer buyBookId,
                               Integer buyQuantity, Date buyDate) {
        BuyBookEntity buyBookEntity = new BuyBookEntity();
        buyBookEntity.setBuyBuyerId(buyBuyerId);
        buyBookEntity.setBuyBookId(buyBookId);
        buyBookEntity.setBuyQuantity(buyQuantity);
        buyBookEntity.setBuyDate(buyDate);
        buyBookEntity.setBuyState(1);
        if (bookService.buyBook(buyBookEntity))
            return new Response(200, "添加成功", null);
        return new Response(400, "添加失败", null);
    }

    @DeleteMapping("/buy/delete")
    @Operation(summary = "删除购书信息")
    public Response deleteBuyBook(Integer id) {
        if (bookService.deleteBuyBook(id))
            return new Response(200, "删除成功", null);
        return new Response(400, "删除失败", null);
    }

    @PutMapping("/buy/update")
    @Operation(summary = "修改购书信息")
    public Response updateBuyBook(Integer id, Integer buyBuyerId, Integer buyBookId,
                                  Integer buyQuantity, Date buyDate) {
        BuyBookEntity buyBookEntity = new BuyBookEntity();
        buyBookEntity.setBuyId(id);
        buyBookEntity.setBuyBuyerId(buyBuyerId);
        buyBookEntity.setBuyBookId(buyBookId);
        buyBookEntity.setBuyQuantity(buyQuantity);
        buyBookEntity.setBuyDate(buyDate);
        if (bookService.updateBuyBook(buyBookEntity))
            return new Response(200, "修改成功", null);
        return new Response(400, "修改失败", null);
    }

    @PutMapping("/buy/updateState")
    @Operation(summary = "修改购书状态")
    public Response updateBuyState(Integer id) {
        if (bookService.updateBuyState(id))
            return new Response(200, "修改成功", null);
        return new Response(400, "修改失败", null);
    }

    @PostMapping("/buy/getAll")
    @Operation(summary = "获取所有购书信息")
    public Response getAllBuyBook() {
        List<BuyBookEntity> res = bookService.getAllBuyBook();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }

    @PostMapping("/buy/getInfo")
    @Operation(summary = "获取(带书籍名称)购书信息")
    public Response getBuyBookInfo(){
        List<BuyBookDTO> res = bookService.getBuyBookInfo();
        if (res!=null)
            return new Response(200, "获取成功", res);
        return new Response(400, "获取失败", null);
    }


}
