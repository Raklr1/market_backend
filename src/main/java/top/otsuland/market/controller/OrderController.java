package top.otsuland.market.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.otsuland.market.common.Result;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public Result<?> create(@RequestAttribute("id") Integer uid) {
        return null;
    }

}
