package com.howie.dao;

import org.springframework.stereotype.Repository;

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
@Repository
public interface MyMapper {
    String user();

    List<Map<String, Object>> login(String phone, String password);

    List<Map<String, Object>> getBookList(Integer book_status);

    List<Map<String, Object>> getScanBookInfo(String book_isbn);

    List<Map<String, Object>> isUserExist(String phone);

    int insertUser(String phone, String password);
    int borrowBook(String phone, String book_isbn);
    int giveBackBook(String book_isbn);
    List<Map<String, Object>> getHaveBorrowBook(String phone);
}