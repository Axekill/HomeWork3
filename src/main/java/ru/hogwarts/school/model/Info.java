package ru.hogwarts.school.model;

import java.util.Objects;

public class Info {
    int port;

    public Info(int port) {
        this.port = port;
    }

    public Info() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(port, info.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Info{" +
                "port=" + port +
                '}';
    }
}
