package ua.edu.sumdu.j2se.ZavaliiVV.tasks.model;

import ua.edu.sumdu.j2se.ZavaliiVV.tasks.view.Observer;

/**
 * Created by Administratorus on 29.12.2015.
 */
public interface Observable {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

}
