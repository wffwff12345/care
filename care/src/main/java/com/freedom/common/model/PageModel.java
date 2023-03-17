package com.freedom.common.model;

import org.springframework.data.domain.Page;

public class PageModel<T> {

    private int page;
    private int size;
    private long total;
    private Iterable<T> items;

    public PageModel(Page<T> model) {
        this.page = model.getNumber();
        this.size = model.getSize();
        this.total = model.getTotalElements();
        this.items = model.getContent();
    }

    public PageModel(int page, int size, long total, Iterable<T> items) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.items = items;
    }

    // region getter

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public Iterable<T> getItems() {
        return items;
    }

    // endregion
}
