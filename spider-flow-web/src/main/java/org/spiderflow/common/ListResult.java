package org.spiderflow.common;

import java.util.Collections;
import java.util.List;

/**
 * @Author : xuaoping
 * @Date : 2023/08
 */
public class ListResult<E> {
    public static final ListResult EMPTY_RESULT = buildResult(Collections.emptyList(), 0L);

    private List<E> list;
    private long total;

    public static <E> ListResult<E> buildResult(List<E> list, long total) {
        ListResult<E> result = new ListResult();
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }

        result.setList(list);
        result.setTotal(total);
        return result;
    }

    public List<E> getList() {
        return this.list;
    }

    public long getTotal() {
        return this.total;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ListResult(list=" + this.getList() + ", total=" + this.getTotal() + ")";
    }

    public ListResult() {
    }

    public ListResult(List<E> list, long total) {
        this.list = list;
        this.total = total;
    }
}
