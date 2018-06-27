package com.shop.ningbaoqi.ningbaoqi_core.delegates.web.event;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;

public class EventManager {
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {

    }

    private static class Holder {
        public static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action) {
        Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
