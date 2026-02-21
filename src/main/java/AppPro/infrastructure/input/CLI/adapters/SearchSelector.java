package AppPro.infrastructure.input.CLI.adapters;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SearchSelector<T> {

    private final Screen screen;
    private final TextGraphics tg;

    //providers
    private final Function<String , List<T>> searchProvider;
    private final Function<T , String> labelProvider;
    private final Consumer<T> onSelectAction;

    //component state
    private String currentQuery = "";
    private List<T> currentResults = new ArrayList<>();
    private int selectedItem = 0;
    private int scrollOffset = 0;

    public SearchSelector(Screen screen,
                          Function<String, List<T>> searchProvider,
                          Function<T, String> labelProvider,
                          Consumer<T> onSelectAction) {
        this.screen = screen;
        this.tg = screen.newTextGraphics();
        this.searchProvider = searchProvider;
        this.labelProvider = labelProvider;
        this.onSelectAction = onSelectAction;
    }

    private void refreshResults(){
        List<T> rawResults = searchProvider.apply(currentQuery);

        rawResults.sort(Comparator.comparingInt(item -> labelProvider.apply(item).length()));

        this.currentResults = rawResults;

        this.selectedItem = 0;
        this.scrollOffset = 0;
    }

    private void moveSelection(int delta){
        if (currentResults.isEmpty()){
            return;
        }

        int newIndex = selectedItem + delta;

        if (newIndex >= 0 && newIndex <= currentResults.size()){
            selectedItem = newIndex;

            int visibleRows = screen.getTerminalSize().getRows() - 4;

            if (selectedItem >= scrollOffset + visibleRows){
                scrollOffset = selectedItem - visibleRows + 1;
            }

            if (selectedItem < scrollOffset){
                scrollOffset = selectedItem;
            }
        }
    }

    private void executeSelection(){
        if (!currentResults.isEmpty() && selectedItem >= 0 && selectedItem < currentResults.size()){
            T selectedItem = currentResults.get(this.selectedItem);
            onSelectAction.accept(selectedItem);
        }
    }

}
