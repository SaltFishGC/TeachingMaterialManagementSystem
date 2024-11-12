package com.example.demo.service;

import com.example.demo.DTO.BuyBookDTO;
import com.example.demo.DTO.InBookDTO;
import com.example.demo.DTO.OutBookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BuyBookEntity;
import com.example.demo.entity.InBookEntity;
import com.example.demo.entity.OutBookEntity;

import java.util.List;

public interface IBookService {
    Integer getNumOfBook();

    List<BookEntity> getBookInfoAll();

    List<BookEntity> getBookInfoByAuthor(String name);

    BookEntity getBookInfoById(Integer id);

    List<BookEntity> getBookInfoByKey(String name);

    List<BookEntity> getBookInfoByName(String name);

    List<BookEntity> getBookInfoByKeyAndPage(String name, Integer page);

    Integer getBookInfoCount();

    Boolean addBookInfo(BookEntity bookEntity);

    Boolean deleteBookInfo(Integer id);

    Boolean updateBookInfo(BookEntity bookEntity);

    Boolean updateBookQuantity(Integer id, Integer num);

    //------------
    // 购书记录操作：
    //------------
    List<BuyBookEntity> getAllBuyBook();

    BuyBookEntity getBuyBookById(Integer id);

    Boolean buyBook(BuyBookEntity buyBookEntity);

    Boolean updateBuyState(Integer id);

    Boolean deleteBuyBook(Integer id);

    Boolean updateBuyBook(BuyBookEntity buyBookEntity);

    List<BuyBookDTO> getBuyBookInfo();

    List<InBookEntity> getAllInBook();

    Boolean addInBook(InBookEntity inBookEntity);

    Boolean updateInBook(InBookEntity inBookEntity);

    Boolean deleteInBook(Integer id);

    List<InBookDTO> getInBookInfo();

    Boolean addOutBook(OutBookEntity outBookEntity);

    Boolean updateOutBook(OutBookEntity outBookEntity);

    Boolean deleteOutBook(Integer id);

    List<OutBookEntity> getAllOutBook();

    List<OutBookDTO> getOutBookInfo();
}
