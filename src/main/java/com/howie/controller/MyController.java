package com.howie.controller;

import com.howie.sevice.MyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created with IntelliJ IDEA
 *
 * @author shiyun shiyun.zb@ccbft.com
 * @description
 * @date 2020-12-16
 * @time 12:25
 */
@RestController
public class MyController {
    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping(value = "/login")
    public Object login(@Param("phone") String phone, @Param("password") String password) {
        if(!isChinaPhoneLegal(phone)) {
            return "手机号格式错误";
        }
//        if (myService.isUserExist(phone) == null || myService.isUserExist(phone).isEmpty()) {
//            int insertRes = myService.insertUser(phone, password);
//            return insertRes;
//        } else {
//            List<Map<String,Object>> res = myService.login(phone, password);
//            if(res!=null && !res.isEmpty()){
//                return res;
//            }else{
//                return "手机号或密码错误";
//            }
//        }
        List<Map<String,Object>> res = myService.login(phone, password);
        if(res!=null && !res.isEmpty()){
            return res;
        }else{
            return "手机号或密码错误";
        }
    }

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @RequestMapping(value = "/getBookList")
    public List<Map<String,Object>> getBookList(@RequestParam Integer book_status) {
        return myService.getBookList(book_status);
    }
    @RequestMapping(value = "/getHaveBorrowBook")
    public List<Map<String,Object>> getHaveBorrowBook(@Param("phone") String phone) {
        return myService.getHaveBorrowBook(phone);
    }
    @RequestMapping(value = "/giveBackBook")
    public int giveBackBook(@Param("book_isbn") String book_isbn) {
        return myService.giveBackBook(book_isbn);
    }
    @RequestMapping(value = "/getScanBookInfo")
    public Object getScanBookInfo(@Param("book_isbn") String book_isbn, @Param("phone") String phone) {
        List<Map<String,Object>> list = myService.getScanBookInfo(book_isbn);
        for (Map<String, Object> map : list) {
            if(map.get("book_status").equals(0)){
                myService.borrowBook(book_isbn, phone);
                return list;
            } else {
                return "此书已经被借出";
            }
        }
        return list;
    }
}
