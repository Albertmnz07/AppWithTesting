package AppPro.infrastructure.input.CLI.adapters;


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
