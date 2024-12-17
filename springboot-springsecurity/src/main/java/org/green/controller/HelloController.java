package org.green.controller;

import org.green.vo.ResultVO;
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
}
