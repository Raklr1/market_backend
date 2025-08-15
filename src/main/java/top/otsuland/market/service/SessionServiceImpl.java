package top.otsuland.market.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.otsuland.market.entity.Session;
import top.otsuland.market.entity.SessionMes;
import top.otsuland.market.mapper.SessionMapper;
import top.otsuland.market.mapper.SessionMesMapper;
import top.otsuland.market.mapper.UserMapper;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SessionMapper sessionMapper;
    @Autowired
    private SessionMesMapper sessionMesMapper;

    @Override
    public int createSession(Integer sid, Integer rid) {
        if(userMapper.selectById(sid) == null ||
            userMapper.selectById(rid) == null ||
            sessionMapper.selectBySidAndRid(sid, rid) != null) {
            return 0;
        }
        Session session = new Session();
        session.setAId(sid);
        session.setBId(rid);
        session.setAUsername(userMapper.selectUsernameById(sid));
        session.setBUsername(userMapper.selectUsernameById(rid));
        if(sessionMapper.insert(session) == 1) {
            return sessionMapper.selectBySidAndRid(sid, rid).getId();
        }
        return 0;
    }

    @Override
    public int createMsg(Integer sid, Integer sesid, String msg) {
        if(userMapper.selectById(sid) == null ||
            sessionMapper.selectById(sesid) == null) {
            return 0;
        }
        SessionMes mes = new SessionMes();
        mes.setContent(msg);
        mes.setSessionId(sesid);
        mes.setSid(sid);
        mes.setSendTime(new Timestamp(System.currentTimeMillis()));
        return sessionMesMapper.insert(mes);
    }

    @Override
    public List<Session> getSes(Integer sid) {
        return sessionMapper.selectBySidOrRid(sid);
    }

    @Override
    public List<SessionMes> getMsg(Integer sesid, Integer sid) {
        Session ses = sessionMapper.selectById(sesid);
        if(ses == null) {
            return null;
        } else if(ses.getAId() != sid && ses.getBId() != sid) {
            return null;
        }
        return sessionMesMapper.selectBySessionId(sesid);
    }
}
