package top.otsuland.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.otsuland.market.common.Result;
import top.otsuland.market.dto.SessionReq;
import top.otsuland.market.dto.SessionResp;
import top.otsuland.market.entity.Session;
import top.otsuland.market.entity.SessionMes;
import top.otsuland.market.service.SessionService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    /**
     * 创建会话
     */
    @PostMapping("/{rid}")
    public Result<?> session(
        @RequestAttribute("id") Integer sid,
        @PathVariable Integer rid
    ) {
        Integer sesid = sessionService.createSession(sid, rid);
        if(sesid == 0) {
            return Result.set(0, "用户不存在或已经创建！");
        }
        SessionResp sresp = new SessionResp();
        sresp.setSesid(sesid);
        return Result.set(1, "创建成功！", sresp);
    }

    /**
     * 创建消息
     */
    @PostMapping("/mes/{sesid}")
    public Result<?> msg(
        @RequestAttribute("id") Integer sid,
        @PathVariable Integer sesid,
        @RequestBody SessionReq sreq
    ) {
        if(sessionService.createMsg(sid, sesid, sreq.getMsg()) == 1) {
            return Result.set(1, "创建成功！");
        }
        return Result.set(0, "创建失败！");
    }
    

    /**
     * 获取会话列表
     */
    @GetMapping
    public Result<?> ses(@RequestAttribute("id") Integer sid) {
        List<Session> sess = sessionService.getSes(sid);
        if(sess == null || sess.isEmpty()) {
            return Result.set(0, "获取失败！");
        }
        return Result.set(1, "获取成功", sess);
    }


    /**
     * 获取消息列表
     */
    @GetMapping("/{sesid}")
    public Result<?> getMsg(
        @RequestAttribute("id") Integer sid,
        @PathVariable Integer sesid
    ) {
        List<SessionMes> msglist = sessionService.getMsg(sesid, sid);
        if(msglist == null || msglist.isEmpty()) {
            return Result.set(0, "获取失败");
        }
        return Result.set(1, "获取成功", msglist);
    }
    
}
