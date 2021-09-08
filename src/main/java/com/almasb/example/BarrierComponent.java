package com.almasb.example;

import com.almasb.fxgl.entity.component.Component;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class BarrierComponent extends Component {

    private int numKeys = 0;

    public void open(int numberOfKeys) {
        numKeys += numberOfKeys;
    }

    public boolean isUnlocked() {
        return numKeys == 2;
    }
}
