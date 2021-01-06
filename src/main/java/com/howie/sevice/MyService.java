package com.howie.sevice;

import com.howie.dao.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author shiyun shiyun.zb@ccbft.com
 * @description
 * @date 2020-12-16
 * @time 12:25
 */
@Service
public class MyService {
    private final MyMapper myMapper;

    @Autowired
    public MyService(MyMapper myMapper) {
        this.myMapper = myMapper;
    }

    public List<Map<String,Object>> login(String phone, String password) {
        return myMapper.login(phone, password);
    }

    public List<Map<String,Object>> getBookList(Integer book_status) {
        return myMapper.getBookList(book_status);
    }

    public List<Map<String,Object>> getScanBookInfo(String book_isbn) {
        return myMapper.getScanBookInfo(book_isbn);
    }

    public List<Map<String,Object>> isUserExist(String phone) {
        return myMapper.isUserExist(phone);
    }

    public int insertUser(String phone, String password) {
        return myMapper.insertUser(phone, password);
    }

    public int borrowBook(String book_isbn, String phone) {
        return myMapper.borrowBook(book_isbn, phone);
    }

    public int giveBackBook(String book_isbn) {
        return myMapper.giveBackBook(book_isbn);
    }

    public List<Map<String,Object>> getHaveBorrowBook(String phone) {
        return myMapper.getHaveBorrowBook(phone);
    }
}
