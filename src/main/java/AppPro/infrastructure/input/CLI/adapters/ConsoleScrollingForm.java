package AppPro.infrastructure.input.CLI.adapters;


import AppPro.infrastructure.input.CLI.exceptions.BackNavigationException;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class ConsoleScrollingForm {

    public static final int X = 1;
    public static final TextColor defaultColor = TextColor.ANSI.WHITE;

    private final Screen screen;
    private final TextGraphics tg;
    private int currentY = 1;

    public ConsoleScrollingForm(Screen screen){
        this.screen = screen;
        this.tg = screen.newTextGraphics();
        clear();
    }

    public void printLine(String prompt , TextColor color){
        checkScroll();
        tg.setForegroundColor(color);
        tg.putString(X , currentY++ , prompt);
        refresh();
    }

    public void printLine(String prompt){
        printLine(prompt , defaultColor);
    }

    public String readInput(String prompt , boolean isPassword){

        checkScroll();
        StringBuilder buffer = new StringBuilder();
        int startX = X + prompt.length() + 2; //point to star writing //+2 because ": "
        boolean isWriting = true;

        while (isWriting){

            tg.setForegroundColor(TextColor.ANSI.CYAN);
            tg.putString(X , currentY , prompt + ": ");
            tg.setForegroundColor(TextColor.ANSI.WHITE);
            String visibleString = isPassword ?
                    "*".repeat(buffer.length()) :
                    buffer.toString();
            tg.putString(startX , currentY , visibleString + "_ ");

            try {

                KeyStroke stroke = screen.readInput();
                KeyType key = stroke.getKeyType();

                switch(key){
                    case Enter -> {
                        tg.putString(startX , currentY , visibleString + " ");
                        currentY++;
                        isWriting = false;
                    }

                    case Escape -> {
                        isWriting = false;
                        throw new BackNavigationException();
                    }

                    case Backspace -> {
                        if (buffer.length() > 0){
                            buffer.deleteCharAt(buffer.length() - 1);
                            tg.putString(startX + buffer.length() - 1 , currentY , " ");
                        }
                    }

                    default -> {
                        if (stroke.getCharacter() != null){
                            buffer.append(stroke.getCharacter());
                        }
                    }
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        return buffer.toString();
    }

    public void checkScroll(){
        TerminalSize size = screen.getTerminalSize();
        int rows = size.getRows();
        int cols = size.getColumns();

        if (currentY >= rows - 1){
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
