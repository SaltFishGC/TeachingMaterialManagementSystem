package com.example.demo.service.Impl;

import com.example.demo.DTO.BuyBookDTO;
import com.example.demo.DTO.InBookDTO;
import com.example.demo.DTO.OutBookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BuyBookEntity;
import com.example.demo.entity.InBookEntity;
import com.example.demo.entity.OutBookEntity;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    BookMapper bookMapper;

//------------
// 书籍信息操作：
//------------

    @Override
    public Integer getNumOfBook(){
        return bookMapper.getBookInfoCount();
    }

    @Override
    public List<BookEntity> getBookInfoAll() {
        return bookMapper.getBookInfoAll();
    }

    @Override
    public List<BookEntity> getBookInfoByAuthor(String name) {
        return bookMapper.getBookInfoByAuthor(name);
    }

    @Override
    public BookEntity getBookInfoById(Integer id) {
        return bookMapper.getBookInfoById(id);
    }

    @Override
    public List<BookEntity> getBookInfoByKey(String name) {
        return bookMapper.getBookInfoByKey(name);
    }

    @Override
    public List<BookEntity> getBookInfoByName(String name) {
        return bookMapper.getBookInfoByName(name);
    }

    @Override
    public List<BookEntity> getBookInfoByKeyAndPage(String name, Integer page) {
        if(name!=null)
            return bookMapper.getBookInfoByKeyAndPage1(name,(page - 1)*10);
        return bookMapper.getBookInfoByKeyAndPage2((page - 1)*10);
    }

    @Override
    public Integer getBookInfoCount() {
        return bookMapper.getBookInfoCount();
    }

    @Override
    public Boolean addBookInfo(BookEntity bookEntity) {
        return bookMapper.addBookInfo(bookEntity) > 0;
    }

    @Override
    public Boolean deleteBookInfo(Integer id) {
        return bookMapper.deleteBookInfo(id) > 0;
    }

    @Override
    public Boolean updateBookInfo(BookEntity bookEntity) {
        return bookMapper.updateBookInfo(bookEntity) > 0;
    }

    @Override
    public Boolean updateBookQuantity(Integer id, Integer num) {
        return bookMapper.updateBookQuantity(id, num) > 0;
    }

//------------
// 购书记录操作：
//------------

    @Override
    public List<BuyBookEntity> getAllBuyBook() {
        return bookMapper.getAllBuyBook();
    }

    @Override
    public BuyBookEntity getBuyBookById(Integer id) {
        return bookMapper.getBuyBookById(id);
    }

    @Override
    public Boolean buyBook(BuyBookEntity buyBookEntity) {
        return bookMapper.addBuyBook(buyBookEntity) > 0;
    }

    @Override
    public Boolean updateBuyState(Integer id) {
        return bookMapper.updateBuyState(id) > 0;
    }

    @Override
    public Boolean deleteBuyBook(Integer id) {
        return bookMapper.deleteBuyBook(id) > 0;
    }

    @Override
    public Boolean updateBuyBook(BuyBookEntity buyBookEntity) {
        return bookMapper.updateBuyBook(buyBookEntity) > 0;
    }

    @Override
    public List<BuyBookDTO> getBuyBookInfo() {
        return bookMapper.getBuyBookInfo();
    }
//------------
// 库存记录操作：
//------------

    //in book
    @Override
    public List<InBookEntity> getAllInBook() {
        return bookMapper.getAllInBook();
    }

    @Override
    public Boolean addInBook(InBookEntity inBookEntity) {
        return bookMapper.addInBook(inBookEntity) > 0;
    }

    @Override
    public Boolean updateInBook(InBookEntity inBookEntity) {
        return bookMapper.updateInBook(inBookEntity) > 0;
    }

    @Override
    public Boolean deleteInBook(Integer id) {
        return bookMapper.deleteInBook(id) > 0;
    }

    @Override
    public List<InBookDTO> getInBookInfo() {
        return bookMapper.getInBookInfo();
    }

    //out book
    @Override
    public Boolean addOutBook(OutBookEntity outBookEntity) {
        return bookMapper.addOutBook(outBookEntity) > 0;
    }

    @Override
    public Boolean updateOutBook(OutBookEntity outBookEntity) {
        return bookMapper.updateOutBook(outBookEntity) > 0;
    }

    @Override
    public Boolean deleteOutBook(Integer id) {
        return bookMapper.deleteOutBook(id) > 0;
    }

    @Override
    public List<OutBookEntity> getAllOutBook() {
        return bookMapper.getAllOutBook();
    }

    @Override
    public List<OutBookDTO> getOutBookInfo() {
        return bookMapper.getOutBookInfo();
    }

}
