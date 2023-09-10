package org.apache.coyote.http11;

import org.apache.catalina.Manager;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager implements Manager {

    private static final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();

    @Override
    public void add(Session session) {
        SESSIONS.put(session.getId(), session);
    }

    @Override
    public Session findSession(String id) throws IOException {
        if (SESSIONS.containsKey(id)) {
            return SESSIONS.get(id);
        }
        throw new IOException("Not found Session");
    }

    @Override
    public void remove(Session session) {
        SESSIONS.remove(session.getId());
    }
}
