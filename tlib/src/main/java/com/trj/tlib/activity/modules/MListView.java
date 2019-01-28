package com.trj.tlib.activity.modules;

import java.util.List;

@Deprecated
public interface MListView<V> extends MInitView {

    void getListDataSuccess(List<V> infoList);

}
