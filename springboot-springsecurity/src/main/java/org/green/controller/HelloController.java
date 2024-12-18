package org.green.controller;

import org.green.vo.ResultVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@RestController
public class HelloController {

    @GetMapping("/test1")
    public ResultVO test1() {
        System.out.println("test1");
        return ResultVO.ok("test1");
    }

    @GetMapping("/test2")
    public ResultVO test2() {
        System.out.println("test2");
        return ResultVO.ok("test2");
    }

    /**
     * 只有admin角色才可访问
     * @return test3
     */
    @PreAuthorize(value = "hasRole('admin')")
    @GetMapping("/test3")
    public ResultVO test3() {
        System.out.println("test3");
        return ResultVO.ok("test3");
    }

    /**
     * 具有admin角色或者CEO角色
     */
    @PreAuthorize(value = "hasAnyRole('admin','CEO')")
    @GetMapping("/test4")
    public ResultVO test4() {
        System.out.println("test4");
        return ResultVO.ok("test4");
    }

    /**
     * 同时具有CTO和CFO角色
     */
    @PreAuthorize(value = "hasRole('CTO') and hasRole('CFO')")
    @GetMapping("/test5")
    public ResultVO test5() {
        System.out.println("test5");
        return ResultVO.ok("test5");
    }

    /**
     * 具有user:add权限
     */
    @PreAuthorize(value = "hasAuthority('user:add')")
    @GetMapping("/test6")
    public ResultVO test6() {
        System.out.println("test6");
        return ResultVO.ok("test6");
    }

    /**
     * 具有 user:add 或者 user:del权限可以访问
     */
    @PreAuthorize(value = "hasAnyAuthority('user:add','user:del')")
    @GetMapping(value = "/test7")
    public ResultVO test7(){
        System.out.println("test7...");
        return ResultVO.ok("test7...");
    }

    //具有user:add和user:del权限可以访问
    @PreAuthorize(value = "hasAuthority('user:add') and hasAuthority('user:del')")
    @GetMapping(value = "/test8")
    public ResultVO test8(){
        System.out.println("test8...");
        return ResultVO.ok("test8...");
    }
}
