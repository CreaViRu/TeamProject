package org.example.menu.interfaces;

import org.example.menu.Menu;

@FunctionalInterface
public interface Renderer {
    void render(Menu reflect);
}
