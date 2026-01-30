package AppPro.infrastructure.input.CLI.adapters;

import com.googlecode.lanterna.screen.Screen;

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
    public ConsoleSelector add(Runnable action , String label){
        itemList.add(new MenuItem(action , label));
        return this;
    }
}
