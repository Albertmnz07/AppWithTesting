package main.application.ports;

public interface InputPort {
    String readString(String prompt);
    int readInt(String prompt);
}
