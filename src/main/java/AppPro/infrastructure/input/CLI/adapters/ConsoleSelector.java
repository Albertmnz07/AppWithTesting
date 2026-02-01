package AppPro.infrastructure.input.CLI.adapters;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleSelector {

    public record MenuItem(Runnable action , String label){}

    private final Screen screen;
    private final List<MenuItem> itemList = new ArrayList<>();
    private final String title;

    public ConsoleSelector(Screen screen  , String title){
        this.screen = screen;
        this.title = title;
    }
    public ConsoleSelector addItem(Runnable action , String label){
        itemList.add(new MenuItem(action , label));
        return this;
    }

    public void render(int selectedItem) throws IOException {
        screen.clear();
        TextGraphics tg = screen.newTextGraphics();

        tg.setForegroundColor(TextColor.ANSI.BLACK);
        tg.putString(1 , 1, title);

        for (int i = 0 ; i < itemList.size() ; i++){
            if (i == selectedItem){
                tg.setBackgroundColor(TextColor.ANSI.BLACK);
                tg.setForegroundColor(TextColor.ANSI.WHITE);
                tg.putString(1 , 1 + i , itemList.get(i).label());
            } else{
                tg.setBackgroundColor(TextColor.ANSI.WHITE);
                tg.setForegroundColor(TextColor.ANSI.BLACK);
                tg.putString(1 , 1 + i , itemList.get(i).label());
            }
        }

        screen.refresh();
    }
}
