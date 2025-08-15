package top.otsuland.market.service;

import java.util.List;

import top.otsuland.market.entity.Session;
import top.otsuland.market.entity.SessionMes;

public interface SessionService {
    int createSession(Integer sid, Integer rid);
    int createMsg(Integer sid, Integer sesid, String msg);
    List<SessionMes> getMsg(Integer sesid, Integer sid);
    List<Session> getSes(Integer sid);
}
