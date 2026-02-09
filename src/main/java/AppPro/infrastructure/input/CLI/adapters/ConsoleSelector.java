package AppPro.infrastructure.input.CLI.adapters;

import AppPro.infrastructure.input.CLI.exceptions.BackNavigationException;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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

    public void show(){
        int selectedItem = 0;
        boolean running = true;

        while (running){

            try{
                render(selectedItem);

                KeyStroke stroke = screen.readInput();
                KeyType type = stroke.getKeyType();

                switch (type){
                    case ArrowUp -> selectedItem = selectedItem == 0 ? itemList.size() - 1 : selectedItem - 1;
                    case ArrowDown -> selectedItem = selectedItem == itemList.size() - 1 ? 0 : selectedItem +1;
                    case Enter -> {
                        itemList.get(selectedItem).action().run();
                        running = false;
                    }
                    case Escape ->{
                        running = false;
                        throw new BackNavigationException();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
