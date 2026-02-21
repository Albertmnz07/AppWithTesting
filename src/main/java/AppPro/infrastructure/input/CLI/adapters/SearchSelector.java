package AppPro.infrastructure.input.CLI.adapters;

import AppPro.infrastructure.input.CLI.exceptions.BackNavigationException;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.BasicCharacterPattern;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SearchSelector<T> {

    private final Screen screen;
    private final TextGraphics tg;

    //providers
    private final Function<String, List<T>> searchProvider;
    private final Function<T, String> labelProvider;
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

    public void show() throws IOException {
        refreshResults(); //update variables

        boolean running = true;
        while (running) {
            try {

                render(); //paint the current state

                KeyStroke key = screen.readInput();
                if (key == null) continue;
                KeyType type = key.getKeyType();

                switch (type) {
                    case Escape -> {
                        running = false;
                        throw new BackNavigationException();
                    }
                    case Enter -> executeSelection();
                    case ArrowUp -> moveSelection(1);
                    case ArrowDown -> moveSelection(-1);
                    case Backspace -> {
                        if (!currentQuery.isEmpty()) {
                            currentQuery = currentQuery.substring(0, currentQuery.length() - 1);
                            refreshResults();
                        }
                    }
                    case Character -> {
                        currentQuery = currentQuery + key.getCharacter();
                        refreshResults();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }


        }
    }

    private void refreshResults() {
        List<T> rawResults = searchProvider.apply(currentQuery);

        rawResults.sort(Comparator.comparingInt(item -> labelProvider.apply(item).length()));

        this.currentResults = rawResults;
        this.selectedItem = 0;
        this.scrollOffset = 0;
    }

    private void moveSelection(int delta) {
        if (currentResults.isEmpty()) {
            return;
        }

        int newIndex = selectedItem + delta;
        int maxIndex = currentResults.size() - 1;

        if(newIndex < 0) newIndex = 0;
        if (newIndex > maxIndex) newIndex = maxIndex;

        selectedItem = newIndex;
        chekScroll();

    }

    private void chekScroll(){
        int visibleRows = screen.getTerminalSize().getRows() - 4;

        if (selectedItem < scrollOffset){
            scrollOffset = selectedItem;
        } else if (selectedItem >= scrollOffset + visibleRows){
            scrollOffset = selectedItem - visibleRows + 1;
        }
    }

    private void executeSelection() {
        if (!currentResults.isEmpty() && selectedItem >= 0 && selectedItem < currentResults.size()) {
            T selectedItem = currentResults.get(this.selectedItem);
            onSelectAction.accept(selectedItem);
        }
    }

    private void render() throws IOException {
        screen.clear();

        tg.setForegroundColor(TextColor.ANSI.CYAN);
        tg.putString(2, 1, "Search: " + currentQuery + "_");

        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(2, 2, "-".repeat(screen.getTerminalSize().getColumns()));

        int startRow = 3;
        int maxRows = screen.getTerminalSize().getRows() - 4;

        for (int i = 0; i < maxRows; i++) {
            int itemIndex = scrollOffset + i;

            if (itemIndex >= currentResults.size()) {
                break;
            }

            String label = labelProvider.apply(currentResults.get(itemIndex));

            if (itemIndex == selectedItem) {
                tg.enableModifiers(SGR.REVERSE);
                tg.putString(4, startRow + i, "-> " + label);
                tg.disableModifiers(SGR.REVERSE);
            } else {
                tg.putString(2, itemIndex, label);
            }
        }

        if (currentResults.isEmpty()) {
            tg.setForegroundColor(TextColor.ANSI.RED);
            tg.putString(4, startRow, "(No results)");
        }

        screen.refresh();
    }


}
