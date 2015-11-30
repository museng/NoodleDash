package cat.attack.noodledash.API;

import cat.attack.noodledash.MainView;

/**
 * Created by kegan on 11/20/2015.
 */
public interface ButtonHandler {
    public abstract void OnDown(Element e, MainView v);
    public abstract void OnUp(Element e, MainView v);
}
