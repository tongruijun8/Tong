package com.trj.tlib.activity.modules;

import java.util.List;

public interface MListView<V> extends MInitView {

    void getListDataSuccess(List<V> infoList);

}
