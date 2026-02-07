package AppPro.infrastructure.input.CLI.adapters;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class ConsoleScrollingForm {

    private final Screen screen;
    private final TextGraphics tg;
    private int currentY = 0;

    public ConsoleScrollingForm(Screen screen){
        this.screen = screen;
        this.tg = screen.newTextGraphics();
        clear();
    }

    public void printLine(String prompt , TextColor color){
        tg.setForegroundColor(color);
        tg.putString(0 , currentY++ , prompt);
        refresh();
    }

    public void printLine(String prompt){
        printLine(prompt , TextColor.ANSI.WHITE);
    }

    public void checkScroll(){
        TerminalSize size = screen.getTerminalSize();
        int rows = size.getRows();
        int cols = size.getColumns();

        if (rows >= currentY - 1){
            screen.scrollLines(0 , rows -1 , 1);
            currentY = rows - 2;
            tg.putString(2 , currentY , " ".repeat(cols));
        }
    }

    public void clear(){
        screen.clear();
        currentY = 1;
        refresh();
    }

    public void refresh(){
        try{
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
