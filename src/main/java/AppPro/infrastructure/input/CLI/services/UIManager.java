package AppPro.infrastructure.input.CLI.services;

import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.input.CLI.adapters.ConsoleSelector;
import AppPro.infrastructure.input.CLI.adapters.SearchSelector;
import AppPro.infrastructure.input.CLI.utils.LanternaMessagePresenter;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.stereotype.Service;

import java.util.List;

//Manager independent from displays technology
@Service
public class UIManager {

    private final Screen screen;
    private final LanternaMessagePresenter presenter;

    public UIManager(Screen screen , LanternaMessagePresenter presenter){
        this.screen = screen;
        this.presenter = presenter;
    }

    public ConsoleSelector createSelector(String title){
        return new ConsoleSelector(screen , title);
    }

    public ConsoleScrollingForm createForm(){
        return new ConsoleScrollingForm(screen);
    }

    public SearchSelector<T> createSearchSelector(Function<String , List<T>> searchProvider
                                                  , Function<T , String> labelProvider
                                                  , Consumer<T> onSelectedAction){
        return new SearchSelector<>(this.screen , searchProvider , labelProvider , onSelectedAction);
    }

    public void showError(ConsoleScrollingForm form , DomainException e){
        presenter.printError(form , e);
    }

    public void showInfo(ConsoleScrollingForm form , String key){
        presenter.printInfo(form , key);
    }
}
