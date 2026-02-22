package AppPro.infrastructure.input.CLI.base;

import AppPro.infrastructure.input.CLI.adapters.SearchSelector;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

import java.util.List;

public abstract class AbstractSearchSelector<T> extends AbstractCLIPage{

    public AbstractSearchSelector(UIManager ui, CLINavigator navigator) {
        super(ui, navigator);
    }

    @Override
    public void runSafe() {
        SearchSelector<T> selector = ui.createSearchSelector(
                this::search,
                this::getLabel,
                this::onSelect
        );

        selector.show();
    }

    protected abstract List<T> search(String query);

    protected abstract String getLabel(T item);

    protected abstract void onSelect(T item);



}
