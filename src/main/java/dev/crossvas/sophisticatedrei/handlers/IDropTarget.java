package dev.crossvas.sophisticatedrei.handlers;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.drag.DraggableStack;

public interface IDropTarget {

    Rectangle getArea();
    boolean accept(DraggableStack stack);
}
