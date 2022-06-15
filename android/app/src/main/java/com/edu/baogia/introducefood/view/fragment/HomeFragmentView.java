package com.edu.baogia.introducefood.view.fragment;

import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;

import java.util.List;

public interface HomeFragmentView {
    void listRcy(List<Food> listDemo);
    void listRcyCate(List<LoaiMonAn> listDemo);
}
